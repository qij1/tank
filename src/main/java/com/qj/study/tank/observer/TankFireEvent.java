package com.qj.study.tank.observer;

import com.qj.study.tank.Tank;

import java.io.Serializable;

public class TankFireEvent implements Serializable {
    Tank tank;

    public Tank getSource() {
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
