package com.qj.study.tank.abstractfactory;

import com.qj.study.tank.Audio;
import com.qj.study.tank.Dir;
import com.qj.study.tank.ResourceMgr;
import com.qj.study.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private Dir dir;
    private TankFrame tf;

    public boolean living = true;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        Color c  = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10 * step, 10*step);
        step++;
        if(step >= 5) tf.explodes.remove(this);
        g.setColor(c);
    }

    private void die() {
        this.living  = false;
    }
}
