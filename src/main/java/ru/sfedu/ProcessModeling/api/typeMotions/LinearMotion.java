package ru.sfedu.ProcessModeling.api.typeMotions;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Motion;

public class LinearMotion extends Motion {

    private float x, y;

    public LinearMotion(Actor actor, float x, float y){
        super(actor);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void move() {
        super.move();
        float dx = (x - (movingActor.centerX + movingActor.x));
        float dy = (y - (movingActor.centerY + movingActor.y));
        float r = (float)Math.sqrt(dx*dx + dy*dy);
        if(r <= speed){
            movingActor.speedX += dx;
            movingActor.speedY += dy;
            return;
        }
        movingActor.speedX += speed * (dx / r);
        movingActor.speedY += speed * (dy / r);
    }

}
