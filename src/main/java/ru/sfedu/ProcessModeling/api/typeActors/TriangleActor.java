package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.MIN_WIDTH;

public class TriangleActor extends Actor {

    float[] masCornerX, masCornerY;
    float[] normalAngles;

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
        int xm[] = {(int)(masCornerX[0] - centerX), (int)(masCornerX[1] - centerX), (int)(masCornerX[2] - centerX)};
        int ym[] = {(int)(masCornerY[0] - centerY), (int)(masCornerY[1] - centerY), (int)(masCornerY[2] - centerY)};
        graphics2D.fillPolygon(xm,ym,3);
        graphics2D.rotate(-rotation);
        graphics2D.translate(-(centerX+x), -(centerY + y));
    }

    private boolean sameSide(float x, float y, float x1, float y1, float x2, float y2, float x3, float y3){
        return ((x-x1)*(y2-y1)-(y-y1)*(x2-x1))*((x3-x1)*(y2-y1)-(y3-y1)*(x2-x1))>=0;
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        return sameSide(x, y, this.x, this.y+height, this.x+width, this.y +height, this.x+width/2,this.y) &&
               sameSide(x, y, this.x+width, this.y+height, this.x+width/2, this.y, this.x,this.y+height) &&
               sameSide(x, y, this.x+width/2, this.y, this.x, this.y +height, this.x+width,this.y+height);
    }

    @Override
    public float[][] getPoints(float width, float height) {
        int countPointsEdge = (int)((Math.sqrt((width/2)*(width/2) + height*height))/MIN_WIDTH), countPointsGrounds = (int)(height/MIN_WIDTH);
        float[][] points = new float[2][countPointsEdge*2 + countPointsGrounds + 3];
        masCornerX = new float[3];
        masCornerY = new float[3];
        normalAngles = new float[3];
        int i = 1;

        centerX = (width + 0 + width/2)/3;
        centerY = (height + height + 0)/3;

        points[0][0] = 0;
        points[1][0] = height;
        for (int k =1; k < countPointsGrounds+1; k++, i++){
            points[0][i] = points[0][i-1] + width/countPointsGrounds;
            points[1][i] = points[1][i-1];
        }
        masCornerX[1] = points[0][i-1];
        masCornerY[1] = points[1][i-1];
        for (int k = 1; k < countPointsEdge+1; k++, i++){
            points[0][i] = points[0][i-1] - (width/2) / countPointsEdge;
            points[1][i] = points[0][i-1] - height / countPointsEdge;
        }
        masCornerX[2] = points[0][i-1];
        masCornerY[2] = points[1][i-1];
        for (int k = 1; k< countPointsEdge+1; k++, i++){
            points[0][i] = points[0][i-1] - (width/2) / countPointsEdge;
            points[1][i] = points[1][i-1] + height / countPointsEdge;
        }
        masCornerX[0] = points[0][i-1];
        masCornerY[0] = points[1][i-1];
        normalAngles[0] = (float)Math.atan(((masCornerX[1] - masCornerX[0]))/(masCornerY[1] - masCornerY[0]));
        normalAngles[1] = (float)Math.atan(((masCornerX[2] - masCornerX[1]))/(masCornerY[2] - masCornerY[1]));
        normalAngles[2] = (float)Math.atan(((masCornerX[0] - masCornerX[2]))/(masCornerY[0] - masCornerY[2]));
        return points;
    }

    @Override
    public float getNormalAngle(float x, float y, float speedX, float speedY) {
        return defaultNormalAngle;
    }
}
