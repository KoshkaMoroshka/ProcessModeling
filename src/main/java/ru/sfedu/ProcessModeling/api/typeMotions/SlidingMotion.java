package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class SlidingMotion extends Motion {

    public float weakCoef = 0;
    private float prevSpeedX = 0;
    private float prevSpeedY = 0;

    public SlidingMotion(Actor actor, float weakCoef) {
        super(actor);
        this.weakCoef = weakCoef;
    }

    @Override
    protected void move() {
        super.move();

        prevSpeedY = movingActor.speedY -= prevSpeedY * weakCoef;
        prevSpeedX = movingActor.speedX -= prevSpeedX * weakCoef;
    }
}
