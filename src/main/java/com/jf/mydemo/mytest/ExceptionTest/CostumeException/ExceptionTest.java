package com.jf.mydemo.mytest.ExceptionTest.CostumeException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-06-17 16:16
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class ExceptionTest {
    public static void main(String args[]) {
        Object user = null;
        if(user == null){
            throw new BusinessException(ErrorCode.NULL_OBJ);
        }
    }
}
