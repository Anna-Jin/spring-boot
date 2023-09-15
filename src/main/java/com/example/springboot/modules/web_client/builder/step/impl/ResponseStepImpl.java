package com.example.springboot.modules.web_client.builder.step.impl;

import com.example.springboot._common.exception.ApiException;
import com.example.springboot._common.exception.errorCode.ResponseCode;
import com.example.springboot.modules.web_client.builder.step.ResponseStep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class ResponseStepImpl implements ResponseStep {

    private final Object response;

    /**
     * WebClient의 response를 Object로 리턴<br>
     * 응답값을 response type class로 파싱 후 사용<br>
     * <br>
     * Example)<br>
     * TestDto test = (TestDto) webClientConnectorTest.post(url, path, requestBody)
     *                 .connectBlock(headers, TestDto.class)
     *                 .toObjectCall();
     * @return {@link Object}
     */
    @Override
    public Object toObjectCall() {
        return this.response;
    }

    /**
     * WebClient의 response를 JsonNode로 파싱하여 리턴
     * @return {@link JsonNode}
     */
    @Override
    public JsonNode toJsonNodeCall() {
        try {
            ObjectMapper om = new ObjectMapper();

            return om.readTree(this.response.toString());
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * WebClient의 response를 리턴하지 않음
     */
    @Override
    public void toVoidCall() {}
}
