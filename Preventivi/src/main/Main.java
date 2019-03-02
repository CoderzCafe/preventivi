package main;

import com.dropbox.core.DbxException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.model.Dropbox;
import org.controlsfx.control.Notifications;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/mainview.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ui/addingproducts.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ui/productsquotation.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ui/admin.fxml"));
        primaryStage.setTitle("Preventivi");
        primaryStage.setScene(new Scene(root, 1022, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws DbxException {
        Application.launch(args);

        /** when the program is run the
         * products file is saved in
         * the data directory **/
        File file = new File(Dropbox.FILE_PATH+"products.xlsx");
        if (!file.exists()) {
            Dropbox dropbox = new Dropbox();
            dropbox.downloadFile("products.xlsx");
        } else {
            System.out.println("File not downloading");
        }

        System.out.println(System.getProperty("user.dir"));
    }

    public static void notification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(3))
                .position(Pos.BASELINE_RIGHT)
                .graphic(null)
                .onAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Notification is clicked");
                    }
                })
                .darkStyle()
                .showConfirm();
    }
}
