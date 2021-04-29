package ru.sfedu.ProcessModeling.application;

import ru.sfedu.ProcessModeling.api.Simulation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends Simulation {

    public MainMenu(int width, int height, int timeToChangeFrame) {
        super(width, height, timeToChangeFrame);
    }

    @Override
    public void createInitialObject() throws IOException {
        BufferedImage image_up = ImageIO.read(new File("G:\\f\\app\\src\\main\\res\\drawable-nodpi\\action_up.png"));
        BufferedImage image_down = ImageIO.read(new File("G:\\f\\app\\src\\main\\res\\drawable-nodpi\\action_down.png"));
        JButton button = new JButton(new ImageIcon(image_up));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setLayout(null);
        button.setBounds(60, 30, image_up.getWidth(), image_up.getHeight());
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setIcon(new ImageIcon(image_down));
            }
        }
    );
        buttons.add(button);
    }
}
