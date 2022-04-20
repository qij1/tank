package com.qj.study.tank.cor;

import com.qj.study.tank.Bullet;
import com.qj.study.tank.GameObject;
import com.qj.study.tank.Tank;
import com.qj.study.tank.Wall;

public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall) {
            Tank t  = (Tank) o1;
            Wall w = (Wall) o2;
            if(t.rect.intersects(w.rect)) {
                t.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
           return collide(o2, o1);
        }

        return true;
    }

}
