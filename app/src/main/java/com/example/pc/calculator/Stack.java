package com.example.pc.calculator;

/**
 * Created by PC on 4/26/2018.
 */

public class Stack {
    int top = -1;
    static final int MAX = 1000;
    String[] stack = new String[MAX];

    public void push(String element){
        if(top>MAX){
            //Do nothing
        }else{
            stack[++top]=element;
        }
    }

    public String pop(){
        if(!isEmpty()){
            return stack[top--];
        }else{
            return "NaN";
        }
    }

    public boolean isEmpty(){
        if(top<0){
            return true;
        }else{
            return false;
        }
    }

    public String stackTop(){
        return stack[top];
    }
}
