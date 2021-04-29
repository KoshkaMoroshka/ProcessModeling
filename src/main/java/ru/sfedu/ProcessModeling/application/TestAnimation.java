package ru.sfedu.ProcessModeling.application;

import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.typeActors.AnimatedActor;
import ru.sfedu.ProcessModeling.api.typeActors.VideoRecorder;

import java.io.IOException;

public class TestAnimation extends Simulation {

    public TestAnimation(int width, int height, int timeToChangeFrame) {
        super(width, height, timeToChangeFrame);
    }

    @Override
    public void createInitialObject() throws IOException {
        AnimatedActor canterHorse = new AnimatedActor();
        canterHorse.createAnimatedActor(this, 100, 150, 1, "D:\\AnimatedHorse\\canter");

        AnimatedActor trotHorse = new AnimatedActor();
        trotHorse.createAnimatedActor(this, 300, 150, 1, "D:\\AnimatedHorse\\trot");

        AnimatedActor gallopHorse = new AnimatedActor();
        gallopHorse.createAnimatedActor(this, 100, 350, 1, "D:\\AnimatedHorse\\gallop");

        AnimatedActor walkHorse = new AnimatedActor();
        walkHorse.createAnimatedActor(this, 300, 350, 1, "D:\\AnimatedHorse\\walk");

        AnimatedActor jumpHorse = new AnimatedActor();
        jumpHorse.createAnimatedActor(this, 200, 500, 1, "D:\\AnimatedHorse\\jump");

        VideoRecorder videoRecorder = new VideoRecorder(this, this.windowWidth, this.windowHeight,
                                            "D:\\AnimatedHorse\\horseMove.mp4", 3);


        actors.add(canterHorse);
        actors.add(trotHorse);
        actors.add(gallopHorse);
        actors.add(walkHorse);
        actors.add(jumpHorse);
        actors.add(videoRecorder);
    }
}
