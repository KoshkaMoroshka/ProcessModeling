package ru.sfedu.ProcessModeling.api;


import ru.sfedu.ProcessModeling.api.typeActors.CircleActor;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleActor;
import ru.sfedu.ProcessModeling.api.typeActors.TriangleActor;
import ru.sfedu.ProcessModeling.api.typeMotions.LinearMotion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation{

    public JFrame window;
    private ProcessField processField;
    public ArrayList<Actor> actors = new ArrayList<>();
    public ArrayList<Motion> motions = new ArrayList<>();
    public float width, height;


    //JPanel invisibleArea = new JPanel();


    /***
     * This function create and start simulation
     */
    public void start() throws IOException, AWTException {
        createWindow();
        createInitialActorObject();
        for (Actor actor: actors)
            actor.start();
        startTimer();
    }

    public void finish(){
        for (Actor actor: actors)
            actor.stop();
    }

    /***
     * Сreates a simulation rendering area
     */
    public void createWindow() throws IOException, AWTException {
        processField = new ProcessField(this);
        window = new JFrame("ProcessField");
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                finish();
                super.windowClosing(e);
            }
        });
        window.setSize(800, 800);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(processField);
        //invisibleArea.setPreferredSize(new Dimension(800, 800));
        //window.add(invisibleArea, BorderLayout.WEST);
        window.setVisible(true);
    }

    /***
     * Timer, after which a new frame will be drawn
     */
    private void startTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        };
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 20);
    }

    /***
     * Single frame refresh function
     */
    private void tick(){
        //rectangleActor2.x = (float) invisibleArea.getMousePosition().getX();
        //rectangleActor2.y = (float) invisibleArea.getMousePosition().getY();
        //rectangleActor2.color = Color.GREEN;
        for (Motion motion: motions)
            motion.move();
        for (Actor actor : actors) {
            actor.update();
            //actor.rotate(1f);
        }
        updateCollisions();
        processField.repaint();
    }

    private void updateCollisions(){
        Actor collider1, collider2;
        for (int i = 0; i<actors.size()-1; i++){
            collider1 = actors.get(i);
            for (int k = i+1; k < actors.size(); k++){
                collider2 = actors.get(k);
                if(collider1.collision(collider2) || collider2.collision(collider1)){
                    collider1.color = Color.RED;
                    collider1.onCollision(collider2);
                }else collider1.color = Color.BLUE;
            }
        }
    }

    /***
     * Create all actor objects
     */
    public RectangleActor rectangleActor2;
    public RectangleActor rectangleActor3;
    private void createInitialActorObject() throws IOException, AWTException {
        TriangleActor triangleActor = new TriangleActor(this, 40, 40);
        triangleActor.x = 115;
        triangleActor.y = 40;
        triangleActor.rotate(1f);
        TriangleActor triangleActor1 = new TriangleActor(this, 40, 40);
        triangleActor1.x = 250;
        triangleActor1.y = 200;
        triangleActor1.setSpeedX(2f);
        triangleActor1.setSpeedY(2f);
        RectangleActor rectangleActor = new RectangleActor(this, 120, 130);
        rectangleActor.x = 350;
        rectangleActor.y = 90;
        rectangleActor.setSpeedX(2f);
        rectangleActor.setSpeedY(2f);
        RectangleActor rectangleActor1 = new RectangleActor(this, 150, 150);
        rectangleActor1.x = 250;
        rectangleActor1.y = 350;
        rectangleActor1.rotate(1f);
        CircleActor circleActor = new CircleActor(this, 40, 220);
        circleActor.setSpeedX(4f);
        circleActor.setSpeedY(0f);
        circleActor.x = 100;
        circleActor.y = 250;
        CircleActor circleActor1 = new CircleActor(this, 120, 220);
        Color color = new Color(150,75,0);
        circleActor1.color = color;
        circleActor1.x = 360;
        circleActor1.y = 320;
        //VideoRecorder videoRecorder = new VideoRecorder(this, 0,0);
        //videoRecorder.isRigid = false;

        LinearMotion linearMotion = new LinearMotion(rectangleActor1, 100, 100);
        motions.add(linearMotion);

        //circleActor1.rotate(1f);
        rectangleActor2 = rectangleActor;
        rectangleActor3 = rectangleActor1;
        //actors.add(circleActor);
        //actors.add(rectangleActor);
        actors.add(rectangleActor1);
        //actors.add(triangleActor);
        //actors.add(triangleActor1);
        //actors.add(circleActor1);
        //actors.add(videoRecorder);


    }
}

