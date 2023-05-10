package com.example.UI;

import com.example.Iteration.IterationMethod;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.util.Arrays;


public class HelloApplication extends Application {
    Label result;

    @Override
    public void start(Stage stage) {
        GridPane form = EnterForm.enterFrom();
        GridPane formForVector = EnterFormForVector.enterFrom();

        Button solveButton = new Button("Розрахувати");

        solveButton.setMaxSize(130, 30);
        solveButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        solveButton.setOnAction(event -> solveIterationMethod());
        solveButton.setStyle("-fx-background-color: #C0BDE1;");
        solveButton.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        result = BottomPanel.createBottomPanel();

        VBox box = new VBox(form, formForVector, solveButton, result);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: #F4F2E6;");

        Scene scene = new Scene(box, 700, 600);

        stage.setTitle("Iteration calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void solveIterationMethod() {
        IterationMethod iterationMethod;
        
        TextField[][] textFieldsOurEquation = EnterForm.getAllFields();
        TextField[][] textFieldsOurVector = EnterFormForVector.getAllFieldsForVector();
        
        double[][] matrix = new double[textFieldsOurEquation.length][textFieldsOurEquation[0].length];
        double[] vector = new double[textFieldsOurVector.length];

        for (int i = 0; i < textFieldsOurEquation.length; i++) {
            for (int j = 0; j < textFieldsOurEquation[0].length; j++) {
                matrix[i][j] = Float.parseFloat(String.valueOf(textFieldsOurEquation[i][j].getText()));
            }
        }

        for (int i = 0; i < textFieldsOurVector.length; i++) {
            for (int j = 0; j < textFieldsOurVector[0].length; j++) {
                vector[i] = Float.parseFloat(String.valueOf(textFieldsOurVector[i][j].getText()));
            }
        }

        iterationMethod = new IterationMethod(matrix, vector);
        double[] solve = iterationMethod.function();
        result.setText(String.format("x1: %.6f; x2: %.6f; x3: %.6f; x4: %.6f", solve[0], solve[1], solve[2], solve[3]));
        //result.setText(Arrays.toString(iterationMethod.function()));
        result.setVisible(true);
    }

    public static void main(String[] args) {
        launch();
    }
}