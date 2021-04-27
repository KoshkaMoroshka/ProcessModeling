package ru.sfedu.ProcessModeling;

import ru.sfedu.ProcessModeling.api.Simulation;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String arg[]) throws IOException, AWTException {
        Simulation simulation = new Simulation(800, 800);
        simulation.start();
    }
}