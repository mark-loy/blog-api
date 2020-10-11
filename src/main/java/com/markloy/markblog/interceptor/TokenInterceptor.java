package com.markloy.markblog.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        ResultDTO error;
        if (!StringUtils.isEmpty(token)) {
            try {
                JwtUtil.verifyToken(token);
                return true; //请求放行
            } catch (SignatureVerificationException e) {
                error = ResultDTO.fail("签名异常");
            } catch (TokenExpiredException e) {
                error = ResultDTO.fail("token过期");
            } catch (AlgorithmMismatchException e) {
                error = ResultDTO.fail("算法不匹配");
            } catch (InvalidClaimException e) {
                error = ResultDTO.fail("payload失效");
            } catch (Exception e) {
                error = ResultDTO.fail(e.getMessage());
            }
        } else {
            error = ResultDTO.fail("token为空");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JSON.toJSONString(error));
        return false;
    }
}
