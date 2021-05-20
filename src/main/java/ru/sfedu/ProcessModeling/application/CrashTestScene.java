package ru.sfedu.ProcessModeling.application;

import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.typeActors.RectangleActor;
import ru.sfedu.ProcessModeling.api.typeActors.VideoRecorder;

import java.awt.*;
import java.io.IOException;

public class CrashTestScene extends Simulation {

    public CrashTestScene(int width, int height, int timeToChangeFrame) {
        super(width, height, timeToChangeFrame);
    }

    @Override
    public void createInitialObject() throws IOException {
        RectangleActor circleActor = new RectangleActor(this, 360, 160, 300, 60);
        Color color = new Color(9, 116, 151);
        circleActor.color = color;
        RectangleActor circleActor1 = new RectangleActor(this, 240, 350, 600, 60);
        color = new Color(9, 116, 151);
        circleActor1.color = color;
        RectangleActor circleActor2 = new RectangleActor(this, 120, 500, 1200, 60);
        color = new Color(9, 116, 151);
        circleActor2.color = color;
        RectangleActor circleActor3 = new RectangleActor(this, 20, 700, 2400, 60);
        color = new Color(9, 116, 151);
        circleActor3.color = color;
        RectangleActor circleActor4 = new RectangleActor(this, 1000, 150, 60, 300);
        color = new Color(23, 128, 118);
        circleActor4.color = color;
        RectangleActor circleActor5 = new RectangleActor(this, 1400, 50, 60, 600);
        color = new Color(23, 128, 118);
        circleActor5.color = color;
        actors.add(circleActor);
        actors.add(circleActor1);
        actors.add(circleActor2);
        actors.add(circleActor3);
        actors.add(circleActor4);
        actors.add(circleActor5);
        VideoRecorder videoRecorder = new VideoRecorder(this, 2000, 1000, "D:\\Защита дипломы\\wow.mp4", 10);
        actors.add(videoRecorder);
    }
}
