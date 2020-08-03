package inteceptor;

import com.alibaba.fastjson.JSON;
import com.wsf.demo.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        boolean bool = false;
        String token = request.getHeader(JwtUtils.AUTH_HEADER);
        try {
            bool = !JwtUtils.isExpired(token);
        } catch (Exception e) {
            ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body("token失效");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(responseEntity));
        }
        return bool;
    }
}
