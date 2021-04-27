package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Simulation;

public class BoxCollider extends Actor {

    public BoxCollider(Simulation processing, float x, float y, int width, int height) {
        super(processing, x, y, width, height);
        inert = false;
        bounce = false;
    }
    @Override
    public boolean pointBelongToArea(float x, float y){
        float cosRotation = (float) Math.cos(rotation);
        float sinRotation = (float) Math.sin(rotation);
        float rx = -(x - centerX - this.x);
        float ry = -(y - centerY - this.y);
        float dx = rx * cosRotation - ry * sinRotation;
        float dy = ry * cosRotation + rx * sinRotation;
        return (dx <= -width/2 || dx >= width/2) || (dy <= -height/2 || dy >= height/2);
    }

    @Override
    public float[][] getPoints(float width, float height) {
        centerX = width/2;
        centerY = height/2;
        points = new float[2][4];
        points[0][0] = -width/2;
        points[0][1] = width/2;
        points[0][2] = width/2;
        points[0][3] = -width/2;
        points[1][0] = -height/2;
        points[1][1] = -height/2;
        points[1][2] = height/2;
        points[1][3] = height/2;
        return points;
    }
}
