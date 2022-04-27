package com.qj.study.tank.cor;

import com.qj.study.tank.GameObject;

import java.io.Serializable;

public interface Collider extends Serializable {
    boolean collide(GameObject o1, GameObject o2);
}
