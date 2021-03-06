package view.function;

import javax.swing.*;

public class InfoButton extends JButton {
    private String key;

    public InfoButton(String viewName, String key, int index){
        super(viewName);

        int INFO_BUTTON_X = 100;
        int INFO_BUTTON_Y = 10;
        int INFO_BUTTON_WIDTH = 150;
        int INFO_BUTTON_HEIGHT = 30;

        this.setBounds(INFO_BUTTON_X + INFO_BUTTON_WIDTH * index, INFO_BUTTON_Y, INFO_BUTTON_WIDTH, INFO_BUTTON_HEIGHT);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
