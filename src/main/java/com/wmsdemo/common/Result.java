package com.wmsdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private int code;
    private String msg;
    private Long total;
    private Object data;

    public static Result failed() {
        return new Result(400, "失败", 0L, null);
    }

    public static Result success() {
        return new Result(200, "成功", 0L, null);
    }

    public static Result success(Object data) {
        return new Result(200, "成功", 0L, data);
    }

    public static Result success(Object data, Long total) {
        return new Result(200, "成功", total, data);
    }
}
