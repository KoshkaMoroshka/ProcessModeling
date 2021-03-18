package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import static ru.sfedu.ProcessModeling.Constants.*;

import java.awt.*;


public class RectangleActor extends Actor {

    float masCornerX[], masCornerY[];
    float[] normalAngles;

    public RectangleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(this.color);
        int[]   bufX = {(int)(masCornerX[0] + x), (int)(masCornerX[1] + x),(int)(masCornerX[2] + x),(int)(masCornerX[3] + x)},
                bufY = {(int)(masCornerY[0] + y), (int)(masCornerY[1] + y),(int)(masCornerY[2] + y),(int)(masCornerY[3] + y)};
        g.fillPolygon(bufX, bufY, 4);
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        return  ((x >= this.x &&  x <= this.x+width ) && ( y >= this.y && y <= this.y+height));
    }

    @Override
    public float[][] getPoints(float width, float height) {
        int countPointsWidth = (int)(width/MIN_WIDTH), countPointsHeight = (int)(height/MIN_HEIGHT);
        float[][] points = new float[2][countPointsWidth*2 + countPointsHeight*2 + 4];
        int i = 1;

        masCornerX = new float[4];
        masCornerY = new float[4];
        normalAngles = new float[4];

        this.centerX = width/2;
        this.centerY = height/2;

        points[0][0] = 0;
        points[1][0] = 0;

        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] + width/countPointsWidth;
            points[1][i] = points[1][i-1];
        }
        masCornerX[1] = points[0][i-1];
        masCornerY[1] = points[1][i-1];
        for(int k=1; k<countPointsHeight+1; k++, i++)
        {
            points[0][i] = points[0][i-1];
            points[1][i] = points[1][i-1] + height/countPointsHeight;
        }
        masCornerX[2] = points[0][i-1];
        masCornerY[2] = points[1][i-1];
        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] - width/countPointsWidth;
            points[1][i] = points[1][i-1];
        }
        masCornerX[3] = points[0][i-1];
        masCornerY[3] = points[1][i-1];
        for(int k=1; k<countPointsHeight+1; k++, i++)
        {
            points[0][i] = points[0][i-1];
            points[1][i] = points[1][i-1] - height/countPointsHeight;
        }
        masCornerX[0] = points[0][i-1];
        masCornerY[0] = points[1][i-1];
        normalAngles[0] = (float) Math.PI/2;
        normalAngles[1] = 0;
        normalAngles[2] = (float) -Math.PI/2;
        normalAngles[3] = (float) Math.PI;
        return points;
    }

    @Override
    public float getNormalAngle(float x, float y, float speedX, float speedY) {
        if(x < this.x + width && y < this.y + height){
            if(lineCrossing(masCornerX[0] + this.x, masCornerY[0] + this.y,
                    masCornerX[1] + this.x, masCornerY[1] + this.y,
                    x, y,
                    x - speedX + this.speedX, y - speedY + this.speedY)){
                return normalAngles[0];
            }
            if(lineCrossing(masCornerX[2] + this.x, masCornerY[2] + this.y,
                    masCornerX[3] + this.x, masCornerY[3] + this.y,
                    x, y,
                    x - speedX + this.speedX, y - speedY + this.speedY)){
                return normalAngles[2];
            }
        } else {
            if(lineCrossing(masCornerX[1] + this.x, masCornerY[1] + this.y,
                    masCornerX[2] + this.x, masCornerY[2] + this.y,
                    x, y,
                    x - speedX + this.speedX, y - speedY + this.speedY)){
                return normalAngles[1];
            }
            if(lineCrossing(masCornerX[3] + this.x, masCornerY[3] + this.y,
                    masCornerX[0] + this.x, masCornerY[0] + this.y,
                    x, y,
                    x - speedX + this.speedX, y - speedY + this.speedY)){
                return normalAngles[3];
            }
        }
        return defaultNormalAngle;
    }
}
