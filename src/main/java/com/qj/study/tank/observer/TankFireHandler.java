package com.qj.study.tank.observer;

import com.qj.study.tank.Tank;

import java.io.Serializable;

public class TankFireHandler implements TankFireObserver, Serializable {

    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank t = e.getSource();
        t.fire();
    }
}
