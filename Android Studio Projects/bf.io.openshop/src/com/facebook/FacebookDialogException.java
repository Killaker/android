package com.facebook;

public class FacebookDialogException extends FacebookException
{
    static final long serialVersionUID = 1L;
    private int errorCode;
    private String failingUrl;
    
    public FacebookDialogException(final String s, final int errorCode, final String failingUrl) {
        super(s);
        this.errorCode = errorCode;
        this.failingUrl = failingUrl;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public String getFailingUrl() {
        return this.failingUrl;
    }
    
    @Override
    public final String toString() {
        return "{FacebookDialogException: " + "errorCode: " + this.getErrorCode() + ", message: " + this.getMessage() + ", url: " + this.getFailingUrl() + "}";
    }
}
