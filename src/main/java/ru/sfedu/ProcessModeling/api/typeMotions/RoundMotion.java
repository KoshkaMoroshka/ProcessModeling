package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class RoundMotion extends Motion {

    public float r;
    public float pointX, pointY;

    public RoundMotion(Actor actor, float x, float y) {
        super(actor);
        pointX = x;
        pointY = y;
    }

    @Override
    protected void move() {
        float dx = pointX - (movingActor.x + movingActor.centerX);
        float dy = pointY - (movingActor.y + movingActor.centerY);
        r = (float) Math.sqrt(dx * dx + dy * dy);
        movingActor.speedX = speed * (dy/r);
        movingActor.speedY = -speed * (dx/r);
    }
}
