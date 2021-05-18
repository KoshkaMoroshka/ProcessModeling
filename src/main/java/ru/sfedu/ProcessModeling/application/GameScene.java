package ru.sfedu.ProcessModeling.application;

import ru.sfedu.ProcessModeling.Constants;
import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.typeActors.GraphicActor;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleActor;
import ru.sfedu.ProcessModeling.api.typeMotions.LinearMotion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameScene extends Simulation {

    Random random = new Random();

    public GameScene(int width, int height, int timeToChangeFrame) {
        super(width, height, timeToChangeFrame);
    }

    @Override
    public void createInitialObject() throws IOException {
        window.setResizable(false);
        window.setLocation(380, 120);

        BufferedImage image = ImageIO.read(new File(Constants.PATH_TO_IMAGE_SPACE));
        BufferedImage imageNLO = ImageIO.read(new File(Constants.PATH_TO_IMAGE_NLO));
        BufferedImage imageMeteor = ImageIO.read(new File(Constants.PATH_TO_IMAGE_METEOR));
        BufferedImage imageHeart = ImageIO.read(new File(Constants.PATH_TO_IMAGE_HEART));

        GraphicActor space = new GraphicActor(this, 0,0, image);
        space.inert = false;
        space.bounce = false;
        space.rigid = false;
        GraphicActor space1 = new GraphicActor(this, 1200,0, image);
        space1.inert = false;
        space1.bounce = false;
        space1.rigid = false;
        GraphicActor nlo = new GraphicActor(this, 0, 300, imageNLO);
        RectangleActor rectangleActor = new RectangleActor(this, 0, 0 , window.getWidth(), 50);
        rectangleActor.inert = false;
        rectangleActor.color = new Color(0, 0, 0, 220);

        actors.add(space);
        actors.add(space1);
        actors.add(nlo);

        GraphicActor meteor = new GraphicActor(this, window.getWidth() + 60, 55, imageMeteor);
        GraphicActor[] meteors = new GraphicActor[(int)((window.getHeight() - 50) / meteor.height) - 1];
        meteors[0] = new GraphicActor(this, window.getWidth() + 60, 55, imageMeteor);
        actors.add(meteors[0]);
        for(int i = 1; i < (int)((window.getHeight() - 50) / meteor.height) - 1; i++){
            meteors[i] = new GraphicActor(this, window.getWidth() + random.nextInt()*30,
                                        meteors[i-1].y + meteor.height + 10, imageMeteor);
            actors.add(meteors[i]);
        }
        actors.add(rectangleActor);

        LinearMotion linearMotion = new LinearMotion(space, -1800 + space.centerX, 0 + space.centerY);
        LinearMotion linearMotion1 = new LinearMotion(space1, -1800 + space1.centerX, 0 + space1.centerY);
        LinearMotion gravityNLO = new LinearMotion(nlo, nlo.centerX, window.getHeight() - 2 * nlo.centerY - 20);
        gravityNLO.speed = 1.5f;
        LinearMotion motion = new LinearMotion(nlo, nlo.centerX,0 + nlo.centerX + 20);
        motion.speed = 0f;
        LinearMotion meteorMotion = new LinearMotion(meteor, -60, meteor.centerY + meteor.y);
        meteorMotion.speed = 4f;
        LinearMotion[] meteorsMotions = new LinearMotion[(int)((window.getHeight() - 50) / meteor.height) - 1];
        for(int i = 0; i < (int)((window.getHeight() - 50) / meteor.height) - 1; i++){
            meteorsMotions[i] = new LinearMotion(meteors[i], -60, meteors[i].centerY + meteors[i].y);
            meteorsMotions[i].speed = 0.5f + random.nextFloat()*3f;
            motions.add(meteorsMotions[i]);
        }

        motions.add(linearMotion);
        motions.add(linearMotion1);
        motions.add(gravityNLO);
        motions.add(motion);

        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    motion.speed = 3f;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                motion.speed = 0;
            }
        });
        manager.addListener(() -> {
            if(actors.get(0).x == -1800 + actors.get(0).centerX){
                actors.get(0).x = 1200;
            }
            if(actors.get(1).x == -1800 + actors.get(1).centerX){
                actors.get(1).x = 1200;
            }
        });
        manager.addListener(() -> {
            for(int i = 0; i < (int)((window.getHeight() - 50) / meteor.height) - 1; i++){
                if(meteors[i].x <= - 80){
                    meteors[i].x = window.getWidth() + 80;
                    meteorsMotions[i].speed = 0.5f + random.nextFloat()*3f;
                }
                if(meteors[i].collision(nlo) || nlo.collision(meteors[i])){
                    meteors[i].x = window.getWidth() + 80;
                    meteorsMotions[i].speed = 0.5f + random.nextFloat()*3f;
                }
            }
        });
    }
}
