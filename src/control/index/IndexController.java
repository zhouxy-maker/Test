package control.index;

import control.Controller;
import control.MainFrame;
import model.Client;
import model.mapping.ClientMapping;
import view.IndexPanel;
import util.config;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class IndexController extends Controller {
    private IndexPanel indexPanel;

    public IndexController() {
        super(config.INDEX_PANEL_NAME, new IndexPanel());
        indexPanel = (IndexPanel) this.panel;
        indexPanel.addListener(e -> {
            if (e.getSource() == indexPanel.getLoginButton()) {
                try {
                    clean();
                    forLogin(indexPanel.getUserText().getText(), indexPanel.getPasswordText().getPassword());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == indexPanel.getRegisterButton()) {
                forRegister();
            }
        });
    }

    private void forLogin(String admin, char[] pwd) throws FileNotFoundException {
        String pin = String.valueOf(pwd);
        checkIn(admin, pin);
    }

    private void forRegister() {
        MainFrame.getInstance().goTo(config.ENROLL_PANEL_NAME);
    }

    private void clean() {
        indexPanel.getPasswordError().setText("");
        indexPanel.getNoAccount().setText("");
    }

    private void checkIn(String nickName, String password) throws FileNotFoundException {
        HashMap<String, String> map = new HashMap<>();
        map.put("nickName", nickName);
        ArrayList<Client> clients = ClientMapping.find(map);
        if (clients.isEmpty()) {
            indexPanel.getNoAccount().setText("the account is not exist");
            indexPanel.getNoAccount().setForeground(Color.red);
        } else {
            //不能登录
            if (!clients.get(0).getPassword().equals(password)) {
                indexPanel.getPasswordError().setText("your password is wrong, please try again!");
                indexPanel.getPasswordError().setForeground(Color.red);
                System.out.println("can not login");
            } else {
                MainFrame.getInstance().setClient(clients.get(0));
                MainFrame.getInstance().goTo(config.FUNCTION_PANEL_NAME);
            }
        }
    }

    @Override
    public void update() {
        System.out.println("Index update");
        clean();
        indexPanel.getPasswordText().setText("");
        indexPanel.getUserText().setText("");
    }
}
