package com.qj.study.tank.observer;

import com.qj.study.tank.Tank;

public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank t = e.getSource();
        t.fire();
    }
}
