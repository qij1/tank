package com.qj.study.tank.abstractfactory;

import com.qj.study.tank.*;

import java.awt.*;
import java.util.Random;

/**
 * 抽象出坦克类，封装相应的熟悉和方法，供TankFrame调用
 */
public class RectTank extends BaseTank {
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Rectangle rect = new Rectangle();

    private Random random = new Random();

    Dir dir = Dir.DOWN;

    int x = 200, y = 200;

    TankFrame tf;
    private boolean living = true;
    private boolean moving = true;
    Group group = Group.BAD;

    FireStragety fs;

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        if(group == Group.GOOD) {
            String goodFsName = PropertyMgr.getString("goodFs");
            try {
                fs = (FireStragety) Class.forName(goodFsName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else fs = new DefaultFireStragety();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public RectTank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if(!living) {
            tf.tanks.remove(this);
            return;
        }
        Color color = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        g.fillRect(x, y, 40, 40);
        g.setColor(color);
        move();
    }

    private void move() {
        if(!moving) return;
        // 根据坦克的方向进行移动
        switch (dir) {
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            default:
                break;
        }

        if(this.group == Group.BAD && random.nextInt(100) > 95) this.fire();
        if(this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();

        boundsCheck();

        // update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if(this.y < 40) y = 40;
        if(this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2) x = TankFrame.WIDTH - RectTank.WIDTH - 2;
        if(this.y >  TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2) y = TankFrame.HEIGHT - RectTank.HEIGHT -2;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
//        fs.fire(this);
        int bX = this.x + RectTank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + RectTank.HEIGHT/2 - Bullet.HEIGHT/2;
        this.tf.gf.createBullet(bX, bY, this.dir, this.group, this.tf);
        if(this.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }
}
