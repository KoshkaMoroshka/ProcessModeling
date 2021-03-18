package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;

import java.awt.*;

public abstract class Actor extends Collider{


    public Actor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }


    public void draw(Graphics g){

    }
}
