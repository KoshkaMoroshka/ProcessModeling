package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;

import static ru.sfedu.ProcessModeling.Constants.*;

public abstract class Collider {
    public float x, y;
    public float width = MIN_WIDTH, height = MIN_HEIGHT;
    public float scaleX = CONSTANT_SCALE_OBJECTS, scaleY = CONSTANT_SCALE_OBJECTS;
    protected final Simulation processing;

    public float points[][];

    Collider(Simulation processing, int width, int height){
        this.processing = processing;
        this.width = width;
        this.height = height;
        points = getPoints(width, height);
    }

    public abstract boolean pointBelongToArea(float x, float y);
    public boolean collision(Collider collider){
        if(this == collider)
            return false;
        for (int i=0; i<points[0].length; i++)
            if(collider.pointBelongToArea(points[0][i] + x,points[1][i] + y))
                return true;
        return false;
    };
    public abstract float[][] getPoints(float width, float height);

}
