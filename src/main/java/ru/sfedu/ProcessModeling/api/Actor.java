package ru.sfedu.ProcessModeling.api;

import java.awt.*;

public abstract class Actor extends Collider {

    public Actor(Simulation processing, float x, float y, int width, int height) {
        super(processing, width, height);
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){

    }
    public void start(){

    }
    public void stop(){

    }
}
