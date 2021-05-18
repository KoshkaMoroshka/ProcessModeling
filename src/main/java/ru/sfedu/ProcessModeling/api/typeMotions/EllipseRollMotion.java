package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class EllipseRollMotion extends Motion {

    float h;
    float phase = 0;

    public EllipseRollMotion(Actor actor) {
        super(actor);
        h = actor.height;
    }

    //This motion doesn't work :(
    @Override
    protected void move() {
        float cos = (float) Math.cos(phase/2);
        movingActor.speedX += speed * cos * cos;
        float sin = (float) Math.sin(phase);
        h = sin * 2 * (float) Math.sqrt(movingActor.width + movingActor.height) * (float) Math.sin(speed/3.14f);
        phase += speed / 3.14;
        movingActor.rotate((float) (speed/3.14));
        movingActor.speedY += h - this.h;
        this.h = h;
    }
}
