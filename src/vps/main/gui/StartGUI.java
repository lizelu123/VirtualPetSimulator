package vps.main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vps.util.io;


/**
 * Created by jsomani on 3/25/2016.
 */
public class StartGUI extends JFrame implements ActionListener {
    static final double GAME_VERSION = 1.5;
    public static String readPath = "";
    static JMenuBar menuBar = new JMenuBar();
    JLabel vpstitle = new JLabel("VPS Alpha " + getGameVersion(), JLabel.CENTER);
    JMenu fileMenu = new JMenu("File");
    JMenuItem newSave = new JMenuItem("New Save");
    JMenuItem openSave = new JMenuItem("Open Save");
    JButton play = new JButton("Play");
    JButton help = new JButton("Help");
    PetUI p = new PetUI();

    public StartGUI() {
        super("Startup");
        setLayout(new GridLayout(3, 3));
        add(vpstitle);
        add(play);
        add(help);
        play.addActionListener(this);
        help.addActionListener(this);
        menuBar.add(fileMenu);
        fileMenu.add(newSave);
        fileMenu.add(openSave);
        newSave.addActionListener(this);
        openSave.addActionListener(this);
        vpstitle.setFont(new Font("Segoe Print", Font.BOLD, 72));

    }

    public static void main(String[] args) {
        StartGUI s = new StartGUI();
        s.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        s.setSize(720, 480);
        s.setJMenuBar(menuBar);
        s.setVisible(true);
    }

    public static String getReadPath() {
        return readPath;
    }

    public static void setReadPath(String readPath) {
        StartGUI.readPath = readPath;
    }

    public static double getGameVersion() {
        return GAME_VERSION;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == help) {
            JOptionPane.showMessageDialog(null, "Virtual Pet Simulator.\nCurrent version: "+ getGameVersion());
        }
        else if (e.getSource() == play) {
            try {
                if (readPath.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Please select a save file.\nFile --> Open Save / New Save");

                } else {
                    p.main();
                }
            } catch (NullPointerException n) {
                JOptionPane.showMessageDialog(null, "You didn't choose a file.");
            }
        } else if (e.getSource() == newSave) {
            String saveName = JOptionPane.showInputDialog(null, "Please name this save file:");
            JOptionPane.showMessageDialog(null, "Select your new save directory.");
            JFileChooser jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jf.showOpenDialog(null);
            readPath = jf.getSelectedFile().getAbsolutePath() + "\\" + saveName + ".bin";
            String petName = JOptionPane.showInputDialog(null, "Please name your pet:");
            p.mainPet.setName(petName);
            io.write(readPath);

        } else if (e.getSource() == openSave) {
            JOptionPane.showMessageDialog(null, "Press OK to select your previous save file.");
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(null);
           setReadPath(jf.getSelectedFile().getAbsolutePath());

        }
    }
}

