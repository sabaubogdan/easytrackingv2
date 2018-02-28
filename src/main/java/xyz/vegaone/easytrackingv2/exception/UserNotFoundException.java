package xyz.vegaone.easytrackingv2.exception;

public class UserNotFoundException extends RuntimeException {

    private String errCode;

    private String errMsg;

    public UserNotFoundException(String errMsg) {
        this.errMsg = errMsg;
    }

    public UserNotFoundException(String errCode, String errMsg) {
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
