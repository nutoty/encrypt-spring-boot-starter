package com.github.nutoty.encryptspringbootstarter.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nutoty.encryptspringbootstarter.annotation.Encrypt;
import com.github.nutoty.encryptspringbootstarter.config.EncryptProperties;
import com.github.nutoty.encryptspringbootstarter.utils.AESUtils;
import com.github.nutoty.encryptspringbootstarter.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<Message> {

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public Message beforeBodyWrite(Message message, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        byte[] keyBytes = encryptProperties.getKey().getBytes();
        try {
            if(message.getMsg()!=null){
                message.setMsg(AESUtils.encrypt(message.getMsg().getBytes(),keyBytes));
            }
            if(message.getObj()!=null){
                message.setObj(AESUtils.encrypt(om.writeValueAsBytes(message.getObj()),keyBytes));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
