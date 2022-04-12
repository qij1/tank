package com.qj.study.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       TankFrame tankFrame = new TankFrame();

       int initTankCount = PropertyMgr.getInt("initTankCount");

       // 初始化敌方坦克
        for(int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, tankFrame));
        }

//        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

       while (true) {
           Thread.sleep(50);
           tankFrame.repaint();
       }
    }
}
