package cn.battlehawk233.barbershop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"code", "msg", "data"})
public class JsonResponse<T> implements IJsonResponse{
    int code = 200;
    String msg = "OK";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    public JsonResponse(T data) {
        this.data = data;
    }

    public static <T> JsonResponse<T> OK_WITH_DATA(T data) {
        return new JsonResponse<T>(data);
    }
    public static IJsonResponse OK() {
        return new JsonResponse<>(null);
    }

    public static JsonResponse<Object> FAIL_WITH_MESSAGE(int code, String msg) {
        var res = new JsonResponse<>(null);
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
}
