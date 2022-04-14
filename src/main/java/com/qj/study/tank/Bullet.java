package com.qj.study.tank;

import java.awt.*;

/**
 * 子弹类
 */
public class Bullet {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    Rectangle rect = new Rectangle();

    private int x, y;
    private Dir dir;
    private TankFrame tf;
    private Group group = Group.BAD;

    public boolean living = true;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if(!living) {
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
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

        // update rect
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) this.living = false;



    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;

        if(rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY= tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(ex, eY , tf));
        }
    }

    private void die() {
        this.living  = false;
    }
}
