package ru.sfedu.ProcessModeling.api;

public abstract class Motion {

    protected Actor movingActor;
    boolean active = true;
    protected float speed = 0.5f;

    public Motion (Actor actor){
        movingActor = actor;
    }

    protected void move() {

    };



}
