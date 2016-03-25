package vps.util;

import vps.main.Pet;
import vps.main.gui.PetUI;

import javax.swing.*;
import java.io.*;

/**
 * Created by jsomani on 3/23/2016.
 */
public class io implements Serializable {


    public static void replaceMainPet(String f) {
        /* Replaces the main pet with the saved version of the pet. */
        PetUI.mainPet = get(f);
    }

    public static Pet get(String f) {
        /* Gets a pet from a file. */
        Pet output = new Pet("Unknown Pet", 0, 0, 0, 0);
        try {
            try (FileInputStream fi = new FileInputStream(f)) {

                ObjectInputStream os = new ObjectInputStream(fi);

                output = (Pet) os.readObject();

                os.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to read save file.");
        }
        return output;
    }

    public static void write(String f) {
/* Writes a pet to disk. */

        try (FileOutputStream fs = new FileOutputStream(f)) {

            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(PetUI.mainPet);


            os.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to write save file.");
            e.printStackTrace();
        }
    }
}
