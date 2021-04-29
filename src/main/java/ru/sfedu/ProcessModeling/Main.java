package ru.sfedu.ProcessModeling;

import ru.sfedu.ProcessModeling.application.TestAnimation;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String arg[]) throws IOException, AWTException {
        //Simulation simulation = new Simulation(800, 800, 20);
        //simulation.start();

        TestAnimation testAnimation = new TestAnimation(600, 800, 440);
        testAnimation.start();
    }
}