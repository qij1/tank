package com.qj.study.tank.cor;

import com.qj.study.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
