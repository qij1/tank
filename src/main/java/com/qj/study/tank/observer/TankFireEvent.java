package com.qj.study.tank.observer;

import com.qj.study.tank.Tank;

public class TankFireEvent {
    Tank tank;

    public Tank getSource() {
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
