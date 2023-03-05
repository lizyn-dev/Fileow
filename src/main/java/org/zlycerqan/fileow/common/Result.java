package org.zlycerqan.fileow.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result {
    private Integer code;

    private String message;

    private Object data;

    public static Result ok(Object data) {
        return new Result().setCode(200).setMessage("OK").setData(data);
    }

}
