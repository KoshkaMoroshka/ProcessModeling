package ru.sfedu.ProcessModeling.application;

import ru.sfedu.ProcessModeling.Constants;
import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.typeActors.EllipseGraphicActor;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleActor;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleGraphicActor;
import ru.sfedu.ProcessModeling.api.typeMotions.LinearMotion;
import ru.sfedu.ProcessModeling.api.typeMotions.SpinMotion;

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
    EllipseGraphicActor[] meteors;
    LinearMotion[] meteorsMotions;
    SpinMotion[] spinMeteorsMotions;
    int countHeart = 3;

    public GameScene(int width, int height, int timeToChangeFrame) {
        super(width, height, timeToChangeFrame);
    }

    private void updateSpeedMeteor(LinearMotion linearMotion, SpinMotion spinMotion){
        linearMotion.speed = 2f + random.nextFloat()*3f;
        spinMotion.speed = linearMotion.speed * (random.nextBoolean() ? 1f : -1f) / 16;
    }

    @Override
    public void createInitialObject() throws IOException {
        window.setResizable(false);
        window.setLocation(380, 120);

        BufferedImage image = ImageIO.read(new File(Constants.PATH_TO_IMAGE_SPACE));
        RectangleGraphicActor space = new RectangleGraphicActor(this, 0,0, image);
        space.inert = false;
        space.bounce = false;
        space.rigid = false;
        LinearMotion linearMotion = new LinearMotion(space, -1800 + space.centerX, 0 + space.centerY);
        actors.add(space);
        motions.add(linearMotion);

        RectangleGraphicActor space1 = new RectangleGraphicActor(this, 1200,0, image);
        space1.inert = false;
        space1.bounce = false;
        space1.rigid = false;
        LinearMotion linearMotion1 = new LinearMotion(space1, -1800 + space1.centerX, 0 + space1.centerY);
        actors.add(space1);
        motions.add(linearMotion1);

        BufferedImage imageNLO = ImageIO.read(new File(Constants.PATH_TO_IMAGE_NLO));
        RectangleGraphicActor nlo = new RectangleGraphicActor(this, 0, 300, imageNLO);
        LinearMotion gravityNLO = new LinearMotion(nlo, nlo.centerX, window.getHeight() - 2 * nlo.centerY - 20);
        gravityNLO.speed = 3f;
        LinearMotion motion = new LinearMotion(nlo, nlo.centerX,0 + nlo.centerX + 20);
        motion.speed = 0f;
        actors.add(nlo);
        motions.add(gravityNLO);
        motions.add(motion);

        float meteorX = window.getWidth() + 60, meteorY = 55;
        BufferedImage imageMeteor = ImageIO.read(new File(Constants.PATH_TO_IMAGE_METEOR));
        meteors = new EllipseGraphicActor[(int)((window.getHeight() - 50) / imageMeteor.getHeight()) - 1];
        meteorsMotions = new LinearMotion[(int)((window.getHeight() - 50) / imageMeteor.getHeight()) - 1];
        spinMeteorsMotions = new SpinMotion[(int)((window.getHeight() - 50) / imageMeteor.getHeight()) - 1];
        for(int i = 0; i < meteors.length; i++, meteorY += imageMeteor.getHeight() + 10) {
            actors.add(meteors[i] = new EllipseGraphicActor(this, meteorX + random.nextFloat() * 1000,
                    meteorY, imageMeteor));
            meteorsMotions[i] = new LinearMotion(meteors[i], -60, meteors[i].centerY + meteors[i].y);
            spinMeteorsMotions[i] = new SpinMotion(meteors[i], 0);
            updateSpeedMeteor(meteorsMotions[i], spinMeteorsMotions[i]);
            motions.add(meteorsMotions[i]);
            motions.add(spinMeteorsMotions[i]);
        }

        RectangleActor rectangleActor = new RectangleActor(this, 0, 0 , window.getWidth(), 55);
        rectangleActor.inert = false;
        rectangleActor.rigid = false;
        rectangleActor.color = new Color(0, 0, 0, 220);
        actors.add(rectangleActor);

        BufferedImage imageHeart = ImageIO.read(new File(Constants.PATH_TO_IMAGE_HEART));
        float heartX = window.getWidth() - imageHeart.getHeight() - imageHeart.getHeight() - imageHeart.getHeight() - 50, heartY = 0;
        RectangleGraphicActor[] heartActors = new RectangleGraphicActor[3];
        for(int i = 0; i < 3; i++, heartX += imageHeart.getHeight() + 10){
            heartActors[i] = new RectangleGraphicActor(this, heartX, heartY, imageHeart);
            heartActors[i].inert = false;
            heartActors[i].rigid = false;
            heartActors[i].bounce = false;
            actors.add(heartActors[i]);
        }

        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    motion.speed = 6f;
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
            for(int i = 0; i < (int)((window.getHeight() - 50) / imageMeteor.getHeight()) - 1; i++){
                if(meteors[i].x <= - 80){
                    meteors[i].x = window.getWidth() + 80;
                    meteorsMotions[i].speed = 0.5f + random.nextFloat()*3f;
                }
                if(meteors[i].collision(nlo) || nlo.collision(meteors[i])){
                    meteors[i].x = window.getWidth() + 80;
                    updateSpeedMeteor(meteorsMotions[i], spinMeteorsMotions[i]);
                    if (countHeart > 0) {
                        actors.remove(actors.size() - countHeart);
                        countHeart--;
                        if (countHeart == 0) {
                            window.setVisible(false); //you can't see me!
                            window.dispose();
                        }
                    }
                }
            }
        });
    }
}
