package ru.sfedu.ProcessModeling.api;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.*;
import static ru.sfedu.ProcessModeling.Constants.CONSTANT_SPEED_ACTOR;

public abstract class RigidObject{

    public boolean rigid = true;
    public boolean inert = true;
    public boolean bounce = true;
    public float x, y;
    public float prevPointX , prevPointY;
    public float width = MIN_WIDTH, height = MIN_HEIGHT;
    protected Simulation processing;
    public float speedX = CONSTANT_SPEED_ACTOR, speedY = CONSTANT_SPEED_ACTOR;
    public float weight = 1f;
    public Color color;
    public float centerX;
    public float centerY;
    protected float rotation = 0;
    public float points[][];

    public void update(){
        prevPointX = x;
        prevPointY = y;
        x+=speedX;
        y+=speedY;
    }

    float maxX, maxY, minX, minY;

    public void resolveRect(){
        maxX = points[0][0]; maxY = points[1][0]; minX = maxX; minY = maxY;

        for(int i = 1; i<points[0].length; i++){
            if (maxX < points[0][i])
                maxX = points[0][i];
            if (maxY < points[1][i])
                maxY = points[1][i];
            if (minX > points[0][i])
                minX = points[0][i];
            if (minY > points[1][i])
                minY = points[1][i];
        }
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

    public boolean onCollision(Collider collider){
        if(!this.rigid || !collider.rigid)
            return false;

        float signX = (speedX - collider.speedX) > 0 ? -1 : 1;
        float pX = speedX * weight + collider.speedX * collider.weight;
        float energyX = speedX * speedX * weight + collider.speedX * collider.speedX * collider.weight;
        float disX = signX * (float) Math.sqrt(weight * collider.weight * energyX * (weight + collider.weight) - weight * collider.weight * pX * pX);
        float speedX1 = (weight * pX + disX) / weight / (weight + collider.weight);
        float speedX2 = (collider.weight * pX - disX) / collider.weight / (weight + collider.weight);

        float signY = (speedY - collider.speedY) > 0 ? -1 : 1;
        float pY = speedY * weight + collider.speedY * collider.weight;
        float energyY = speedY * speedY * weight + collider.speedY * collider.speedY * collider.weight;
        float disY = signY * (float) Math.sqrt(weight*collider.weight*energyY*(weight+collider.weight)-weight*collider.weight*pY*pY);
        float speedY1 =  (weight*pY + disY)/ weight/(weight+collider.weight);
        float speedY2 = (collider.weight*pY - disY)/ collider.weight/(weight+collider.weight);

        if (!bounce || !collider.bounce){
            speedX = 0; speedY = 0;
            collider.speedX = 0; collider.speedY = 0;
            return false;
        }
        if (inert){
            if(collider.inert){
                speedX = speedX1; speedY = speedY1;
            } else {
                speedX = collider.speedX - speedX;
                speedY = collider.speedY - speedY;
            }
        }
        if(collider.inert){
            if (inert) {
                collider.speedX = speedX2; collider.speedY = speedY2;
            } else {
                collider.speedX = speedX - collider.speedX;
                collider.speedY = speedY - collider.speedY;
            }
        }
        return false;
    }

    public float rotate(float rotation){
        this.rotation += rotation;
        float cosRotation = (float) Math.cos(rotation);
        float sinRotation = (float) Math.sin(rotation);
        for(int i=0; i<points[0].length; i++){
            float x = points[0][i];
            float y = points[1][i];
            points[0][i] = x * cosRotation - y * sinRotation;
            points[1][i] = y * cosRotation + x * sinRotation;
        }
        resolveRect();
        return this.rotation;
    }
}
