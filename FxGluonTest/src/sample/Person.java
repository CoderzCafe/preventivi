package sample;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private CheckBox checkBox;
    private Button previewButton;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.checkBox = new CheckBox();
        this.previewButton = new Button("", new ImageView(new Image(getClass().getResource("/res/preview.png").toString())));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public Button getPreviewButton() {
        return previewButton;
    }

    public void setPreviewButton(Button previewButton) {
        this.previewButton = previewButton;
    }
}
