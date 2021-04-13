package control;

import control.Userinformation.UserInformationController;
import control.enroll.EnrollController;
import control.function.FunctionController;
import control.index.IndexController;

import static util.config.*;

public class ControllerFactory {
    public static Controller create(String name){
        switch (name){
            case INDEX_PANEL_NAME:
                return new IndexController();
            case ENROLL_PANEL_NAME:
                return new EnrollController();
            case USERDESCRIPTION_PANEL_NAME:
                return new UserInformationController();
            case FUNCTION_PANEL_NAME:
                return new FunctionController();

        }
        return null;
    }
}
