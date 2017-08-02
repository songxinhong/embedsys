package com.example.songxinhong.hardlibrary;
/**
 * Created by songxinhong on 2017/8/1.
 */

public class HardControl {
    public static native int ledCtrl(int which, int status);//native表示这个方法不是在Java中实现的而是在C语言中实现的
    public static native int ledOpen();//static声明成静态方法可以便于调用，不必new构造出对象再调用方法，可以直接调用
    public static native void ledClose();

    static {                             //静态代码块在构造对象之前执行，并且只执行一次
        try {
            System.loadLibrary("hardcontrol");//libhardcontrol.so
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
