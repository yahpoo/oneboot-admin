package xyz.yahpoo.oneboot.common.response;


import lombok.Getter;

@Getter
public enum  RTCode {

    SUCCESS("0000", "请求成功"),

    ;

    private String code;
    private String msg;

    RTCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
