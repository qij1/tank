package com.qj.study.tank.abstractfactory;

import com.qj.study.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);
}
