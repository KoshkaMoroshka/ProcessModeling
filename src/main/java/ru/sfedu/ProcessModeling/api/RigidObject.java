package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;

public abstract class RigidObject{
    public float normalAngle;
    public boolean isRigid = true;

    public abstract void getNormalAngle(float x, float y, float speedX, float speedY);
}
