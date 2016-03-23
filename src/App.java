import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class StartGUI extends JFrame implements ActionListener {
    static JMenuBar mb = new JMenuBar();
    static JMenu file = new JMenu("File");
    static JMenuItem newSave = new JMenuItem("New Save");
    static JMenuItem openSave = new JMenuItem("Open Save");
    static JButton play = new JButton("Play");
    static JButton help = new JButton("Help");
    public static String filename = "";
    public static String readPath = "";
    JFileChooser jf = new JFileChooser();

    public StartGUI() {
        super("Virtual Pet Simulator");
        setLayout(new GridLayout());
        play.addActionListener(this);
        help.addActionListener(this);
        file.addActionListener(this);
        jf.addActionListener(this);
        newSave.addActionListener(this);
        openSave.addActionListener(this);
        openSave.setIcon(new ImageIcon("src\\icons\\folder_open_16.gif"));
        newSave.setIcon(new ImageIcon("src\\icons\\properties_doc_16.gif"));
        file.add(newSave);
        file.add(openSave);
        mb.add(file);
        add(play);
        add(help);


    }

    public static void main(String[] args) {

        StartGUI gui = new StartGUI();
        gui.setVisible(true);
        gui.setSize(320, 240);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setJMenuBar(mb);
        gui.setResizable(false);
        gui.setLocationRelativeTo(null); //center it

    }


    public static String getReadPath() {
        return readPath;
    }

    public static void setReadPath(String readPath) {
        StartGUI.readPath = readPath;
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        StartGUI.filename = filename;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == openSave) {
                jf.setDialogTitle("Open Save");
                jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jf.setVisible(true);
                jf.showOpenDialog(this);
                setReadPath(jf.getSelectedFile().getPath());


            } else if (e.getSource() == newSave) {

                filename = JOptionPane.showInputDialog(null, "Save name?");
                JOptionPane.showMessageDialog(null, "Please note that this will overwrite any files with the same name and extension");
                jf.setDialogTitle("Select Save Location");
                jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jf.setVisible(true);
                jf.showOpenDialog(this);
                App.mainPet.save(jf.getSelectedFile().getPath() + "\\" + filename + ".bin");

                JOptionPane.showMessageDialog(null, "Save data saved to: " + jf.getSelectedFile().getPath() + "\\" + filename + ".bin");
                setReadPath(jf.getSelectedFile().getPath() + "\\" + filename + ".bin");

            } else if (e.getSource() == help) {
                JOptionPane.showMessageDialog(null, "Not done yet");

            } else if (e.getSource() == play) {
                if (getReadPath().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select/open save location\nFile --> New Save/Open Save");
                } else {
                    dispose();
                    App.main(null);
                    //run
                }
            }
        } catch (Exception ex) {
            if (ex.getMessage() != null) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                ex.printStackTrace();
            } else {

                JOptionPane.showMessageDialog(null, "Unknown error - requires restart");
                ex.printStackTrace();
            }
        }
    }

}


class Pet implements Serializable {
    String name;
    static String keepDate;
    int age;
    double health;
    double hunger;

    public Pet(String name, int age, int health, int hunger, int happiness) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.hunger = hunger;
        this.happiness = happiness;
    }

    double happiness;
    boolean dynamicExpansion;
    DateFormat dateFormat = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
    String lastDate = dateFormat.format(Calendar.getInstance().getTime());
    Thread dateCheck;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }


    public String toString() {
        return "//Version 1 savefile \nPet [isDynamicAge: "+ isDynamicExpansion()+", LD: " + getLastDate() + ", NAME: " + getName() + ", AGE: " + getAge() + "]";
    }

    public static void save(String path) {
        try (FileOutputStream fs = new FileOutputStream(path)) {
            keepDate = App.mainPet.lastDate;
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(App.mainPet);
            App.mainPet.lastDate = keepDate;
            os.close();



        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to save data.");
        }

    }

    public boolean isDynamicExpansion() {
        return dynamicExpansion;
    }

    public void setDynamicExpansion(boolean dynamicExpansion) {
        this.dynamicExpansion = dynamicExpansion;
    }

    public static void read() {
        try (FileInputStream fi = new FileInputStream(StartGUI.readPath)) {

            ObjectInputStream os = new ObjectInputStream(fi);

            Pet data = (Pet) os.readObject();

            os.close();


            App.mainPet.setAge(data.getAge());
            App.mainPet.setLastDate(data.getLastDate());
            App.mainPet.setName(data.getName());
            App.mainPet.setDynamicExpansion(data.dynamicExpansion);



        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to read save data. Please retry.");
            System.exit(-1);
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    public static void saveAll() {
        save(StartGUI.readPath);
    }

    public void startDynamicallyExpandingAge() {
        // STOPSHIP: 3/20/2016 starts expanding age
        //Uses time and files
        dynamicExpansion = true;
        dateCheck = new Thread() {
            public void run() {
                Calendar compare_date = Calendar.getInstance();
                compare_date.add(Calendar.DATE, 1);
                final String FINAL_COMPARE_DATE = dateFormat.format(compare_date.getTime());
                while (true) {

                    if (lastDate.compareTo(FINAL_COMPARE_DATE) >= 0) { //if a day has passed since last save
                        JOptionPane.showMessageDialog(null, "Pet age increased! Your pet is now " + age + "years old!");
                        age++;
                        keepDate = dateFormat.format(Calendar.getInstance().getTime());
                    }
                    try {
                        dateCheck.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        dateCheck.start(); //starts another thread to keep updating age
    }


    public void stopDynamicallyExpandingAge(Pet p) {
        // STOPSHIP: 3/20/2016 stops expanding age
        dynamicExpansion = false;
        System.out.println("Your pet is no longer growing. Resume growing by pressing Pet --> Grow Settings.");
        dateCheck.stop(); //Using a deprecated function you shouldn't do but this is STOPSHIP.

    }

}
class App_Shop extends JFrame implements ActionListener {
    public double money = 5000;
    JLabel moneyText = new JLabel("Money: $"+money);
    static JMenuBar menu = new JMenuBar();
    JMenu buyMenu = new JMenu("Buy");
    JMenuItem buyToy = new JMenuItem("Buy toys");
    JMenuItem buyFood = new JMenuItem("Buy foods");
    JMenu sellMenu = new JMenu("Sell");
    JMenuItem sellToy = new JMenuItem("Sell toys");
    JMenuItem sellFood = new JMenuItem("Sell foods");
    JButton buy = new JButton("Buy");
    JButton sell = new JButton("Sell");
    public App_Shop() {
        super("Shop");
        setLayout(new GridLayout());
        //add(moneyText);
        add(buy);
        add(sell);
        menu.add(buyMenu);
        menu.add(sellMenu);
        sellMenu.add(sellToy);
        sellMenu.add(sellFood);
        buyMenu.add(buyToy);
        buyMenu.add(buyFood);
        sellToy.addActionListener(this);
        sellFood.addActionListener(this);
        buyToy.addActionListener(this);
        buyFood.addActionListener(this);

    }

    public static void main(String[] args) {
        App_Shop a = new App_Shop();
        a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        a.setJMenuBar(menu);
        a.pack();
        a.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buyToy) {

        }
    }

}

public class App extends JFrame implements ActionListener {

    static Pet mainPet = new Pet("Default Pet", 0, 100, 40, 65); //name, age, health, hunger, happy
    static JProgressBar health = new JProgressBar(0,100);
    static JProgressBar hunger = new JProgressBar(0,100);
    static JProgressBar happiness = new JProgressBar(0,100);
    static JButton feed = new JButton("Feed");
    static JButton play = new JButton("Play");


    public App() {
        super("Virtual Pet Simulator");
        setLayout(new FlowLayout());
        health.setStringPainted(true);
        health.setValue((int)Math.round(mainPet.getHealth()));
        health.setString("Health");
        hunger.setStringPainted(true);
        hunger.setString("Hunger");
        hunger.setValue((int)Math.round(mainPet.getHunger()));
        happiness.setStringPainted(true);
        happiness.setString("Happiness");
        happiness.setValue((int)Math.round(mainPet.getHappiness()));
        add(health);
        add(hunger);
        add(happiness);
    }

    public static void main(String[] args) {
        App a = new App();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.pack();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        while (true) {
            happiness.setValue((int)Math.round(mainPet.happiness));
            health.setValue((int)Math.round(mainPet.health));
            hunger.setValue((int)Math.round(mainPet.hunger));
            mainPet.setHappiness(mainPet.getHappiness()-0.1);
            mainPet.setHunger(mainPet.getHunger()-0.5);
            //color set
            /**
             * if val greater than 75 = great
             * if val less than 75 = good
             * if val less than 50 = OK
             * if val less than 25 = bad
             * if val less than or equ 0 = dead/horrible
             */

        if(mainPet.getHappiness() > 75) {
            happiness.setString("Ecstatic");
            happiness.setForeground(Color.green);
        }
        if(mainPet.getHappiness() <= 75) {
            happiness.setString("Happy");
            happiness.setForeground(Color.magenta);
        }
        if(mainPet.getHappiness() <= 50) {
            happiness.setString("OK");
            happiness.setForeground(Color.orange);
        }
        if(mainPet.getHappiness() <= 25) {
            happiness.setString("Sad");
            happiness.setForeground(Color.red);
        }
        if(mainPet.getHappiness() <= 0) {
            happiness.setString("Horrible");
            happiness.setForeground(Color.black);

        }

            if(mainPet.getHealth() > 75) {
                health.setString("Great");
                health.setForeground(Color.green);
            }
            if(mainPet.getHealth() <= 75) {
                health.setString("Good");
                health.setForeground(Color.magenta);
            }
            if(mainPet.getHealth() <= 50) {
                health.setString("OK");
                health.setForeground(Color.orange);
            }
            if(mainPet.getHealth() <= 25) {
                health.setString("Requires attention");
                health.setForeground(Color.red);
            }
            if(mainPet.getHealth() <= 0) {
                health.setString("Death");
                health.setForeground(Color.black);
            }
            
            if(mainPet.getHunger() > 75) {
                hunger.setString("Full");
                hunger.setForeground(Color.green);
            }
            if(mainPet.getHunger() <= 75) {
                hunger.setString("Great");
                hunger.setForeground(Color.magenta);
            }
            if(mainPet.getHunger() <= 50) {
                hunger.setString("Hungry");
                hunger.setForeground(Color.orange);
            }
            if(mainPet.getHunger() <= 25) {
                hunger.setString("Empty");
                hunger.setForeground(Color.red);
            }
            if(mainPet.getHunger() <= 0) {
                hunger.setString("Starving");
                hunger.setForeground(Color.black);
            }


            //death values
            if(mainPet.hunger <= 5) {
                mainPet.setHealth(mainPet.getHealth()-1);
            }
            if(mainPet.health <= 45) {
                mainPet.setHappiness(mainPet.getHappiness()-1);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

