package com.blueprint.exception;

/**
 * @Author: ZiJie.Yip
 * @Description:系统异常类
 * @date: 2018/11/23 16:44
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 7683291063937843594L;

    private String retCd;
    private String msgDes;

    public SysException(Exception e) {
        super(e);
    }

    public SysException(String message) {
        super(message);
        msgDes = message;
    }

    public SysException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public SysException(String message,Exception e){
        super(e);
        this.msgDes = message;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }

}
