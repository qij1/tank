package com.qj.study.tank;

import com.qj.study.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank mytank;

    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }


//    java.util.List<Bullet> bullets = new ArrayList<>();
//    java.util.List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
//    Collider collider = new BulletTankCollider();
//    Collider collider2 = new TankTankCollider();
    ColliderChain chain = new ColliderChain();
    private List<GameObject> objects = new ArrayList<>();

    private GameModel() {}

    private void init() {
        // 初始化主站坦克
        mytank = new Tank(200, 400, Dir.DOWN, Group.GOOD);
        int initTankCount = PropertyMgr.getInt("initTankCount");
        // 初始化敌方坦克
        for(int i = 0; i < initTankCount; i++) {
            new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD);
        }

        // 初始化墙
        new Wall(150, 150, 200, 50);
        new Wall(550, 150, 200, 50);
        new Wall(300, 300, 50, 200);
        new Wall(550, 300, 50, 200);
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
//        g.drawString("敌人的数量：" + tanks.size(), 10, 80);
//        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(c);

        mytank.paint(g);

        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // 互相碰撞
        for(int i = 0; i < objects.size(); i++) {
            for(int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
//                collider.collide(o1, o2);
//                collider2.collide(o1, o2);
                chain.collide(o1, o2);
            }
        }


        // 注意这边不能用for(Bullet b : buffets), 用到迭代器，不允许for循环之外对集合修改
        // 绘制子弹
//        for(int i = 0; i < bullets.size(); i++) {
//            bullets.get(i).paint(g);
//        }
//        // 绘制敌方子弹
//        for(int i = 0; i < tanks.size(); i++) {
//            tanks.get(i).paint(g);
//        }
        // 子弹碰撞
//        for(int i = 0; i < bullets.size(); i++){
//            for(int j = 0; j < tanks.size(); j++){
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }
//        for(int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }

    }

    public Tank getMainTank() {
        return this.mytank;
    }
}
