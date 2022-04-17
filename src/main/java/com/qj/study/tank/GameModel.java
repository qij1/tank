package com.qj.study.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank mytank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);


    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = PropertyMgr.getInt("initTankCount");
        // 初始化敌方坦克
        for(int i = 0; i < initTankCount; i++) {
            this.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, this));
        }
    }


    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌人的数量：" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);

        g.setColor(c);

        mytank.paint(g);
        // 注意这边不能用for(Bullet b : buffets), 用到迭代器，不允许for循环之外对集合修改
        // 绘制子弹
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        // 绘制敌方子弹
        for(int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        // 子弹碰撞
        for(int i = 0; i < bullets.size(); i++){
            for(int j = 0; j < tanks.size(); j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        for(int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

    }

    public Tank getMainTank() {
        return this.mytank;
    }
}
