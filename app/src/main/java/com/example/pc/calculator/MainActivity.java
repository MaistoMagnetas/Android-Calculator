package com.example.pc.calculator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//ALT+0247 = ÷
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Layout itmes variables
    private Button btnAC, btnLeftBracket, btnRightBracket, btnDivide, btn7, btn8, btn9, btnMultiply,
                    btn4, btn5, btn6, btnMinus, btn1, btn2, btn3, btnPlus, btnDot, btn0, btnDelete, btnEquals;
    private TextView tvInsertField, tvAnswerField;
    private DatabaseHelper db = new DatabaseHelper(this);
    private InfixToPostFix infixClass = new InfixToPostFix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Method for layout items initialization
        initLayoutItems();
    }

    //Menu infalter in Main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    //Menu item on click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_history://Shows database of actions done in this session
                Intent intentHistory = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.menu_rateapp: //Simply takes to app location in GooglePlay Store.
                // (TO-DO//Finish uploading app if needed)
                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
        }
        return true;
    }

    //Method for layout items initialization
    private void initLayoutItems(){
        tvInsertField =  (TextView) findViewById(R.id.tvInsertField);
        tvAnswerField = (TextView) findViewById(R.id.tvAnswerField);

        btnAC = (Button) findViewById(R.id.btnAC);
        btnLeftBracket = (Button) findViewById(R.id.btnLeftBracket);
        btnRightBracket = (Button) findViewById(R.id.btnRightBracket);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btn0 = (Button) findViewById(R.id.btn0);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEquals = (Button) findViewById(R.id.btnEquals);

        btnAC.setOnClickListener(this);
        btnLeftBracket.setOnClickListener(this);
        btnRightBracket.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnEquals.setOnClickListener(this);

    }

    //Method to get string in textView
    private String returnTextViewString(){
        return tvInsertField.getText().toString();
    }

    //Method to set String in textView
    private void setTextViewString(String message){
        tvInsertField.setText(returnTextViewString()+message);
    }
    //Method to set String in textView for sings. (Not to double ++ etc)
    private void setTextViewStringForSigns(char symbol){
        if( !tvInsertField.getText().toString().substring(tvInsertField.getText().toString().length()-1).equals("*") &&
               !tvInsertField.getText().toString().substring(tvInsertField.getText().toString().length()-1).equals("-") &&
                !tvInsertField.getText().toString().substring(tvInsertField.getText().toString().length()-1).equals("+")&&
                !tvInsertField.getText().toString().substring(tvInsertField.getText().toString().length()-1).equals("÷")){
            tvInsertField.setText(tvInsertField.getText().toString()+symbol);
            //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        }else{
            tvInsertField.setText(tvInsertField.getText().toString().substring(0,tvInsertField.getText().toString().length()-1)+symbol);
            //Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        }
    }

    @Override //Method to get button clicked
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAC: //Clears textView
                tvInsertField.setText("");
                tvAnswerField.setText("");
                break;
            case R.id.btnLeftBracket:
                setTextViewString("(");
                break;
            case R.id.btnRightBracket:
                setTextViewString(")");
                break;
            case R.id.btnDivide:
                setTextViewStringForSigns('÷');
                break;
            case R.id.btn7:
                setTextViewString("7");
                break;
            case R.id.btn8:
                setTextViewString("8");
                break;
            case R.id.btn9:
                setTextViewString("9");
                break;
            case R.id.btnMultiply:
                setTextViewStringForSigns('*');
                break;
            case R.id.btn4:
                setTextViewString("4");
                break;
            case R.id.btn5:
                setTextViewString("5");
                break;
            case R.id.btn6:
                setTextViewString("6");
                break;
            case R.id.btnMinus:
                setTextViewStringForSigns('-');
                break;
            case R.id.btn1:
                setTextViewString("1");
                break;
            case R.id.btn2:
                setTextViewString("2");
                break;
            case R.id.btn3:
                setTextViewString("3");
                break;
            case R.id.btnPlus:
                setTextViewStringForSigns('+');
                break;
            case R.id.btnDot:
                setTextViewString(".");
                break;
            case R.id.btn0:
                setTextViewString("0");
                break;
            case R.id.btnDelete:
                deleteButtonClicked();
                break;
            case R.id.btnEquals:
                equalsButtonClicked();
                break;
        }
    }

    //Function to delete last symbol-letter then DEL is clicked
    private void deleteButtonClicked(){
        String message = tvInsertField.getText().toString();
        if(message != null && message.length() > 0){
            message = message.substring(0,message.length()-1);
        }
        tvInsertField.setText(message);
    }

    //Calculates the answer. PostfixEvaluation from InfixClass
    private void equalsButtonClicked(){
        String postfixConverted = infixClass.infixToPostfixConversion(tvInsertField.getText().toString());
        String answer = infixClass.postfixEvaluation(postfixConverted);
        tvAnswerField.setText(answer);
        boolean isInserted = db.addEntryToHistory(tvInsertField.getText().toString(),
                tvAnswerField.getText().toString());
//        if(isInserted){
//            Toast.makeText(this, "Data inserted "+tvAnswerField.getText().toString(), Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Smth went wrong", Toast.LENGTH_SHORT).show();
//        }


    }

}
