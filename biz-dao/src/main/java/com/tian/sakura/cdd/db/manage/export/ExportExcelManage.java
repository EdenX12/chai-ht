package com.tian.sakura.cdd.db.manage.export;

import com.tian.sakura.cdd.common.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class ExportExcelManage {
    public void expoerDataExcel(HttpServletResponse response, String[] title, String[][] content, String fileName, String sheetName) {
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
