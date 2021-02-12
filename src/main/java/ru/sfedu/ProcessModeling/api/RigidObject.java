package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;

public abstract class RigidObject extends Collider{
    public float normalAngle;
    public boolean isRigid = true;

    RigidObject(Simulation processing, int width, int height) {
        super(processing, width, height);
    }

    public abstract void getNormalAngle(float x, float y, float speedX, float speedY);
}
