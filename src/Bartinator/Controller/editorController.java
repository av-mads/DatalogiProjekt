package Bartinator.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Gamer on 14-09-2016.
 */
public class editorController {

    public Button addItemBtn;
    public VBox btnContainer;
    public Button removeItemBtn;
    int btnCount = 0;
    ArrayList<Button> btnList = new ArrayList<Button>();

    public void addItemHandler(ActionEvent actionEvent) {
        Button b = new Button("hallo manner");
        btnContainer.getChildren().add(b);
        btnList.add(b);
        setBtnPreference();
    }

    public void setBtnPreference(){
        for (int i = 0; i < btnList.size(); i++) {
            btnList.get(i).setMinSize(100,100);
        }
    }
}
