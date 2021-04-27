package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class GravityMotion extends Motion {

    private float prevSpeed = 0;
    private float prevY, prevPrevY;
    private float damping = 0.4f;

    public GravityMotion(Actor actor) {
        super(actor);
    }

    @Override
    protected void move() {
        super.move();
        if (prevY == movingActor.y && prevSpeed != 0){
            prevSpeed *= -0.5 * damping;
            movingActor.y = prevPrevY;
        }
        prevPrevY = prevY;
        prevY = movingActor.y;
        prevSpeed = movingActor.speedY = prevSpeed + speed;
    }
}
