package com.jf.mydemo.mytest.ExceptionTest.CostumeException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-06-17 16:16
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */


public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException(Object Obj) {
        super(Obj.toString());
    }

}

