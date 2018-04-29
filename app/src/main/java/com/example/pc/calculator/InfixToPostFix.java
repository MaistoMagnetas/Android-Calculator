package com.example.pc.calculator;


import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * Created by PC on 4/26/2018.
 */

public class InfixToPostFix extends com.example.pc.calculator.Stack{

    //Converts infix to postfix ex: 4*3+5 to 4 3 * 5 +  with spaces
    public String infixToPostfixConversion(String inputString){ //Makes good infix to postfix(multidigit working)
        int i,j;
        Stack stack = new Stack();
        char temp;
        String outputString = "";
        String tempDigitString = "";
        for(i = 0;i<inputString.length();i++){
            temp = inputString.charAt(i);
            if(Character.isLetterOrDigit(temp)){
                j = i+1; //j = next char in input string to check if it is number
                while(j<inputString.length() && Character.isLetterOrDigit(inputString.charAt(j))){ //Checks if number is multidigit number ex: 10-9999999 etc.
                    tempDigitString += inputString.charAt(j);
                    j++;
                    i++; //Increses i so that first if would not repeat if while is allready true
                }
                outputString += temp+tempDigitString+" ";
                tempDigitString = ""; //Refreshes string to be empty so it would not duplicate
            }else if(temp == '('){
                stack.push(""+temp);
            }else if(temp == ')'){
                while (!stack.isEmpty() && (!stack.stackTop().equals("("))){ //parentheses
                    outputString+=stack.pop()+" "; //Must be space at the end. Tested.
                }
                if(stack.isEmpty()){
                    return "NaN";
                }else{
                    stack.pop();
                }
            }else {
                while (!stack.isEmpty() && (precedence(""+temp) <= precedence(stack.stackTop()))){ //Check mathematical operation flow
                    outputString+=stack.pop()+" ";
                }
                stack.push(""+temp);
            }
        }
        while (!stack.isEmpty()){ //Put everything from stack to output string
            outputString += stack.pop()+" ";
        }
        return outputString;
    }

    //Methematical operations flow
    public int precedence(String c){
        switch (c){
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "÷":
                return 2;
        }
        return -1;
    }

    //Method to loop tokens and get answer from postfix
    public String postfixEvaluation(String fixedPostfix){//4*3+5 to 4 3 * 5 +
        double answerTemp = 0.0;
        String temp;
        Stack stack = new Stack();
        String operand1, operand2;
        String[] tickets = fixedPostfix.split("\\ "); //Splits fixedPostFixString at separators=" ". turns into string array

        for(int i = 0;i<tickets.length;i++){
            temp = tickets[i]; //Take string array member = ticket
            if(isNumeric(temp)){ //If ticket is number push it to stack
                stack.push(""+temp);
            }else if(isOperand(temp)) { //if string is sign take two numbers from stack
                operand2 = stack.pop(); //Removes element form stack and returns it to CHAR variable
                operand1 = stack.pop();
                answerTemp = calculation("" + temp, "" + operand1, "" + operand2); //calculates
                stack.push("" + answerTemp); //puts the calculated result back to stack for further calculations
            }
        }
        return String.valueOf(stack.pop()); //returns char to String
    }

    //Checks if a String is numeric.
    public static boolean isNumeric(String str){
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    //Method to check if character is a sign or not
    public boolean isOperand(String operand){
        if(operand.equals("+")||operand.equals("-")||operand.equals("*")||operand.equals("÷")){
            return true;
        }else{
            return false;
        }
    }

    //Method to calculate the answer of numbers
    public double calculation(String sign, String operand1, String operand2){
        switch (sign){
            case "+":
                return Double.parseDouble(String.valueOf(operand1))+Double.parseDouble(String.valueOf(operand2));
            case "-":
                return Double.parseDouble(String.valueOf(operand1))-Double.parseDouble(String.valueOf(operand2));
            case "*":
                return Double.parseDouble(String.valueOf(operand1))*Double.parseDouble(String.valueOf(operand2));
            case "÷":
                return Double.parseDouble(String.valueOf(operand1))/Double.parseDouble(String.valueOf(operand2));
            default:
                return 0;
        }
    }
}