package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class RoundMotion extends Motion {

    public float pointX, pointY;

    public RoundMotion(Actor actor, float x, float y) {
        super(actor);
        pointX = x;
        pointY = y;
    }

    @Override
    protected void move() {
        float x1 = movingActor.x + movingActor.centerX - pointX;
        float y1 = movingActor.y + movingActor.centerY - pointY;
        float cosRotation, sinRotation;
        cosRotation = (float) Math.cos(speed / 3.14);
        sinRotation = (float) Math.sin(speed / 3.14);
        float x2 = x1 * cosRotation - y1 * sinRotation;
        float y2 = y1 * cosRotation + x1 * sinRotation;
        movingActor.speedX += x2 - x1;
        movingActor.speedY += y2 - y1;
    }
}
