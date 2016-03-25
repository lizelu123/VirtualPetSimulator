package vps.main.gui;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupGUI extends JFrame implements ActionListener {

    static JMenuBar menuBar = new JMenuBar();
    public static void main(String[] args) {
       StartupGUI g = new StartupGUI();
        g.setSize(720,480);
        g.setVisible(true);
        g.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
