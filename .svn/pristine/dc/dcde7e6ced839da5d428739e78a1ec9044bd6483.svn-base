package com.tian.sakura.cdd.common.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tian.sakura.cdd.common.dto.ResultDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 咸鱼
 * @date 2019-06-04 19:45
 */
public final class CommonUtils {
    public static void writeResponse(HttpServletResponse response, ResultDto resultDto) throws IOException {
        writeResponse(response, resultDto, null);
    }

    public static void writeResponse(HttpServletResponse response, ResultDto resultDto, Integer status) throws IOException {
        response.setContentType(FinalName.CONTENT_TYPE);
        if (status != null) {
            response.setStatus(status);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(resultDto));
        out.flush();
        out.close();
    }
}
