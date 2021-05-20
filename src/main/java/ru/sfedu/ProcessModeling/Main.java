package ru.sfedu.ProcessModeling;

import ru.sfedu.ProcessModeling.application.GameScene;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String arg[]) throws IOException, AWTException {
        //Simulation simulation = new Simulation(800, 800, 40);
        //simulation.start();

        //TestAnimation testAnimation = new TestAnimation(1000, 600, 20);
        //testAnimation.start();

        //MainMenu menu = new MainMenu(600, 600, 1) ;
        //menu.start();

        //CrashTestScene scene = new CrashTestScene(2000, 1000, 20);
        //scene.start();

        GameScene gameScene = new GameScene(1200, 720, 20);
        gameScene.start();
    }
}