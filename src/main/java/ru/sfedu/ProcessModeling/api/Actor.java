package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;

import java.awt.*;

public abstract class Actor extends Collider{

    private float rotation;
    private int color;

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Actor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }


    public void draw(Graphics g){

    }
}
