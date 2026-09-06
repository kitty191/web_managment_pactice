package org.example.pojo;

import lombok.Data;
import org.jspecify.annotations.NonNull;

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static @NonNull Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static @NonNull Result success(Object object) {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = object;
        return result;
    }

    public static @NonNull Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
