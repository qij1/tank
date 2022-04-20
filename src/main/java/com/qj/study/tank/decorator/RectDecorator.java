package com.qj.study.tank.decorator;

import com.qj.study.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator {


    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(go.x, go.y, getWidth(), getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth() + 2;
    }

    @Override
    public int getHeight() {
        return super.go.getHeight() + 2;
    }
}
