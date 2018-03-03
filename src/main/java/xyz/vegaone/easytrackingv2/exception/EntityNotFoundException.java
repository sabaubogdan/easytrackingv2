package xyz.vegaone.easytrackingv2.exception;

public class EntityNotFoundException extends RuntimeException {

    private String errCode;

    private String errMsg;

    public EntityNotFoundException(String errMsg) {
        this.errMsg = errMsg;
    }

    public EntityNotFoundException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
