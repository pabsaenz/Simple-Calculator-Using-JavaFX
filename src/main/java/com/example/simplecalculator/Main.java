package com.example.simplecalculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

public class Main extends Application {
    public static  void main(String[] args) {
        launch(Main.class);
    }
    public void start(Stage window) throws IOException {
        TextField outputField= new TextField();
        outputField.setFont(new Font(20));
        outputField.positionCaret(outputField.getText().length());
        StackPane root = new StackPane(outputField);

        Button oneBtn = new Button("1");
        Button twoBtn = new Button("2");
        Button threeBtn = new Button("3");
        Button fourBtn = new Button("4");
        Button fiveBtn = new Button("5");
        Button sixBtn = new Button("6");
        Button sevenBtn = new Button("7");
        Button eightBtn = new Button("8");
        Button nineBtn = new Button("9");
        Button zeroBtn = new Button("0");
        Button plusBtn = new Button("+");
        Button minusBtn = new Button("-");
        Button multiplyBtn = new Button("*");
        Button divideBtn = new Button("/");
        Button equalsBtn = new Button("=");
        Button cancelBtn = new Button("C");

        Button[] buttons = {sevenBtn, eightBtn, nineBtn, plusBtn,
                            fourBtn, sixBtn, fiveBtn, minusBtn,
                            oneBtn, twoBtn, threeBtn, multiplyBtn,
                            cancelBtn, zeroBtn, divideBtn,equalsBtn};

        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setVgap(5);
        buttonsGrid.setHgap(5);
        buttonsGrid.setAlignment(Pos.CENTER);

        int col =0;
        int row=0;
        for(Button button:buttons){
            button.setPrefSize(65, 70);
            button.setFont(new Font(15));
            button.setStyle("-fx-font-weight: bold");
            button.setOnAction(e -> {
                if (!button.getText().equals("C") && !button.getText().equals("=")) {
                    outputField.setText(outputField.getText() + button.getText());
                } else if (button.getText().equals("C")) {
                    outputField.clear();
                } else if (button.getText().equals("=")) {
                    System.out.println(outputField.getText());
                    ScriptEngineManager mgr = new ScriptEngineManager();
                    ScriptEngine engine = mgr.getEngineByName("JavaScript");
                    try {
                        outputField.setText(engine.eval(outputField.getText()).toString());
                    } catch (ScriptException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                {

                }
            });
            buttonsGrid.add(button, col, row);
            col++;
            if(col>3){
                row++;
                col = 0;
            }
        }

        BorderPane display = new BorderPane();
        display.setPrefSize(300, 400);
        display.setPadding(new Insets(20));
        display.setTop(root);
        display.setCenter(buttonsGrid);

        Scene scene = new Scene(display);
        window.setTitle("Simple Calculator");
        window.setScene(scene);
        window.show();

    }


}
