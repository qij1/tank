package com.qj.study.tank.abstractfactory;

import com.qj.study.tank.Group;

import java.awt.*;

public abstract class BaseTank {

    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);

    public abstract Group getGroup();

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}
