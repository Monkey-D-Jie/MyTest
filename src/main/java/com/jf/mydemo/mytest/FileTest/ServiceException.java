package com.jf.mydemo.mytest.FileTest;

/**
 * 自定义业务异常
 *
 * @author FCG
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1377442113857414950L;
    /**
     * 异常代码
     */
    private String exCode = "400";

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, String exCode) {
        super(message);
        this.exCode = exCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, String exCode, Throwable cause) {
        super(message, cause);
        this.exCode = exCode;
    }

    public String getExCode() {
        return exCode;
    }

}
