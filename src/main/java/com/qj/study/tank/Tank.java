package com.qj.study.tank;

import com.qj.study.tank.decorator.RectDecorator;
import com.qj.study.tank.decorator.TailDecorator;
import com.sun.jndi.toolkit.ctx.HeadTail;

import java.awt.*;
import java.util.Random;

/**
 * 抽象出坦克类，封装相应的熟悉和方法，供TankFrame调用
 */
public class Tank extends GameObject {
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Rectangle rect = new Rectangle();

    private Random random = new Random();

    Dir dir = Dir.DOWN;

    int oldX, oldY;

    private boolean living = true;
    private boolean moving = true;
    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        GameModel.getInstance().add(this);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if(!living) {
            GameModel.getInstance().remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        oldX = x; oldY = y;
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

    public void back() {
        x = oldX;
        y = oldY;
    }

    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if(this.y < 40) y = 40;
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2)
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if(this.y >  TankFrame.GAME_HEIGHT - Tank.HEIGHT -2)
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT -2;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
//        GameModel.getInstance().add(new RectDecorator(new TailDecorator(new Bullet(bX, bY, this.dir, this.group))));
        GameModel.getInstance().add(new Bullet(bX, bY, this.dir, this.group));
    }

    public void die() {
        this.living = false;
    }
}
