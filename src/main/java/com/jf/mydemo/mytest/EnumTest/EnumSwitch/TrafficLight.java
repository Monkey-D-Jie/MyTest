package com.jf.mydemo.mytest.EnumTest.EnumSwitch;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-14 13:50
 * @Description: switch 判断enum类型数据
 * To change this template use File | Settings | File and Templates.
 */

public class TrafficLight {
    private Signal color;
    public TrafficLight(){

    }
    public TrafficLight(Signal color){
        this.color = color;
    }
    public  void change(){
        switch (color){
            case RED:
                color = Signal.GREEN;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
        }
        System.out.println(color);
    }

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight(Signal.YELLOW);
        trafficLight.change();
        TrafficLight trafficLight2 = new TrafficLight(Signal.GREEN);
        trafficLight2.change();
        TrafficLight trafficLight3 = new TrafficLight(Signal.RED);
        trafficLight3.change();
    }
}
