package com.qj.study.tank;

import com.qj.study.tank.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank mytank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
    public List<BaseBullet> bullets = new ArrayList<>();
    public List<BaseTank> tanks = new ArrayList<>();
    public List<BaseExplode> explodes = new ArrayList<>();

    public GameFactory gf = new RectFactory();

    public static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");

    public TankFrame(){
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    // update方法在paint前被调用
    @Override
    public void update(Graphics g) {
        // 目的：消除闪屏。
        // 在内存里创建一样大小的画布，获取到画布的画笔（g）,将画笔传给系统paint方法，
        // 让系统直接画到内存的画布上，然后用系统的画笔将内存中画好的画布一次性刷到屏幕上
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
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



        // 这种写法也行
//        for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//            Bullet b = it.next();
//            if(!b.live) it.remove();
//        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            // 确定按键的状态
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            // 根据按键状态确定坦克的方向
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    mytank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL && !bU && !bR && !bD) {
                mytank.setMoving(false);
            } else {
                mytank.setMoving(true);
                if(bL) mytank.setDir(Dir.LEFT);
                if(bU) mytank.setDir(Dir.UP);
                if(bR) mytank.setDir(Dir.RIGHT);
                if(bD) mytank.setDir(Dir.DOWN);
            }
        }
    }
}
