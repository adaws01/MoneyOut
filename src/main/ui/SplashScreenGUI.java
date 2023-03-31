package ui;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class SplashScreenGUI extends AbstractGUI{

    JLabel label = new JLabel();
    String splashImageLocation = "./lib/MoneyOutSplash.jpg";

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
