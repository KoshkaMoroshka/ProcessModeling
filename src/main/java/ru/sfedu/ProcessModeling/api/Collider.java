package ru.sfedu.ProcessModeling.api;


public abstract class Collider extends RigidObject{

    Collider(Simulation processing, int width, int height){
        this.processing = processing;
        this.width = width;
        this.height = height;
        points = getPoints(width, height);
        resolveRect();
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

    public float getSpeedX() {
        return speedX;
    }
    public float getSpeedY() {
        return speedY;
    }
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
