package com.zeus.manage.context;

/**
 * 结果集封装类
 *
 * @Author Alex_Bao
 * @create 2017-06-15
 * create by IntelliJ IDEA
 */
public class ResultMessage {

    /**
     * 是否成功
     */
    private boolean isSuccess = true;
    /**
     * 错误信息
     */
    private String erroText = "";
    /**
     * 成功信息
     */
    private String successMsg = "";


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErroText() {
        return erroText;
    }

    public void setErroText(String erroText) {
        this.erroText = erroText;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}
