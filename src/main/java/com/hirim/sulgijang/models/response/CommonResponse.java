package com.hirim.sulgijang.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private int status;
    private String message;
    private Object data;

    public CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static CommonResponse fail(String message) {
        return new CommonResponse(ResultStatus.FAIL.getValue(), message);
    }

    public static CommonResponse success(String message) {
        return new CommonResponse(ResultStatus.SUCCESS.getValue(), message);
    }

    public static CommonResponse successObject(Object object) {
        return new CommonResponse(ResultStatus.SUCCESS.getValue(), "success", object);
    }

    public static CommonResponse success(String message, Object object) {
        return new CommonResponse(ResultStatus.SUCCESS.getValue(), message, object);
    }
}
