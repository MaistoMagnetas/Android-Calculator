package com.example.pc.calculator;


/**
 * Created by PC on 4/26/2018.
 */

public class InfixToPostFix extends com.example.pc.calculator.Stack{

    public String infixToPostfixConversion(String inputString){
        int i;
        Stack stack = new Stack();
        char temp;
        String outputString = "";
        for(i = 0;i<inputString.length();i++){
            temp = inputString.charAt(i);
            if(Character.isLetterOrDigit(temp)){
                outputString += temp;
            }else if(temp == '('){
                stack.push(""+temp);
            }else if(temp == ')'){
                while (!stack.isEmpty() && (!stack.stackTop().equals("("))){
                    outputString+=stack.pop();
                }
                if(stack.isEmpty()){
                    return "NaN";
                }else{
                    stack.pop();
                }
            }else {
                while (!stack.isEmpty() && (precedence(""+temp) <= precedence(stack.stackTop()))){
                    outputString+=stack.pop();
                }
                stack.push(""+temp);
            }
        }
        while (!stack.isEmpty()){
            outputString += stack.pop();
        }
        return outputString;
    }

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
    public String postfixEvaluation(String fixedPostfix){
        double answerTemp = 0.0;
        char temp;
        Stack stack = new Stack();
        String operand1, operand2;

        for(int i = 0;i<fixedPostfix.length();i++) {
            temp = fixedPostfix.charAt(i); //Take fist char
            if (Character.isDigit(temp)) { //If char is number push it to stack
                stack.push(""+temp);
            } else if(isOperand(temp)) { //if char is sign take two numbers from stack
                operand2 = stack.pop(); //Removes element form stack and returns it to CHAR variable
                operand1 = stack.pop();
                answerTemp = calculation("" + temp, "" + operand1, "" + operand2); //calculates
                stack.push("" + answerTemp); //puts the calculated result back to stack for further calculations
            }
        }
        return String.valueOf(stack.pop()); //returns char to String
    }

    //Method to check if character is a sing or not
    public boolean isOperand(char operand){
        switch (operand){
            case '+':
                return true;
            case '*':
                return true;
            case '÷':
                return true;
            case '-':
                return true;
            default:
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

