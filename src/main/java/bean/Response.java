package bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

@JsonPropertyOrder({"code", "msg", "data"})
@Data
@ToString
public class Response {
    int code = 0;
    String msg = "OK";
    Object data;
}
