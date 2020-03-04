package com.luoxd.graduation_project.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jackson 格式化 base 类
 * @author
 * @Title: BaseResult.java
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {

    @JsonProperty("resultCode")
    private int resultCode = 0;

    @JsonProperty("resultMessage")
    private String resultMessage = "";

    @JsonProperty("result")
    private T result;

    public BaseResult(T t) {
        this.result = t;
    }

    public BaseResult(int errCode, String errMsg) {
        this.resultCode = errCode;
        this.resultMessage = errMsg;
    }

    public static <T> BaseResult success(T t) {
        return new BaseResult(0, "", t);
    }

    public static <T> BaseResult error(int errCode, String errMsg, T t) {
        return new BaseResult(errCode, errMsg, t);
    }

    public static BaseResult error(int errCode, String errMsg) {
        return new BaseResult(errCode, errMsg, null);
    }

    public static BaseResult error(String errMsg) {
        return new BaseResult(-1, errMsg);
    }

}