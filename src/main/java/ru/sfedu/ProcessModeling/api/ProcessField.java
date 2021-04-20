package ru.sfedu.ProcessModeling.api;

import javax.swing.*;
import java.awt.*;

public class ProcessField extends JPanel {
    private final Simulation processing;

    public ProcessField(Simulation processing){
        this.processing = processing;
    }
    int i = 0;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        processing.width = (float) g.getClipBounds().getWidth();
        processing.height = (float) g.getClipBounds().getHeight();
        for(Actor actor : processing.actors)
            actor.draw(g);
        }

}

