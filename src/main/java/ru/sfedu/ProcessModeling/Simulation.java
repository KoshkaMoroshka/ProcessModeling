package ru.sfedu.ProcessModeling;


import ru.sfedu.ProcessModeling.api.*;
import ru.sfedu.ProcessModeling.api.typeActors.CircleActor;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleActor;
import ru.sfedu.ProcessModeling.api.typeActors.TriangleActor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedHashSet;
import java.util.Set;

public class Simulation{

    public JFrame window;
    private ProcessField processField;
    public Set<Actor> actors = new LinkedHashSet<>();
    public float width, height;
    public static void main(String arg[]) {
        Simulation simulation = new Simulation();
        simulation.start();
    }

    /***
     * This function create and start simulation
     */
    public void start(){
        createWindow();
        createInitialActorObject();
        startTimer();
    }

    /***
     * Сreates a simulation rendering area
     */
    public void createWindow(){
        processField = new ProcessField(this);
        window = new JFrame("ProcessField");
        window.setSize(500, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(processField);
        window.setVisible(true);
    }

    /***
     * Timer, after which a new frame will be drawn
     */
    private void startTimer(){
        Timer timer = new Timer(5, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
    }

    /***
     * Single frame refresh function
     */
    private void tick(){
        for (Actor actor : actors) {
            actor.update();
        }
        processField.repaint();
    }

    /***
     * Create all actor objects
     */
    public TriangleActor rectangleActor2;
    public TriangleActor rectangleActor3;
    private void createInitialActorObject(){
        TriangleActor triangleActor = new TriangleActor(this, 40, 40);
        triangleActor.x = 115;
        triangleActor.y = 40;
        TriangleActor triangleActor1 = new TriangleActor(this, 40, 40);
        triangleActor1.x = 250;
        triangleActor1.y = 200;
        triangleActor1.setSpeedX(4f);
        triangleActor1.setSpeedY(4f);
        RectangleActor rectangleActor = new RectangleActor(this, 40, 40);
        rectangleActor.x = 450;
        rectangleActor.y = 250;
        rectangleActor.setSpeedX(-1f);
        RectangleActor rectangleActor1 = new RectangleActor(this, 40, 40);
        rectangleActor1.x = 230;
        rectangleActor1.y = 90;
        CircleActor circleActor = new CircleActor(this, 40, 40);
        circleActor.setSpeedX(10f);
        circleActor.setSpeedY(10f);
        circleActor.x = 120;
        circleActor.y = 340;
        CircleActor circleActor1 = new CircleActor(this, 40, 40);
        circleActor1.x = 150;
        circleActor1.y = 300;
        rectangleActor2 = triangleActor;
        rectangleActor3 = triangleActor1;
        actors.add(circleActor);
        actors.add(rectangleActor);
        actors.add(rectangleActor1);
        actors.add(triangleActor);
        actors.add(triangleActor1);
        actors.add(circleActor1);
    }
}

