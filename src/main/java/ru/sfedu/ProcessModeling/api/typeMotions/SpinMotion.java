package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class SpinMotion extends Motion {

    public SpinMotion(Actor actor, float rotateAngle) {
        super(actor);
        this.speed = rotateAngle;
    }

    @Override
    protected void move() {
        movingActor.rotate(speed);
    }
}
