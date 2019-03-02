package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerProductsQuotation implements Initializable {

    private static final Font ROBOTO_TEXT_FIELDS = Font.loadFont(ControllerProductsQuotation.class.getResource("/res/fonts/RobotoRegular.ttf").toString(), 16);
    private static final Font LATO_BLACK_16 = Font.loadFont(ControllerProductsQuotation.class.getResource("/res/fonts/LatoBlack.ttf").toString(), 16);

    @FXML
    private AnchorPane productsQuotationPage;
    @FXML
    private ScrollPane productsQuotationScrollPane;
    @FXML
    private AnchorPane productsQuotationanchorPaneContainer;
    @FXML
    private Label brandName;
    @FXML
    private Label registryOneTitle;
    @FXML
    private Label registryTwoTitle;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField telText;
    @FXML
    private TextField dateText;
    @FXML
    private TextField noteOneText;
    @FXML
    private TextField nameTextReg2;
    @FXML
    private TextField sirnameTextReg2;
    @FXML
    private TextField emailTextReg2;
    @FXML
    private TextField telReg2;
    @FXML
    private TextField noteTwoText;
    @FXML
    private RadioButton serviceOneRadioButton;
    @FXML
    private RadioButton serviceTwoRadioButton;
    @FXML
    private Label categoryTypeTitle;
    @FXML
    private ChoiceBox<?> categoryChoiceBox;
    @FXML
    private Button previewButton;
    @FXML
    private Button saveQuoteButton;
    @FXML
    private Button saveOnlyDataButton;
    @FXML
    private Label selectProductTitle;
    @FXML
    private Label backButton;


    @FXML
    void backButtonAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ui/mainview.fxml"));
        Scene scene  = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        window.setScene(scene);
        window.show();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productsQuotationanchorPaneContainer.setStyle("-fx-background-image: url('/res/pic/page.png');");
        productsQuotationPage.setStyle("-fx-background-image: url('/res/pic/page.png');");
        brandName.setFont(Font.loadFont(getClass().getResource("/res/fonts/SpicyRice.ttf").toString(), 40));
        brandName.setTextFill(Paint.valueOf("#DB1E1E"));
        backButton.setGraphic(ControllerMain.getImage("back.png"));

        registryOneTitle.setFont(Font.loadFont(getClass().getResource("/res/fonts/LatoRegular.ttf").toString(), 23));
        registryTwoTitle.setFont(Font.loadFont(getClass().getResource("/res/fonts/LatoRegular.ttf").toString(), 23));

        /** text fields **/
        firstNameText.setFont(ROBOTO_TEXT_FIELDS);
        firstNameText.setPromptText("First name");
        lastNameText.setFont(ROBOTO_TEXT_FIELDS);
        lastNameText.setPromptText("Last name");
        emailText.setFont(ROBOTO_TEXT_FIELDS);
        emailText.setPromptText("Email");
        telText.setFont(ROBOTO_TEXT_FIELDS);
        telText.setPromptText("Telephone");
        dateText.setFont(ROBOTO_TEXT_FIELDS);
        dateText.setPromptText("Date");
        noteOneText.setFont(ROBOTO_TEXT_FIELDS);
        noteOneText.setPromptText("Note for preventivo");
        noteTwoText.setFont(ROBOTO_TEXT_FIELDS);
        noteTwoText.setPromptText("Photographer internal notes");

        serviceOneRadioButton.setFont(ROBOTO_TEXT_FIELDS);
        serviceTwoRadioButton.setFont(ROBOTO_TEXT_FIELDS);

        /** check this **/
        categoryTypeTitle.setFont(LATO_BLACK_16);

        previewButton.setFont(LATO_BLACK_16);
        saveQuoteButton.setFont(LATO_BLACK_16);
        saveOnlyDataButton.setFont(LATO_BLACK_16);

        nameTextReg2.setFont(ROBOTO_TEXT_FIELDS);
        nameTextReg2.setPromptText("Name");
        sirnameTextReg2.setFont(ROBOTO_TEXT_FIELDS);
        sirnameTextReg2.setPromptText("Sirname");
        emailTextReg2.setFont(ROBOTO_TEXT_FIELDS);
        emailTextReg2.setPromptText("Email");
        telReg2.setFont(ROBOTO_TEXT_FIELDS);
        telReg2.setPromptText("Telephone");
        selectProductTitle.setFont(LATO_BLACK_16);

    }
}
