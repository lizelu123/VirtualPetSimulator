package vps.main.gui;

import vps.main.Pet;
import vps.util.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jsomani on 3/23/2016.
 */
public class PetUI extends JFrame implements ActionListener {
     public static Pet mainPet = new Pet("Unknown Pet", 100, 100, 100, 100);
     JProgressBar healthBar = new JProgressBar(0, 100);
     JProgressBar hungerBar = new JProgressBar(0, 100);
     JProgressBar happinessBar = new JProgressBar(0, 100);
    JMenuBar menuBar = new JMenuBar();
    JLabel petInfo = new JLabel("Name:" + mainPet.getName() + ", Age: "+mainPet.getAge() + ", MedPack: "+mainPet.getMedpacks() + ", Food: " +mainPet.getFood());
    JMenu file = new JMenu("File");
    JMenuItem save = new JMenu("Save");
    JButton healButton = new JButton("", new ImageIcon("src\\vps\\files\\ambulanceIcon.png"));
    JButton feedButton = new JButton("", new ImageIcon("src\\vps\\files\\feedIcon.png"));
    JButton playButton = new JButton("", new ImageIcon("src\\vps\\files\\playIcon.png"));
    JButton quitButton = new JButton("", new ImageIcon("src\\vps\\files\\quitIcon.png"));
    JPanel buttons = new JPanel();
    JPanel bars = new JPanel();
    public Thread game = new Thread(){
        public void run() {
            while (mainPet.isAlive()) {
                petInfo.setText("Name:" + mainPet.getName() + ", Age: "+mainPet.getAge() + ", MedPack: "+mainPet.getMedpacks() + ", Food: " +mainPet.getFood());
                happinessBar.setValue((int) Math.round(mainPet.getHappiness()));
                healthBar.setValue((int) Math.round(mainPet.getHealth()));
                hungerBar.setValue((int) Math.round(mainPet.getHunger()));
                mainPet.setHappiness(mainPet.getHappiness() - 0.1);
                mainPet.setHunger(mainPet.getHunger() - 0.5);
                //color set
                /**
                 * if val greater than 75 = great
                 * if val less than 75 = good
                 * if val less than 50 = OK
                 * if val less than 25 = bad
                 * if val less than or equ 0 = dead/horrible
                 */

                if (mainPet.getHappiness() > 75) {
                    happinessBar.setString("Ecstatic");
                    happinessBar.setForeground(Color.green);
                    mainPet.setMoney(mainPet.getMoney()+1);
                }
                if (mainPet.getHappiness() <= 75) {
                    happinessBar.setString("Happy");
                    happinessBar.setForeground(Color.magenta);
                }
                if (mainPet.getHappiness() <= 50) {
                    happinessBar.setString("OK");
                    happinessBar.setForeground(Color.orange);
                }
                if (mainPet.getHappiness() <= 25) {
                    happinessBar.setString("Sad");
                    happinessBar.setForeground(Color.red);
                }
                if (mainPet.getHappiness() <= 0) {
                    happinessBar.setString("Horrible");
                    happinessBar.setForeground(Color.black);

                }

                if (mainPet.getHealth() > 75) {
                    healthBar.setString("Great");
                    healthBar.setForeground(Color.green);
                }
                if (mainPet.getHealth() <= 75) {
                    healthBar.setString("Good");
                    healthBar.setForeground(Color.magenta);
                }
                if (mainPet.getHealth() <= 50) {
                    healthBar.setString("OK");
                    healthBar.setForeground(Color.orange);
                }
                if (mainPet.getHealth() <= 25) {
                    healthBar.setString("Requires attention");
                    healthBar.setForeground(Color.red);
                }
                if (mainPet.getHealth() <= 0) {
                    healthBar.setString("Death");
                    healthBar.setForeground(Color.black);
                    JOptionPane.showMessageDialog(null, "Your pet: " + mainPet.getName() + " has died.");
                    dispose();
                    StartGUI.main(null);
                    break;



                }

                if (mainPet.getHunger() > 75) {
                    hungerBar.setString("Full");
                    hungerBar.setForeground(Color.green);
                }
                if (mainPet.getHunger() <= 75) {
                    hungerBar.setString("Great");
                    hungerBar.setForeground(Color.magenta);
                }
                if (mainPet.getHunger() <= 50) {
                    hungerBar.setString("Hungry");
                    hungerBar.setForeground(Color.orange);
                }
                if (mainPet.getHunger() <= 25) {
                    hungerBar.setString("Empty");
                    hungerBar.setForeground(Color.red);
                }
                if (mainPet.getHunger() <= 0) {
                    hungerBar.setString("Starving");
                    hungerBar.setForeground(Color.black);
                }


                //death values
                if (mainPet.getHunger() <= 5) {
                    mainPet.setHealth(mainPet.getHealth() - 1);
                }
                if (mainPet.getHealth() <= 45) {
                    mainPet.setHappiness(mainPet.getHappiness() - 1);
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


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
        save.addActionListener(this);
        add(petInfo);
        add(buttons);
        add(bars);
        menuBar.add(file);
        file.add(save);


    }


    public void main() {
        PetUI p = new PetUI();
        p.setJMenuBar(menuBar);
        p.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        p.setSize(640, 480);
        p.setVisible(true);
        io.replaceMainPet(StartGUI.readPath);
        p.game.start();
        }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            int quit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?"); // ask if you want to quit
            if (quit == 0) {
                System.exit(0); //exit
            } else {
               //do nothing
            }
        } else if (e.getSource() == feedButton) {
            if (mainPet.getFood() <= 0) { //check if out of food
                JOptionPane.showMessageDialog(null, "You are out of food.");
            } else if (mainPet.getHunger() > 100) { //check if you need food
                JOptionPane.showMessageDialog(null, "You do not need food right now.");
            } else {
                mainPet.setHunger(mainPet.getHunger() + 20);
                mainPet.setFood(mainPet.getFood() - 1);

            }
        } else if (e.getSource() == healButton) {
            if (mainPet.getMedpacks() <= 0) {
                JOptionPane.showMessageDialog(null, "You are out of medpacks.");
            } else if (mainPet.getHealth() > 100) {
                JOptionPane.showMessageDialog(null, "You do not need a medpack right now.");
            } else {

               if(mainPet.getHealth() + 20 >= 100) {
                   JOptionPane.showMessageDialog(null, "You do not need a medpack right now.");
               }
                else {
                   mainPet.setMedpacks(mainPet.getMedpacks() - 1);
               }
            }

        }
        else if(e.getSource() == save) {
            io.write(StartGUI.readPath);
        }

    }
}