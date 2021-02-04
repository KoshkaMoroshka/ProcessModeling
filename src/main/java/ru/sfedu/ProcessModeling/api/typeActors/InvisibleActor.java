package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Collider;

public class InvisibleActor extends Actor {

    public InvisibleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        return false;
    }

    @Override
    public float[][] getPoints(float width, float height) {
        return new float[0][];
    }

}
