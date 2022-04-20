package com.qj.study.tank.cor;

import com.qj.study.tank.*;

public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b  = (Bullet) o1;
            Tank d = (Tank) o2;

            if(b.getGroup() == d.getGroup()) return true;
            if(b.rect.intersects(d.rect)) {
                b.die();
                d.die();
                int ex = d.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY= d.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
                new Explode(ex, eY);
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
           return collide(o2, o1);
        }

        return true;
    }

}
