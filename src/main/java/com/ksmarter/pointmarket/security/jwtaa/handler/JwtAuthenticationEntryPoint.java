package com.ksmarter.pointmarket.security.jwtaa.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksmarter.pointmarket.domain.common.dto.CommonResponse;
import com.ksmarter.pointmarket.domain.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401(인증 실패)
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("UNAUTHORIZED")
                .code("AUTH")
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(objectMapper.writeValueAsString(CommonResponse.builder()
                .success(false)
                .error(error)
                .build()));
    }

}
