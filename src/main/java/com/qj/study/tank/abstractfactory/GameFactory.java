package com.qj.study.tank.abstractfactory;

import com.qj.study.tank.Dir;
import com.qj.study.tank.Group;
import com.qj.study.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

}
