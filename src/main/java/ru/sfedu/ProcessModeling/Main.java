package ru.sfedu.ProcessModeling;

import ru.sfedu.ProcessModeling.api.Simulation;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String arg[]) throws IOException, AWTException {
        Simulation simulation = new Simulation(800, 800, 20);
        simulation.start();

        //TestAnimation testAnimation = new TestAnimation(600, 800, 20);
        //testAnimation.start();

        //MainMenu menu = new MainMenu(300, 300, 20) ;
        //menu.start();
    }
}