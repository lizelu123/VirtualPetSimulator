package vps.main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vps.util.io;
import vps.util.io.*;
/**
 * Created by jsomani on 3/25/2016.
 */
public class StartGUI extends JFrame implements ActionListener {
    public static String readPath = "";
    static JMenuBar menuBar = new JMenuBar();
    JLabel vpstitle = new JLabel("VPS Alpha 1.2",JLabel.CENTER);
    JMenu fileMenu = new JMenu("File");
    JMenuItem newSave = new JMenuItem("New Save");
    JMenuItem openSave = new JMenuItem("Open Save");
    JButton play = new JButton("Play");
    JButton help = new JButton("Help");

    public StartGUI() {
        super("Startup");
        setLayout(new GridLayout(3,3));
        add(vpstitle);
        add(play);
        add(help);
        play.addActionListener(this);
        menuBar.add(fileMenu);
        fileMenu.add(newSave);
        fileMenu.add(openSave);
        vpstitle.setFont(new Font ("Segoe Print", Font.BOLD , 72));

    }
    public static void main(String[] args) {
        StartGUI s = new StartGUI();
        s.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        s.setSize(720,480);
        s.setJMenuBar(menuBar);
        s.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play) {
            try {
                if (readPath.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Please select a save file.\nFile --> Open Save / New Save");
                    JFileChooser jf = new JFileChooser();
                    jf.showOpenDialog(null);
                    readPath = jf.getSelectedFile().getAbsolutePath();
                    io.replaceMainPet(readPath);

                }
            }
                catch(NullPointerException n) {
                    JOptionPane.showMessageDialog(null,"You didn't choose a file.");
                }
            }
        }
    }

