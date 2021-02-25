package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Collider;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.MIN_HEIGHT;
import static ru.sfedu.ProcessModeling.Constants.MIN_WIDTH;

public class CircleActor extends Actor {
    public Color color = Color.GREEN;

    public CircleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(color);
        g.fillOval((int)x, (int)y, (int)this.width, (int)this.height);
        g.setColor(Color.RED);
    }

    private float pointFX1, pointFY1, pointFX2, pointFY2;
    private float focalDistance;

    private void findFocusEllipse(float width, float height){
        float e;
        float c;
        float a = width/2, b = height/2;
        if(width > height){
            e = (float) Math.sqrt(1 - b/a);
            c = a * e;
            float pointX = this.x + a, pointY = this.y + b;
            pointFX1 = pointX - c;
            pointFY1 =  pointY;
            pointFX2 = pointX + c;
            pointFY2 = pointY;
            focalDistance = 2*a;
        } else{
            e = (float) Math.sqrt(1 - a/b);
            c = b * e;
            float pointX = this.x + a, pointY = this.y + b;
            pointFX1 = pointX;
            pointFY1 =  pointY - c;
            pointFX2 = pointX;
            pointFY2 = pointY + c;
            focalDistance = 2*b;
        }
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        x -= this.x;
        y -= this.y;
        double d1 = Math.sqrt((pointFX1 - x)*(pointFX1 - x) + (pointFY1 - y)*(pointFY1 - y));
        double d2 = Math.sqrt((pointFX2 - x)*(pointFX2 - x) + (pointFY2 - y)*(pointFY2 - y));
        return d1+d2 < focalDistance;
    }

    private float distance(float x1, float y1, float x2, float y2){
        return (float)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    @Override
    public float[][] getPoints(float width, float height) {
        findFocusEllipse(width, height);
        float dAngleRotation;
        float y0 = MIN_HEIGHT / 2;
        float d = MIN_WIDTH/2;
        int k = 0;
        float bufX, bufY, c, s;
        if (width > height) {
            float x0 = (float) Math.sqrt(width * width * (1 - MIN_WIDTH * MIN_WIDTH / 4 / height / height));
            float angleTetta = (float) (2 * Math.atan(y0 / x0));
            int n = (int) Math.ceil(2 * Math.PI / angleTetta);
            if(n<8)
                n=8;
            dAngleRotation = (float) (2 * Math.PI / n);
            float[][] points = new float[2][n];
            float cc, ss, dc = (float) Math.cos(dAngleRotation), ds = (float) Math.sin(dAngleRotation);
            for(int i = 0; i<4; i++){
                cc = (float) Math.cos(Math.PI * i/2);
                ss = (float) Math.sin(Math.PI * i/2);
                points[0][k] = (width / 2 * (1 + cc));
                points[1][k] = (height / 2 * (1 + ss));
                c = cc; s=ss;
                cc = c * dc - s * ds;
                ss = c * ds + s * dc;
                k++;
                points[0][k] = (width / 2 * (1 + cc));
                points[1][k] = (height / 2 * (1 + ss));
                k++;
                for (int j = 2; j < n/4; j++, k++) {
                    c = cc; s=ss;
                    cc = c * dc - s * ds;
                    ss = c * ds + s * dc;
                    bufX = (width / 2 * (1 + cc));
                    bufY = (height / 2 * (1 + ss));
                    if(distance(bufX,bufY,points[0][k-2],points[1][k-2]) < d
                            && distance(points[0][k-1], points[1][k-1], points[0][k-2],points[1][k-2]) < d)
                        k--;
                    points[0][k] = bufX;
                    points[1][k] = bufY;
                }
            }

            float res[][] = new float[2][k];
            for (int i = 0; i<k; i++){
                res[0][i] = points[0][i];
                res[1][i] = points[1][i];
            }
            return res;
        } else {
            float x0 = (float) Math.sqrt(height * height * (1 - MIN_WIDTH * MIN_WIDTH / 4 / width / width));
            float angleTetta = (float) (2 * Math.atan(y0 / x0));
            int n = (int) (2 * Math.PI / angleTetta + 0.5f);
            if(n<8)
                n=8;
            dAngleRotation = (float) (2 * Math.PI / n);
            float[][] points = new float[2][n];

            float cc = 1.0f, ss = 0, dc = (float) Math.cos(dAngleRotation), ds = (float) Math.sin(dAngleRotation);
            for(int i = 0; i<4; i++){
                cc = (float) Math.cos(Math.PI * i/2);
                ss = (float) Math.sin(Math.PI * i/2);
                points[0][k] = (width / 2 * (1 + cc));
                points[1][k] = (height / 2 * (1 + ss));
                c = cc; s=ss;
                cc = c * dc - s * ds;
                ss = c * ds + s * dc;
                k++;
                points[0][k] = (width / 2 * (1 + cc));
                points[1][k] = (height / 2 * (1 + ss));
                k++;
                for (int j = 2; j < n/4; j++, k++) {
                    c = cc; s=ss;
                    cc = c * dc - s * ds;
                    ss = c * ds + s * dc;
                    bufX = (width / 2 * (1 + cc));
                    bufY = (height / 2 * (1 + ss));
                    if(distance(bufX,bufY,points[0][k-2],points[1][k-2]) < d
                            && distance(points[0][k-1], points[1][k-1], points[0][k-2],points[1][k-2]) < d)
                        k--;
                    points[0][k] = bufX;
                    points[1][k] = bufY;
                }
            }
            float res[][] = new float[2][k];
            for (int i = 0; i<k; i++){
                res[0][i] = points[0][i];
                res[1][i] = points[1][i];
            }
            return res;
        }
    }

    @Override
    public float getNormalAngle(float x, float y, float speedX, float speedY) {
        return defaultNormalAngle;
    }
}
