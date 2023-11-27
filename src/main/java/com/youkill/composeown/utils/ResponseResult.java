package com.youkill.composeown.utils;

import lombok.Data;

@Data
public class ResponseResult {
    int code;
    String event;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", event='" + event + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public ResponseResult(int code, String event, String message) {
        this.code = code;
        this.event = event;
        this.message = message;
    }
}
