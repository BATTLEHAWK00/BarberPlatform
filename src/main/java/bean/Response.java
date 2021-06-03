package bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

@JsonPropertyOrder({"code", "msg", "data"})
@Data
@ToString
public class Response {
    int code = 200;
    String msg = "OK";
    Object data;

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(Object data) {
        this.data = data;
    }

    public Response() {
    }
}
