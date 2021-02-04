package ru.sfedu.ProcessModeling.api;

import ru.sfedu.ProcessModeling.Simulation;
import javax.swing.*;
import java.awt.*;

public class ProcessField extends JPanel {
    private final Simulation processing;

    public ProcessField(Simulation processing){
        this.processing = processing;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        processing.width = (float) g.getClipBounds().getWidth();
        processing.height = (float) g.getClipBounds().getHeight();
        if(processing.rectangleActor2.collision(processing.rectangleActor3) || processing.rectangleActor3.collision(processing.rectangleActor2)){
            //processing.actors.clear();
            return;
        }
        for(Actor actor : processing.actors)
            actor.draw(g);
    }
}
