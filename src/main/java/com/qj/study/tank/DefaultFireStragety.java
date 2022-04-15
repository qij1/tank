package com.qj.study.tank;

import com.qj.study.tank.abstractfactory.BaseTank;

public class DefaultFireStragety implements FireStragety{
    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        new Bullet(bX, bY, t.dir, t.group, t.tf);
        if(t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
