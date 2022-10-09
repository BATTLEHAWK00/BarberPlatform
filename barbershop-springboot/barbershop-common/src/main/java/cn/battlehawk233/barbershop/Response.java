package cn.battlehawk233.barbershop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"code", "msg", "data"})
public class Response<T> {
    int code = 200;
    String msg = "OK";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    public Response(T data) {
        this.data = data;
    }

    public static <T> Response<T> OK_WITH_DATA(T data) {
        return new Response<T>(data);
    }

    public static Response<Object> FAIL_WITH_MESSAGE(int code, String msg) {
        var res = new Response<>(null);
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
}