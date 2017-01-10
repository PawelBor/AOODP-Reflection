package ie.gmit.sw;

import javax.swing.*;

public class Runner {

    public static void main(String[] args) {
            // schedule this for the event dispatch thread (edt)
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                        FacePanel.displayJFrame();

                    } //run
            });
        } //main
} //runner