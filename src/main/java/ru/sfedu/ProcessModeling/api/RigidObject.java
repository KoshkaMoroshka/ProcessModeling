package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;

import static ru.sfedu.ProcessModeling.Constants.*;
import static ru.sfedu.ProcessModeling.Constants.CONSTANT_SPEED_ACTOR;

public abstract class RigidObject{

    public boolean isRigid = true;
    public boolean inert = true;
    public float x, y;
    public float prevPointX , prevPointY;
    public float width = MIN_WIDTH, height = MIN_HEIGHT;
    public float scaleX = CONSTANT_SCALE_OBJECTS, scaleY = CONSTANT_SCALE_OBJECTS;
    protected Simulation processing;
    public float speedX = CONSTANT_SPEED_ACTOR, speedY = CONSTANT_SPEED_ACTOR;
    public static final float defaultNormalAngle = 2 * (float)Math.PI;

    public abstract float getNormalAngle(float x, float y, float speedX, float speedY);

    public void update(){
        prevPointX = x;
        prevPointY = y;
        x+=speedX;
        y+=speedY;
        if(x + width>= processing.width || x <= 0)
            speedX*=-1;
        if(y + height>= processing.height || y <= 0)
            speedY*=-1;
    }

    protected boolean lineCrossing(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4){
        float k1, k2, x5, y5;
        if(x1 == x2)
            x2+=0.001f;
        k1 = (y2-y1)/(x2-x1);
        k2 = (y4-y3)/(x4-x3);
        if(k1 == k2)
            return false;
        if (x1 > x2) {
            float buf = x1;
            x1 = x2;
            x2 = buf;
        }
        if (x3 > x4) {
            float buf = x3;
            x3 = x4;
            x4 = buf;
        }
        if (y1 > y2) {
            float buf = y1;
            y1 = y2;
            y2 = buf;
        }
        if (y3 > y4) {
            float buf = y3;
            y3 = y4;
            y4 = buf;
        }
        x5 = (y3 - y1 + k1 * x1 - k2*x3)/(k1-k2);
        y5 = k2 * (x5-x3) + y3;
        return (x5 <= x2 && x5 >= x1 && y5 <= y2 && y5 >= y1);
    }
    protected boolean onCollision(Collider collider, float angle){
        if(!this.isRigid || !collider.isRigid)
            return false;
        float deltaSpeedX = (speedX - collider.speedX);
        float deltaSpeedY = (speedY - collider.speedY) ;
        //angle += Math.atan(deltaSpeedY/deltaSpeedX);
        float cosAngle = (float) Math.cos(angle);
        float sinAngle = (float) Math.sin(angle);
        speedX +=deltaSpeedX * cosAngle * cosAngle;
        speedY +=deltaSpeedY * cosAngle * sinAngle;
        collider.speedX -= deltaSpeedX * sinAngle * sinAngle;
        collider.speedY -= deltaSpeedY * sinAngle * cosAngle;
        return true;
    }
}
