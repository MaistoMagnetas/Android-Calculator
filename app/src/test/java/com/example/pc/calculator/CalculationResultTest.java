package com.example.pc.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by PC on 4/27/2018.
 */

public class CalculationResultTest {

    InfixToPostFix infixClass = new InfixToPostFix();

    // /Method makes infix to postfix and evaluates it. Returnd double as an answer
    public double returnAnswer(String action){
        String postfixConverted = infixClass.infixToPostfixConversion(action);
        double answer = Double.parseDouble(infixClass.postfixEvaluation(postfixConverted));
        return answer;
    }

    @Test
    public void testAddition(){
        double answer = returnAnswer("5+3");
        double expected = 8.0;
        assertEquals("Sum is not wokring",expected,answer,0.01);
    }

    @Test
    public void testSubstraction(){
        double answer = returnAnswer("9-5");
        double expected = 4.0;
        assertEquals("Addition is not wokring",expected,answer,0.01);
    }

    @Test
    public void testMultiplication(){
        double answer = returnAnswer("6*5");
        double expected = 30.0;
        assertEquals("Multiplication is not wokring",expected,answer,0.01);
    }

    @Test
    public void testDivision(){
        double answer = returnAnswer("6÷5");
        double expected = 1.2;
        assertEquals("Division is not wokring",expected,answer,0.01);
    }

    @Test
    public void testAlgebra(){
        double answer = returnAnswer("(3+4)*(4-3)");
        double expected = 7;
        assertEquals("Calculus is not wokring",expected,answer,0.01);

        answer = returnAnswer("9+2*5-5");
        expected = 14;
        assertEquals("Calculus is not wokring",expected,answer,0.01);

        answer = returnAnswer("2+9÷3-2*3+7");
        expected = 6;
        assertEquals("Calculus is not wokring",expected,answer,0.01);

        answer = returnAnswer("3*(6÷2-3*4+2*6)");
        expected = 9;
        assertEquals("Calculus is not wokring",expected,answer,0.01);

        answer = returnAnswer("3-3*6+2");
        expected = -13;
        assertEquals("Calculus is not wokring",expected,answer,0.01);

        answer = returnAnswer("9-5*(2+5)+4");
        expected = -22;
        assertEquals("Calculus is not wokring",expected,answer,0.01);

        answer = returnAnswer("(1÷(2-3+4))*(5-1)*3");
        expected = 4;
        assertEquals("Calculus is not wokring",expected,answer,0.01);
    }

    @Test
    public void testInfixToPostFix(){
        String postfixConverted = infixClass.infixToPostfixConversion("2+3*4");
        String expected = "234*+";
        assertEquals("Infix to Postfix is not working",expected,postfixConverted);

        postfixConverted = infixClass.infixToPostfixConversion("4*3+5");
        expected = "43*5+";
        assertEquals("Infix to Postfix is not working",expected,postfixConverted);

        postfixConverted = infixClass.infixToPostfixConversion("(1+2)*7");
        expected = "12+7*";
        assertEquals("Infix to Postfix is not working",expected,postfixConverted);

        postfixConverted = infixClass.infixToPostfixConversion("(1÷(2-3+4))*(5-1)*3");
        expected = "123-4+÷51-*3*";
        assertEquals("Infix to Postfix is not working",expected,postfixConverted);
    }
}
