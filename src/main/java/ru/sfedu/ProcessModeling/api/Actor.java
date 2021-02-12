package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.CONSTANT_SPEED_ACTOR;

public abstract class Actor extends RigidObject{
    private float speedX = CONSTANT_SPEED_ACTOR, speedY = CONSTANT_SPEED_ACTOR;
    private float rotation;
    private int color;

    public Actor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public float getRotation() {
        return rotation;
    }

    public int getColor() {
        return color;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void draw(Graphics g){

    }

    public void update(){

    }
}
