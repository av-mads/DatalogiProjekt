package Bartinator.Controller;

import Bartinator.Database.MainDAO;
import Bartinator.Model.Consumer;
import javafx.scene.control.TextField;

/**
 * Created by Casper on 26-10-2016.
 */
public class CreateConsumerController {
    public TextField textFieldName;
    public TextField textFieldUserName;
    public TextField textFieldPassword;
    public TextField textFieldBalance;
    public TextField textFieldStudentID;
    // navn(string), username(string), password(int pga. hash), balance(double),  studentid(int). note til casper

    public void handlePrintTest(){
        System.out.println(textFieldUserName.getText());
    }

    public void handleCreateConsumer() {
        try {
            Consumer testConsumer = new Consumer();
            testConsumer.setName(textFieldName.getText());
            testConsumer.setUsername(textFieldUserName.getText());
            testConsumer.setPassword(textFieldPassword.getText().hashCode());
            testConsumer.setBalance(Double.parseDouble(textFieldBalance.getText()));
            testConsumer.setStudentID(Integer.parseInt(textFieldStudentID.getText()));
            MainDAO.save(testConsumer);
        }catch (Exception e) {
            System.err.println("Failed to load create consumer!");
            e.printStackTrace();
        }
    }


}