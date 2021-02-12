package ru.sfedu.ProcessModeling;


import ru.sfedu.ProcessModeling.api.*;
import ru.sfedu.ProcessModeling.api.typeActors.CircleActor;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleActor;
import ru.sfedu.ProcessModeling.api.typeActors.TriangleActor;

import javax.swing.*;
import java.awt.*;
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

    //JPanel invisibleArea = new JPanel();


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
        //rectangleActor2.x = (float) invisibleArea.getMousePosition().getX();
        //rectangleActor2.y = (float) invisibleArea.getMousePosition().getY();
        //rectangleActor2.color = Color.GREEN;
        for (Actor actor : actors) {
            actor.update();
        }
        processField.repaint();
    }

    /***
     * Create all actor objects
     */
    public RectangleActor rectangleActor2;
    public RectangleActor rectangleActor3;
    private void createInitialActorObject(){
        TriangleActor triangleActor = new TriangleActor(this, 40, 40);
        triangleActor.x = 115;
        triangleActor.y = 40;
        TriangleActor triangleActor1 = new TriangleActor(this, 40, 40);
        triangleActor1.x = 250;
        triangleActor1.y = 200;
        triangleActor1.setSpeedX(4f);
        triangleActor1.setSpeedY(4f);
        RectangleActor rectangleActor = new RectangleActor(this, 150, 150);
        rectangleActor.x = 450;
        rectangleActor.y = 250;
        rectangleActor.setSpeedX(3f);
        rectangleActor.setSpeedX(-2f);
        RectangleActor rectangleActor1 = new RectangleActor(this, 150, 150);
        rectangleActor1.x = 230;
        rectangleActor1.y = 90;
        rectangleActor1.setSpeedX(1f);
        rectangleActor1.setSpeedX(2f);
        CircleActor circleActor = new CircleActor(this, 40, 220);
        circleActor.setSpeedX(2f);
        circleActor.setSpeedY(2f);
        circleActor.x = 120;
        circleActor.y = 80;
        CircleActor circleActor1 = new CircleActor(this, 40, 220);
        circleActor1.x = 360;
        circleActor1.y = 320;

        rectangleActor2 = rectangleActor;
        rectangleActor3 = rectangleActor1;
        actors.add(circleActor);
        actors.add(rectangleActor);
        actors.add(rectangleActor1);
        actors.add(triangleActor);
        actors.add(triangleActor1);
        actors.add(circleActor1);
    }
}

