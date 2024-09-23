package com.calc.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button buttonBorrarTodo;

    @FXML
    private Button buttonBorrar1;

    @FXML
    private Button buttonParentesisDerecho;

    @FXML
    private Button buttonParentesisIzquierdo;

    @FXML
    private Button buttonMas;

    @FXML
    private Button buttonMulti;

    @FXML
    private Button buttonDivision;

    @FXML
    private Button buttonMenos;

    @FXML
    private Button buttonComa;

    @FXML
    private Text Texto;

    @FXML
    private StringBuilder input = new StringBuilder();

    @FXML
    private void Numeros(ActionEvent e) {
        Button sourceButton = (Button) e.getSource();
        String buttonText = sourceButton.getText();

        input.append(buttonText);
        Texto.setText(input.toString());
    }

    @FXML
    private void borrarTodo(ActionEvent e) {
        input.setLength(0);
        Texto.setText("0");
    }

    @FXML
    private void borrarUltimo(ActionEvent e) {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            Texto.setText(input.length() == 0 ? "0" : input.toString());
        }
    }

    @FXML
    private void calcularResultado(ActionEvent e) {
        String expression = input.toString().replaceAll("\\s+", ""); // Eliminar espacios en blanco
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        StringBuilder numberBuffer = new StringBuilder();

        // Recorrer cada carácter
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                numberBuffer.append(currentChar);
            } else {
                if (numberBuffer.length() > 0) {
                    numbers.add(Double.parseDouble(numberBuffer.toString()));
                    numberBuffer.setLength(0);
                }
                operators.add(currentChar);
            }
        }

        if (numberBuffer.length() > 0) {
            numbers.add(Double.parseDouble(numberBuffer.toString()));
        }

        // Primero multiplicaciones y divisiones
        List<Double> newNumbers = new ArrayList<>();
        List<Character> newOperators = new ArrayList<>();

        newNumbers.add(numbers.get(0));

        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);
            double nextNumber = numbers.get(i + 1);

            if (operator == '*' || operator == '/') {
                double result = (operator == '*') ? newNumbers.get(newNumbers.size() - 1) * nextNumber
                        : newNumbers.get(newNumbers.size() - 1) / nextNumber;
                newNumbers.set(newNumbers.size() - 1, result);
            } else {
                newNumbers.add(nextNumber);
                newOperators.add(operator);
            }
        }

        // Ahora sumas y restas
        double result = newNumbers.get(0);
        for (int i = 0; i < newOperators.size(); i++) {
            char operator = newOperators.get(i);
            double nextNumber = newNumbers.get(i + 1);

            if (operator == '+') {
                result += nextNumber;
            } else if (operator == '-') {
                result -= nextNumber;
            }
        }

        Texto.setText(String.valueOf(result));
        input.setLength(0); // Limpiar el input
        input.append(result); // Guardar el resultado para futuras operaciones
    }




    @FXML
    private void agregarOperacion(ActionEvent e) {
        Button sourceButton = (Button) e.getSource();
        String buttonText = sourceButton.getText();


        if (input.length() > 0 && "+-*/".contains(input.substring(input.length() - 1))) {
            input.setCharAt(input.length() - 1, buttonText.charAt(0));
        } else {
            input.append(buttonText);
        }
        Texto.setText(input.toString());
    }

}