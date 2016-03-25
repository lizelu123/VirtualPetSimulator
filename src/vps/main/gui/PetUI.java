package vps.main.gui;

import vps.main.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jsomani on 3/23/2016.
 */
public class PetUI extends JFrame implements ActionListener {
    public static Pet mainPet = new Pet("Unknown Pet", 100, 100, 100, 100);
    static JProgressBar healthBar = new JProgressBar(0, 100);
    static JProgressBar hungerBar = new JProgressBar(0, 100);
    static JProgressBar happinessBar = new JProgressBar(0, 100);
    static JLabel foodCount = new JLabel();
    static JLabel medpackCount = new JLabel();
    static int medpacks = 10;
    static int food = 25;
    JButton healButton = new JButton("", new ImageIcon("src\\vps\\files\\ambulanceIcon.png"));
    JButton feedButton = new JButton("", new ImageIcon("src\\vps\\files\\feedIcon.png"));
    JButton playButton = new JButton("", new ImageIcon("src\\vps\\files\\playIcon.png"));
    JButton quitButton = new JButton("", new ImageIcon("src\\vps\\files\\quitIcon.png"));
    JPanel buttons = new JPanel();
    JPanel bars = new JPanel();

    public PetUI() {
        super("Pet UI");
        setLayout(new FlowLayout());
        buttons.add(healButton);
        buttons.add(feedButton);
        buttons.add(playButton);
        buttons.add(quitButton);
        bars.add(healthBar);
        bars.add(hungerBar);
        bars.add(happinessBar);
        happinessBar.setStringPainted(true);
        hungerBar.setStringPainted(true);
        healthBar.setStringPainted(true);
        happinessBar.setString("Happiness");
        hungerBar.setString("Hunger");
        healthBar.setString("Health");
        healButton.setBorderPainted(false);
        healButton.setContentAreaFilled(false);
        healButton.setFocusPainted(false);
        feedButton.setBorderPainted(false);
        feedButton.setContentAreaFilled(false);
        feedButton.setFocusPainted(false);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        playButton.addActionListener(this);
        healButton.addActionListener(this);
        feedButton.addActionListener(this);
        quitButton.addActionListener(this);
        add(medpackCount);
        add(buttons);
        add(foodCount);
        add(bars);


    }

    public static int getMedpacks() {
        return medpacks;
    }

    public static void setMedpacks(int medpacks) {
        PetUI.medpacks = medpacks;
    }

    public static void main(String[] args) {
        PetUI p = new PetUI();
        p.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        p.setSize(640, 480);
        p.setVisible(true);


    }

    public static int getFood() {
        return food;
    }

    public static void setFood(int food) {
        PetUI.food = food;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            int quit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
            if (quit == 0) {
                System.exit(0);
            } else {
                //do nothing, just stay
            }
        } else if (e.getSource() == feedButton) {
            if (getFood() <= 0) {
                JOptionPane.showMessageDialog(null, "You are out of food.");
            } else if (mainPet.getHunger() > 100) {
                JOptionPane.showMessageDialog(null, "You do not need food right now.");
            } else {
                mainPet.setHunger(mainPet.getHunger() + 20);
                setFood(getFood() - 1);

            }
        } else if (e.getSource() == healButton) {
            if (getMedpacks() <= 0) {
                JOptionPane.showMessageDialog(null, "You are out of medpacks.");
            } else if (mainPet.getHealth() > 100) {
                JOptionPane.showMessageDialog(null, "You do not need a medpack right now.");
            } else {

               if(mainPet.getHealth() + 20 >= 100) {
                   JOptionPane.showMessageDialog(null, "You do not need a medpack right now.");
               }
                else {
                   setMedpacks(getMedpacks() - 1);
               }
            }
        }

    }
}