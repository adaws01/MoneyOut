package ui;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Splash Screen. Image displayed at startup. Proceeds to main menu
 */

public class SplashScreenGUI extends AbstractGUI {

    //JAVA Swing UI Elements
    JLabel label = new JLabel();
    String splashImageLocation = "./lib/MoneyOutSplash.jpg";

    //EFFECTS: Window setup for Splash Screen. Calls Main window after displaying for 3 seconds
    public SplashScreenGUI() throws InterruptedException {
        windowSetup();

        label.setIcon(new ImageIcon(splashImageLocation));

        frame.add(label);
        frameSetup();

        TimeUnit.SECONDS.sleep(3);

        frame.dispose();
        new MoneyOutAppGUI();
    }

}
