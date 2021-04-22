package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class BoxMotion extends Motion {

    float x1, x2, y1, y2;

    public BoxMotion(Actor actor) {
        super(actor);
    }

    @Override
    protected void move() {
        super.move();
    }
}
