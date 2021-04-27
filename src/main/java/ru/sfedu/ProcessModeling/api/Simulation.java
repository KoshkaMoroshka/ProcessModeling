package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.api.typeActors.*;
import ru.sfedu.ProcessModeling.api.typeMotions.LinearMotion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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

    /***
     * This function create and start simulation
     */
    public void start() throws IOException, AWTException {
        createInitialActorObject();
        createWindow();
        for (Actor actor: actors)
            actor.start();
        startTimer();
    }

    /***
     * This function needed to stop all update and render actors
     */
    public void finish(){
        for (Actor actor: actors)
            actor.stop();
    }

    /***
     * Creates a simulation rendering area
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
        for (Motion motion: motions)
            motion.move();
        updateCollisions();
        for (Actor actor : actors) {
            actor.update();
            actor.speedX = 0;
            actor.speedY = 0;
        }
        processField.repaint();
    }

    /***
     * Update collisions all actors
     */
    private void updateCollisions(){
        Actor collider1, collider2;
        for (int i = 0; i<actors.size()-1; i++){
            collider1 = actors.get(i);
            for (int k = i+1; k < actors.size(); k++){
                collider2 = actors.get(k);
                if(collider1.collision(collider2) || collider2.collision(collider1)){
                    collider1.color = Color.RED;
                    collider1.onCollision(collider2);
                    collider1.update();
                    collider2.update();
                }else collider1.color = Color.BLUE;
            }
        }
    }

    /***
     * Create all objects before create simulation window
     */
    public void createInitialActorObject() throws IOException, AWTException {
        TriangleActor triangleActor = new TriangleActor(this, 80, 80);
        triangleActor.x = 100;
        triangleActor.y = 80;
        //triangleActor.rotate(1f);
        TriangleActor triangleActor1 = new TriangleActor(this, 40, 40);
        triangleActor1.x = 250;
        triangleActor1.y = 200;
        triangleActor1.setSpeedX(2f);
        triangleActor1.setSpeedY(2f);
        RectangleActor rectangleActor = new RectangleActor(this, 130, 130);
        rectangleActor.x = 350;
        rectangleActor.y = 250;
        rectangleActor.setSpeedX(2f);
        rectangleActor.setSpeedY(2f);
        //rectangleActor.rotate(1f);
        RectangleActor rectangleActor1 = new RectangleActor(this, 80, 80);
        rectangleActor1.x = 250;
        rectangleActor1.y = 350;
        rectangleActor1.rotate(1f);
        CircleActor circleActor = new CircleActor(this, 120, 220);
        circleActor.setSpeedX(4f);
        circleActor.setSpeedY(0f);
        circleActor.x = 100;
        circleActor.y = 50;
        CircleActor circleActor1 = new CircleActor(this, 60, 130);
        Color color = new Color(150,75,0);
        circleActor1.color = color;
        circleActor1.x = 350;
        circleActor1.y = 260;

        VideoRecorder videoRecorder = new VideoRecorder(this, 500,500);
        videoRecorder.isRigid = false;

        //circleActor.rotate(1f);

        //actors.add(triangleActor);
        //actors.add(rectangleActor);
        //actors.add(rectangleActor1);
        //circleActor.rotate(1f);
        //actors.add(circleActor);

        //actors.add(triangleActor1);
        //circleActor1.rotate(1f);
        //actors.add(circleActor1);
        actors.add(videoRecorder);

        BoxCollider boxCollider = new BoxCollider(this, 800, 568);
        actors.add(boxCollider);

        BufferedImage image = ImageIO.read(new File("G:\\f\\app\\src\\main\\res\\drawable-nodpi\\artur.png"));
        GraphicActor graphicActor = new GraphicActor(this, image);
        graphicActor.x = 100;
        graphicActor.y = 100;
        actors.add(graphicActor);


        LinearMotion linearMotion1 = new LinearMotion(circleActor, 600, 200);
        motions.add(linearMotion1);
        LinearMotion linearMotion = new LinearMotion(rectangleActor1, 0, 0);
        motions.add(linearMotion);
    }

    public void addActorToSimulation(Actor actor){
        actors.add(actor);
    }
    public void addMotionToSimulation(Motion motion) {
        motions.add(motion);
    }
}