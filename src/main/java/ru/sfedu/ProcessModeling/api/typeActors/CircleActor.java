package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;

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
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.translate(x + centerX, y + centerY);
        graphics2D.rotate(rotation);
        graphics2D.setColor(color);
        graphics2D.fillOval((int)-width/2, (int)-height/2, (int)width, (int)height);
        graphics2D.rotate(-rotation);
        graphics2D.setColor(Color.red);
        for (int i = 0; i<points[0].length; i++){
            graphics2D.fillOval((int)points[0][i], (int)points[1][i], 4,4);
        }
        graphics2D.translate(-(x + centerX),-(y + centerY));
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        float cosRotation = (float) Math.cos(-rotation);
        float sinRotation = (float) Math.sin(-rotation);
        float rx = -(x - centerX - this.x);
        float ry = -(y - centerY - this.y);
        float dx = rx * cosRotation - ry * sinRotation;
        float dy = ry * cosRotation + rx * sinRotation;
        return ((dx * dx) / (width/2 * width/2)) + ((dy * dy) / (height/2 * height/2)) <= 1;
    }

    private float distance(float x1, float y1, float x2, float y2){
        return (float)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    @Override
    public float[][] getPoints(float width, float height) {
        float dAngleRotation;
        float y0 = MIN_HEIGHT / 2;
        float d = MIN_WIDTH / 2;
        int k = 0;
        float bufX, bufY, c, s;

        centerX = width/2;
        centerY = height/2;

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
            for(int i=0; i<res[0].length; i++){
                res[0][i] -= centerX;
                res[1][i] -= centerY;
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
            for(int i=0; i<res[0].length; i++){
                res[0][i] -= centerX;
                res[1][i] -= centerY;
            }
            return res;
        }
    }

}
