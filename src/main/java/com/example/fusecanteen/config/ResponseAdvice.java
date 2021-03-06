package com.example.fusecanteen.config;

import com.example.fusecanteen.dto.RestResponse;
import com.example.fusecanteen.utility.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.zalando.problem.DefaultProblem;

@RestControllerAdvice(basePackages = "com.example.fusecanteen.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public RestResponse beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof RestResponse)
            return (RestResponse) body;
        else if (body instanceof DefaultProblem)
            return ResponseUtil.getErrorResponse((DefaultProblem) body, ResponseUtil.getResponseMessage(response, false));
        else
            return ResponseUtil.getSuccessResponse(body, ResponseUtil.getResponseMessage(response, true));
    }

}
