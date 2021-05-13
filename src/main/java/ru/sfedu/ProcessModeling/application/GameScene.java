package ru.sfedu.ProcessModeling.application;

import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.events.EventListener;
import ru.sfedu.ProcessModeling.api.typeActors.GraphicActor;
import ru.sfedu.ProcessModeling.api.typeMotions.LinearMotion;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameScene extends Simulation {



    public GameScene(int width, int height, int timeToChangeFrame) {
        super(width, height, timeToChangeFrame);

    }

    @Override
    public void createInitialObject() throws IOException {
        window.setResizable(false);
        window.setLocation(380, 120);

        BufferedImage image = ImageIO.read(new File("C:\\Users\\Eugene\\IdeaProjects\\ProcessModeling\\src\\main\\" +
                                                             "resources\\space.png"));
        BufferedImage imageNLO = ImageIO.read(new File("C:\\Users\\Eugene\\IdeaProjects\\ProcessModeling\\src\\main\\" +
                "resources\\nlo.png"));
        GraphicActor space = new GraphicActor(this, 0,0, image);
        space.inert = false;
        space.bounce = false;
        space.rigid = false;
        GraphicActor space1 = new GraphicActor(this, 1200,0, image);
        space1.inert = false;
        space1.bounce = false;
        space1.rigid = false;
        GraphicActor nlo = new GraphicActor(this, 0, 300, imageNLO);

        manager.addListener(new EventListener() {
            @Override
            public void event() {
                if(actors.get(0).x == -1800 + actors.get(0).centerX){
                    actors.get(0).x = 1200;
                }
                if(actors.get(1).x == -1800 + actors.get(1).centerX){
                    actors.get(1).x = 1200;
                }
            }
        });


        actors.add(space);
        actors.add(space1);
        actors.add(nlo);


        LinearMotion linearMotion = new LinearMotion(space, -1800 + space.centerX, 0 + space.centerY);
        LinearMotion linearMotion1 = new LinearMotion(space1, -1800 + space1.centerX, 0 + space1.centerY);
        LinearMotion gravityNLO = new LinearMotion(nlo, nlo.centerX, window.getHeight() - 2 * nlo.centerY - 20);
        gravityNLO.speed = 1.5f;
        LinearMotion motion = new LinearMotion(nlo, nlo.centerX,0 + nlo.centerX - 28);
        motion.speed = 0f;


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

    }
}
