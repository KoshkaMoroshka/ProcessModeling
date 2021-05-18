package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.api.events.EventManager;
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
    protected ProcessField processField;
    public ArrayList<Actor> actors = new ArrayList<>();
    public ArrayList<Motion> motions = new ArrayList<>();
    public ArrayList<JButton> buttons = new ArrayList<>();
    public EventManager manager = new EventManager();
    protected int keyCode;
    public float width, height;
    public int  windowWidth, windowHeight;
    public int timeToChangeFrame;

    public Simulation (int width, int height, int timeToChangeFrame) {
        windowWidth = width;
        windowHeight = height;
        this.timeToChangeFrame = timeToChangeFrame;
    }

    /***
     * This function create and start simulation
     */
    public void start() throws IOException {
        createWindow();
        createInitialObject();
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
    public void createWindow(){
        processField = new ProcessField(this);
        window = new JFrame("ProcessField");
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                finish();
                super.windowClosing(e);
            }
        });
        window.setSize(windowWidth, windowHeight);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(processField);
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
        timer.scheduleAtFixedRate(task, 0, timeToChangeFrame);
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
        manager.updateListeners();
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
    public void createInitialObject() throws IOException {
        TriangleActor triangleActor = new TriangleActor(this, 450, 400, 80, 80);
        //triangleActor.rotate(1f);

        TriangleActor triangleActor1 = new TriangleActor(this, 100, 100, 130, 130);

        RectangleActor rectangleActor = new RectangleActor(this, 250, 135, 130, 130);
        //rectangleActor.rotate(1f);

        RectangleActor rectangleActor1 = new RectangleActor(this, 450, 135, 130, 130);
        Color color = new Color(6, 175, 150, 235);
        rectangleActor1.color = color;
        //rectangleActor1.rotate(1f);

        CircleActor circleActor = new CircleActor(this, 100, 300, 180, 100);
        color = new Color(6, 118, 175, 245);
        circleActor.color = color;
        //circleActor.rotate(1f);

        CircleActor circleActor1 = new CircleActor(this, 300, 400, 40, 40);
        color = new Color(6, 118, 175, 245);
        circleActor1.color = color;
        //circleActor1.rotate(1f);

        VideoRecorder videoRecorder = new VideoRecorder(this, 800,800, "G:\\HahaBenis\\stringCoco.mp4", 20);
        videoRecorder.rigid = false;

        BoxCollider boxCollider = new BoxCollider(this, 0, 0, 800, 742);
        //boxCollider.inert = true;

        BufferedImage image = ImageIO.read(new File("G:\\f\\app\\src\\main\\res\\drawable-nodpi\\artur.png"));
        RectangleGraphicActor rectangleGraphicActor = new RectangleGraphicActor(this, 100, 100, image);

        AnimatedActor animatedActor = new AnimatedActor();
        animatedActor.createAnimatedActor(this, 250, 200, 1, "D:\\Animated");

        //actors.add(triangleActor);
        //actors.add(rectangleActor);
        //actors.add(rectangleActor1);
        actors.add(circleActor);
        //actors.add(animatedActor);
        //actors.add(triangleActor1);
        //actors.add(circleActor1);
        actors.add(videoRecorder);
        //actors.add(graphicActor);
        //actors.add(boxCollider);


        //LinearMotion linearMotion1 = new LinearMotion(rectangleActor, 735, 200);

        LinearMotion linearMotion = new LinearMotion(circleActor, 550, 350);

        //GravityMotion gravityMotion = new GravityMotion(triangleActor);

        //PushMotion pushMotion = new PushMotion(circleActor, 0.01f, 2, 2);

        //RoundMotion roundMotion = new RoundMotion(triangleActor1, 350, 350);
        //roundMotion.speed = 0.2f;



        //motions.add(linearMotion1);
        motions.add(linearMotion);
        //motions.add(gravityMotion);
        //motions.add(pushMotion);
        //motions.add(roundMotion);
        //motions.add(ellipseRollMotion);
    }

    public boolean addActorToSimulation(Actor actor){
        actors.add(actor);
        return true;
    }
    public boolean addMotionToSimulation(Motion motion) {
        motions.add(motion);
        return true;
    }
}