package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.order.AdminOrderDetailReq;
import com.tian.sakura.cdd.common.req.order.AdminOrderReq;
import com.tian.sakura.cdd.common.req.order.AdminUserTaskReq;
import com.tian.sakura.cdd.db.dao.order.OrderDetailMapper;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.video.service.auth.ExpressService;
import com.tian.sakura.video.service.auth.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @PostMapping("/listOrder")
    public ResultDto listOrder(@RequestBody AdminOrderReq req) {
        return ResultDto.success().setObj(orderService.queryOrderList(req));
    }

    @PostMapping("/listOrderDetail")
    public ResultDto listOrderDetail(@RequestBody AdminOrderDetailReq req) {
        return ResultDto.success().setObj(orderService.listOrderDetail(req));
    }

    @PostMapping("/updateOrderLogistics")
    public ResultDto updateOrderLogistics(@RequestBody OrderDetail orderDetail) {
        orderService.updateOrderLogistics(orderDetail);
        return ResultDto.success();
    }

    @RequestMapping("/queryExpress")
    public ResultDto queryExpress(@RequestBody OrderDetail orderDetail) {
        return ResultDto.success().setObj(expressService.queryExpressByCode(orderDetail.getShippingCode()));
    }

    @PostMapping("/listTaskOrder")
    public ResultDto listTaskOrder(@RequestBody AdminUserTaskReq userTask){
        return ResultDto.success().setObj(orderService.listTaskOrder(userTask));
    }

    @PostMapping("/orderDetailShippingCode")
    public ResultDto orderDetailShippingCode(@RequestBody OrderDetail orderDetail){
        List<OrderDetail> list = orderDetailMapper.orderDetailShippingCode();
        list.forEach(e->{
            e.setShippingCode(e.getShippingCode().trim());
            orderDetailMapper.updateShippingCode(e.getId(),e.getShippingCode());
        });
        return ResultDto.success();
    }

}
