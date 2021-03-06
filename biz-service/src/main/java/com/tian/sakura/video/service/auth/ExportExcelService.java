package com.tian.sakura.video.service.auth;

import com.alibaba.fastjson.JSONArray;
import com.tian.sakura.cdd.common.req.user.AdminUserWithdraw;
import com.tian.sakura.cdd.common.resp.order.ExportOrderDetail;
import com.tian.sakura.cdd.common.resp.user.ExportUserWithdraw;
import com.tian.sakura.cdd.common.util.ExcelUtil;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;
import com.tian.sakura.cdd.db.domain.user.UserWithdraw;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import com.tian.sakura.cdd.db.manage.user.UserWithdrawManage;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class ExportExcelService {

    @Autowired
    private OrderDetailManage orderDetailManage;
    @Autowired
    private ShopManage shopManage;
    @Autowired
    private ProductManage productManage;
    @Autowired
    private UserWithdrawManage userWithdrawManage;

    public void exportOrderForShipped(HttpServletResponse response, HttpServletRequest request, String shopId) throws IOException {
        List<OrderDetail> orderList = orderDetailManage.queryNotShipped(shopId);
        String sheetName = "待发货订单表";
        JSONArray exportList = new JSONArray();
        for (OrderDetail orderDetail : orderList) {
            for (OrderProduct orderProduct : orderDetail.getOrderProductList()) {
                ExportOrderDetail exportOrderDetail = new ExportOrderDetail();
                exportOrderDetail.setId(orderDetail.getId());
                exportOrderDetail.setOrderSn(orderDetail.getOrderSn());
                exportOrderDetail.setAddressName(orderDetail.getAddressName());
                exportOrderDetail.setAddressPhone(orderDetail.getAddressPhone());
                exportOrderDetail.setAddressDetail(orderDetail.getAddressDetail());
                exportOrderDetail.setShopName(orderDetail.getShopName());
                exportOrderDetail.setProductId(orderProduct.getProductId());
                exportOrderDetail.setProductName(orderProduct.getProductName());
                exportOrderDetail.setProductSpecValueName(orderProduct.getProductSpecValueName());
                exportOrderDetail.setPrice(orderProduct.getProductPrice().toString());
                exportOrderDetail.setProductNumber(orderProduct.getProductNumber());
                exportOrderDetail.setOrderAmount(orderDetail.getOrderAmount().toString());
                exportOrderDetail.setCreateTime(orderDetail.getPaymentTime());
                exportList.add(exportOrderDetail);
            }
        }
        Map<String, String> headMap = new LinkedHashMap<>();
        headMap.put("id", "ID");
        headMap.put("orderSn", "订单编号");
        headMap.put("addressName", "收货人姓名");
        headMap.put("addressPhone", "收货人电话");
        headMap.put("addressDetail", "收货人地址");
        headMap.put("shopName", "商户名称");
        headMap.put("productId", "商品Id");
        headMap.put("productName", "商品名称");
        headMap.put("productSpecValueName", "规格");
        headMap.put("price", "单价");
        headMap.put("productNumber", "数量");
        headMap.put("orderAmount", "订单金额");
        headMap.put("createTime", "支付时间");
        headMap.put("shippingCode", "物流单号");
        headMap.put("deliverExplain", "发货备注");
        ExcelUtil.downloadExcelFile(sheetName, headMap, exportList, response, request);
    }

    public void uploadOrderShipped(MultipartFile file) throws IOException {
        //从第一行开始读取
        int rowBegin = 1;
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());//由输入流文件得到工作簿对象
        XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
        int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数
        //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
        List<ExportOrderDetail> orderDetails = new ArrayList<>();
        for (int i = rowBegin; i <= lastRowNum; ++i) {
            XSSFRow row = sheet.getRow(i);//获取每一行
            int columnNum = row.getLastCellNum();//获取每一行的最后一列的列号，即总列数
            //读取的订单信息
            ExportOrderDetail orderDetail = new ExportOrderDetail();
            for (int j = 0; j < columnNum; ++j) {
                XSSFCell cell = row.getCell(j);//获取每个单元格
                switch (j) {
                    case 0:
                        orderDetail.setId(cell.getStringCellValue());
                        break;
                    case 1:
                        orderDetail.setOrderSn(cell.getStringCellValue());
                        break;
                    case 13:
                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        orderDetail.setShippingCode(cell.getStringCellValue().trim());
                        break;
                    case 14:
                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        orderDetail.setDeliverExplain(cell.getStringCellValue());
                        break;
                }
            }
            orderDetails.add(orderDetail);
        }
        file.getInputStream().close();
        //更新物流单号
        orderDetails.forEach(e -> {
            if (StringUtils.isNoneEmpty(e.getShippingCode())) {
                OrderDetail update = new OrderDetail();
                update.setId(e.getId());
                update.setShippingCode(e.getShippingCode());
                update.setShippingTime(new Date());
                update.setOrderStatus(2);
                update.setDeliverExplain(e.getDeliverExplain());
                orderDetailManage.updateByPrimaryKeySelective(update);
            }
        });
    }

    public void exportUserWithdraw(HttpServletResponse response, HttpServletRequest request) {
        AdminUserWithdraw adminUserWithdraw = new AdminUserWithdraw();
        adminUserWithdraw.setPageNum(null);
        adminUserWithdraw.setPageSize(null);
        List<UserWithdraw> list = userWithdrawManage.queryUserWithdraw(adminUserWithdraw);
        String sheetName = "待发货订单表";
        //0待审核、1审核成功、2审核拒绝、3打款中、4打款成功、5打款失败
        JSONArray exportList = new JSONArray();
        for (UserWithdraw userWithdraw : list) {
            ExportUserWithdraw exportUserWithdraw = new ExportUserWithdraw();
            exportUserWithdraw.setId(userWithdraw.getId());
            exportUserWithdraw.setRealName(userWithdraw.getRealName());
            exportUserWithdraw.setIdCard(userWithdraw.getIdCard());
            exportUserWithdraw.setBankName(userWithdraw.getBankName());
            exportUserWithdraw.setBankCode(userWithdraw.getBankCode());
            exportUserWithdraw.setAmount(userWithdraw.getAmount());
            if (StringUtils.isNotEmpty(userWithdraw.getStatus())) {
                switch (Integer.parseInt(userWithdraw.getStatus())) {
                    case 0:
                        exportUserWithdraw.setWithdrawStatus("待审核");
                        break;
                    case 1:
                        exportUserWithdraw.setWithdrawStatus("审核成功");
                        break;
                    case 2:
                        exportUserWithdraw.setWithdrawStatus("审核拒绝");
                        break;
                    case 3:
                        exportUserWithdraw.setWithdrawStatus("打款中");
                        break;
                    case 4:
                        exportUserWithdraw.setWithdrawStatus("打款成功");
                        break;
                    case 5:
                        exportUserWithdraw.setWithdrawStatus("打款失败");
                        break;
                }
            }
            exportUserWithdraw.setCreateTime(userWithdraw.getCreateTime());
            exportList.add(exportUserWithdraw);
        }
        Map<String, String> headMap = new LinkedHashMap<>();
        headMap.put("id", "ID");
        headMap.put("realName", "提现人");
        headMap.put("idCard", "身份证号");
        headMap.put("bankName", "银行名称");
        headMap.put("bankCode", "银行卡号");
        headMap.put("amount", "提现金额");
        headMap.put("withdrawStatus", "提现状态");
        headMap.put("createTime", "创建时间");
        ExcelUtil.downloadExcelFile(sheetName, headMap, exportList, response, request);
    }
}
