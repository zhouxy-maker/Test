package control;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import control.function.FunctionController;
import model.Client;
import util.config;

public class MainFrame extends JFrame{
    private static MainFrame mainFrame;
    private HashMap<String, Controller> list;
    private Client client;

    private MainFrame() {
        initialize();
    }

    public static MainFrame getInstance() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    private void initialize() {
        this.list = new HashMap<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CourseWork");
        this.setSize(config.FRAME_WIDTH, config.FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setVisible(true);
    }

    public void registerObserver(String name, Controller o) {
        list.put(name, o);
    }

    public void removeObserver(String name) {
        list.remove(name);
    }

    private void notifyObserver() {
        list.forEach((k, v) -> v.update());
    }

    public void setClient(Client client) {
        this.client = client;
        notifyObserver();
    }

    public Client getClient() {
        return client;
    }

    public HashMap<String, Controller> getList() {
        return list;
    }

    public void goTo(String name){
        ArrayList<String> firstGate = new ArrayList<>(Arrays.asList(
                config.INDEX_PANEL_NAME, config.FUNCTION_PANEL_NAME, config.ENROLL_PANEL_NAME));
        // 注册，登录，功能页面跳转用此方法
        if(firstGate.contains(name)) {
            list.forEach((k, v) -> v.getPanel().setVisible(false));
            Controller controller = list.get(name);
            // 如果还没有该页面，创造该页面
            if(controller == null ) {
                controller = ControllerFactory.create(name);
                this.add(controller.getPanel());
            }
            else {
                controller.update();
            }
            controller.getPanel().setVisible(true);
        }
        // 功能页面内的跳转用功能页面的跳转方法
        else{
            FunctionController functionController = (FunctionController) list.get(config.FUNCTION_PANEL_NAME);
            functionController.goTo(name);
        }
    }
}
