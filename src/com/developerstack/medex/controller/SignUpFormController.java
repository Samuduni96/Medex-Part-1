package com.developerstack.medex.controller;

import com.developerstack.medex.db.Database;
import com.developerstack.medex.dto.UserDto;
import com.developerstack.medex.enums.AccountType;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SignUpFormController {
    public AnchorPane signUpContext;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rBtnDoctor;
    public ToggleGroup accountType;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().trim().toLowerCase();
        /*for (UserDto dto: Database.userTable) {
            if (dto.getEmail().equals(email.trim().toLowerCase())) {
                new Alert(Alert.AlertType.WARNING, "Email already exist.").show();
                return;
            }
        }*/
        Optional<UserDto> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();
        if (selectedUser.isPresent()) {
            new Alert(Alert.AlertType.WARNING, "Email already exist.").show();
            return;
        }
        Database.userTable.add(new UserDto(
                txtFirstName.getText(),
                txtLastName.getText(),
                email,
                txtPassword.getText(),
                rBtnDoctor.isSelected()? AccountType.DOCTOR : AccountType.PATIENT)
        );
        new Alert(Alert.AlertType.CONFIRMATION, "Welcome!").show();
        setUi();
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi();
    }

    private void setUi() throws IOException {
        Stage stage = (Stage) signUpContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.centerOnScreen();
    }
}
