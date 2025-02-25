package ru.sfedu.ProcessModeling.api.typeActors;

import org.jcodec.api.awt.AWTSequenceEncoder;
import ru.sfedu.ProcessModeling.api.Simulation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VideoRecorder extends RectangleActor {

    private AWTSequenceEncoder sequenceEncoder;
    private Robot robot;
    private Rectangle screenRect;
    public String nameFile;
    int fpsVideo;

    public VideoRecorder(Simulation processing, int width, int height, String pathAndNameSave, int fpsVideo) {
        super(processing,0, 0, width, height);
        nameFile = pathAndNameSave;
        this.fpsVideo = fpsVideo;
        rigid = false;
        inert = false;
        bounce = false;
    }
    public void startVideo() throws AWTException, IOException {
            robot = new Robot();
            screenRect = new Rectangle((int)processing.width, (int)processing.height);
            File file = new File(nameFile);
            sequenceEncoder = AWTSequenceEncoder.createSequenceEncoder(file, fpsVideo);
    }
    public void finishVideo() throws IOException {
        sequenceEncoder.finish();
    }

    @Override
    public void draw(Graphics g){
        try {
            screenRect.setBounds(0, 0, (int)this.width - (int)this.width%2, (int)this.height - (int)this.height%2);
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
