package ru.sfedu.ProcessModeling.api;

import org.jcodec.api.awt.AWTSequenceEncoder;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleActor;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VideoRecorder extends RectangleActor {

    private AWTSequenceEncoder sequenceEncoder;
    Robot robot;
    Rectangle screenRect;
    String nameFile = "G:\\HahaBenis\\stringCoco.mp4";

    public VideoRecorder(Simulation processing, int width, int height) {
        super(processing, width, height);

    }
    public void startVideo() throws AWTException, IOException {
            robot = new Robot();
            screenRect = new Rectangle((int)processing.width, (int)processing.height);
            File file = new File(nameFile);
            sequenceEncoder = AWTSequenceEncoder.createSequenceEncoder(file, 50);
    }
    public void finishVideo() throws IOException {
        sequenceEncoder.finish();
    }

    @Override
    public void draw(Graphics g){
        try {
            screenRect.setBounds(0, 0, (int)processing.width - (int)processing.width%2, (int)processing.height - (int)processing.height%2);
            sequenceEncoder.encodeImage(robot.createScreenCapture(screenRect));
        }catch (Exception e){
        }
    }

    @Override
    public void start() {
        super.start();
        try {
            startVideo();
        } catch (Exception e){

        }
    }

    @Override
    public void stop() {
        super.stop();
        try{
            finishVideo();
        } catch (Exception e){
        }
    }
}
