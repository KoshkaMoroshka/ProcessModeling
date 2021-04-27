package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.api.typeActors.*;
import ru.sfedu.ProcessModeling.api.typeMotions.GravityMotion;
import ru.sfedu.ProcessModeling.api.typeMotions.LinearMotion;
import ru.sfedu.ProcessModeling.api.typeMotions.SlidingMotion;

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
    public int  windowWidth, windowHeight;

    public Simulation (int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }

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
            //actor.speedX = 0;
            //actor.speedY = 0;
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
    public void createInitialActorObject() throws IOException {
        TriangleActor triangleActor = new TriangleActor(this, 400, 100, 80, 80);
        //triangleActor.rotate(1f);

        TriangleActor triangleActor1 = new TriangleActor(this, 250, 200, 40, 40);

        RectangleActor rectangleActor = new RectangleActor(this, 350, 250, 130, 130);
        //rectangleActor.rotate(1f);

        RectangleActor rectangleActor1 = new RectangleActor(this, 250, 350, 80, 80);
        rectangleActor1.rotate(1f);

        CircleActor circleActor = new CircleActor(this, 100, 50, 120, 220);
        //circleActor.rotate(1f);

        CircleActor circleActor1 = new CircleActor(this, 350, 260, 60, 130);
        Color color = new Color(150,75,0);
        circleActor1.color = color;
        //circleActor1.rotate(1f);

        VideoRecorder videoRecorder = new VideoRecorder(this, 500,500);
        videoRecorder.rigid = false;

        BoxCollider boxCollider = new BoxCollider(this, 0, 0, 800, 742);
        //boxCollider.inert = true;

        BufferedImage image = ImageIO.read(new File("G:\\f\\app\\src\\main\\res\\drawable-nodpi\\artur.png"));
        GraphicActor graphicActor = new GraphicActor(this, 100, 100, image);

        //actors.add(triangleActor);
        //actors.add(rectangleActor);
        //actors.add(rectangleActor1);
        actors.add(circleActor);
        //actors.add(triangleActor1);
        //actors.add(circleActor1);
        //actors.add(videoRecorder);
        //actors.add(graphicActor);
        actors.add(boxCollider);


        LinearMotion linearMotion1 = new LinearMotion(circleActor, 300, 300);

        LinearMotion linearMotion = new LinearMotion(triangleActor, 600, 567);

        GravityMotion gravityMotion = new GravityMotion(triangleActor);

        SlidingMotion slidingMotion = new SlidingMotion(circleActor, 0.2f);


        //motions.add(linearMotion1);
        //motions.add(linearMotion);
        motions.add(gravityMotion);
        //motions.add(slidingMotion);
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