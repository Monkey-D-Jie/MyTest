package com.jf.mydemo.mytest.NativeTest;


public class myNativeTest {
    static
    {
        System.loadLibrary("HelloNative");
    }

    public  native void sayHello();

    @SuppressWarnings("static-access")
    public static void main(String[] args)
    {
        new myNativeTest().sayHello();
    }
}
