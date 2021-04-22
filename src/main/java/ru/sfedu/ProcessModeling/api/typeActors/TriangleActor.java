package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.MIN_WIDTH;

public class TriangleActor extends Actor {

    float[] masCornerX, masCornerY;

    public TriangleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.translate(centerX + x, centerY + y);
        graphics2D.rotate(rotation);
        graphics2D.setColor(Color.RED);
        int xm[] = {(int)(masCornerX[0]), (int)(masCornerX[1]), (int)(masCornerX[2])};
        int ym[] = {(int)(masCornerY[0]), (int)(masCornerY[1]), (int)(masCornerY[2])};
        graphics2D.fillPolygon(xm,ym,3);
        graphics2D.rotate(-rotation);
        graphics2D.setColor(Color.blue);
        for (int i = 0; i<points[0].length; i++){
            graphics2D.fillOval((int)points[0][i], (int)points[1][i], 4,4);
        }
        graphics2D.translate(-(centerX+x), -(centerY + y));
    }

    private boolean sameSide(float x, float y, float x1, float y1, float x2, float y2, float x3, float y3){
        return ((x-x1)*(y2-y1)-(y-y1)*(x2-x1))*((x3-x1)*(y2-y1)-(y3-y1)*(x2-x1))>=0;
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        float cosRotation = (float) Math.cos(rotation);
        float sinRotation = (float) Math.sin(rotation);
        float rx = -(x - centerX - this.x);
        float ry = -(y - centerY - this.y);
        float dx = rx * cosRotation - ry * sinRotation;
        float dy = ry * cosRotation + rx * sinRotation;
        return sameSide(dx, dy, masCornerX[0], masCornerY[0], masCornerX[1], masCornerY[1], masCornerX[2],masCornerY[2]) &&
                sameSide(dx, dy, masCornerX[1], masCornerY[1], masCornerX[2], masCornerY[2], masCornerX[0],masCornerY[0]) &&
                sameSide(dx, dy, masCornerX[2], masCornerY[2], masCornerX[0], masCornerY[0], masCornerX[1],masCornerY[1]);
    }

    @Override
    public float[][] getPoints(float width, float height) {
        int countPointsEdge = (int)((Math.sqrt((width/2)*(width/2) + height*height))/MIN_WIDTH), countPointsGrounds = (int)(height/MIN_WIDTH);
        float[][] points = new float[2][countPointsEdge*2 + countPointsGrounds + 3];
        masCornerX = new float[3];
        masCornerY = new float[3];
        int i = 1;

        centerX = (width + 0 + width/2)/3;
        centerY = (height + height + 0)/3;

        points[0][0] = 0;
        points[1][0] = height;
        masCornerX[0] = points[0][i-1];
        masCornerY[0] = points[1][i-1];
        for (int k =1; k <= countPointsGrounds+1; k++, i++){
            points[0][i] = points[0][i-1] + width/(countPointsGrounds+1);
            points[1][i] = points[1][i-1];
        }
        masCornerX[1] = points[0][i-1];
        masCornerY[1] = points[1][i-1];
        for (int k = 1; k <= countPointsEdge+1; k++, i++){
            points[0][i] = points[0][i-1] - (width/2) / (countPointsEdge+1);
            points[1][i] = points[1][i-1] - height / (countPointsEdge+1);
        }
        masCornerX[2] = points[0][i-1];
        masCornerY[2] = points[1][i-1];
        for (int k = 1; k < countPointsEdge+1; k++, i++){
            points[0][i] = points[0][i-1] - (width/2) / (countPointsEdge+1);
            points[1][i] = points[1][i-1] + height / (countPointsEdge+1);
        }

        return points;
    }

}
