package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Stage stage;
    @FXML
    private Label label;
    @FXML
    private Button openWindow;
    @FXML
    private ImageView uploadImageView;
    @FXML
    private Button uploadImageButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn firstNameCol;
    @FXML
    private TableColumn lastNameCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn selectCol;
    @FXML
    private TableColumn previewCol;
    @FXML
    private Button delectSelectRow;

    private ObservableList<Person> list = FXCollections.observableArrayList();


    @FXML
    void uploadImageAction(ActionEvent e) throws NullPointerException {

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file == null) {
            System.out.println("Cancel button is clicked");
        } else {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                uploadImageView.setImage(image);
                uploadImageView.setFitHeight(200);
                uploadImageView.setFitWidth(200);
                uploadImageView.setPreserveRatio(false);
                System.out.println(file.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @FXML
    void openWindowAction(ActionEvent e) throws IOException{
        openNewWindow("window.fxml", e);
    }

    @FXML
    void mouseAction(MouseEvent e) throws IOException{
        openNewWindow("window.fxml", e);
    }

    @FXML
    void deleteSelectedRowAction(ActionEvent event) {
        ObservableList<Person> removeData = FXCollections.observableArrayList();

        for (Person bean: list) {
            if (bean.getCheckBox().isSelected()) {
                removeData.add(bean);
            }
        }
        list.removeAll(removeData);
    }

    private void openNewWindow(String name, Event e) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(name));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.close();
        window.setScene(scene);
        window.show();
    }

    private void closeWindow() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Person> list = getData();

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        selectCol.setCellValueFactory(new PropertyValueFactory<Person, String>("checkBox"));
        previewCol.setCellValueFactory(new PropertyValueFactory<Person, String>("previewButton"));

        table.setItems(list);

    }

    private ObservableList<Person> getData() {
        Person p1 = new Person("Sudipto", "shine", "sudipto.shine@gmail.com");
        Person p2 = new Person("Rahim", "Sekh", "rahim@gmail.com");
        Person p3 = new Person("Abdul", "Karim", "karim@gmail.com");
        Person p4 = new Person("Arif", "Islam", "arif@gmail.com");
        list.addAll(p1, p2, p3, p4);
        return list;
    }
}
