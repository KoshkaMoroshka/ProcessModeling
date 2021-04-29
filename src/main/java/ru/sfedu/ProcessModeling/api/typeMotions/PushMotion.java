package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class PushMotion extends Motion {

    public float weakCoef;
    private float prevSpeedX;
    private float prevSpeedY;
    private float pushSpeedX, pushSpeedY;

    public PushMotion(Actor actor, float weakCoef, float pushX, float pushY) {
        super(actor);
        this.weakCoef = weakCoef;
        pushSpeedX = pushX; pushSpeedY = pushY;
        prevSpeedX = pushX; prevSpeedY = pushY;
    }

    @Override
    protected void move() {
        super.move();
        movingActor.speedY = pushSpeedX -= prevSpeedY * weakCoef;
        movingActor.speedX = pushSpeedY -= prevSpeedX * weakCoef;
        prevSpeedX = movingActor.speedY;
        prevSpeedY = movingActor.speedY;
    }
}
