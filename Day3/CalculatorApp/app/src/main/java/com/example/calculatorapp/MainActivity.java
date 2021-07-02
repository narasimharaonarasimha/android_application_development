package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperator;

    private Double operand1;
    private Double operand2;
    private String pendingOperation="=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result= (EditText) findViewById(R.id.result);
        newNumber= (EditText) findViewById(R.id.newNumber);
        displayOperator=(TextView) findViewById(R.id.displayOperator);

        Button button0=(Button) findViewById(R.id.button0);
        Button button1=(Button) findViewById(R.id.button1);
        Button button2=(Button) findViewById(R.id.button2);
        Button button3=(Button) findViewById(R.id.button3);
        Button button4=(Button) findViewById(R.id.button4);
        Button button5=(Button) findViewById(R.id.button5);
        Button button6=(Button) findViewById(R.id.button6);
        Button button7=(Button) findViewById(R.id.button7);
        Button button8=(Button) findViewById(R.id.button8);
        Button button9=(Button) findViewById(R.id.button9);
        Button buttonDot=(Button) findViewById(R.id.buttonDot);


        View.OnClickListener listener=new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Button button=(Button) v;
                newNumber.append(button.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        Button buttonPlus=(Button) findViewById(R.id.buttonPlus);
        Button buttonMinus=(Button) findViewById(R.id.buttonMinus);
        Button buttonMultiply=(Button) findViewById(R.id.buttonMultiply);
        Button buttonDivide=(Button) findViewById(R.id.buttonDivide);
        Button buttonEquals=(Button) findViewById(R.id.buttonEquals);

        View.OnClickListener operatorListener=new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Button button=(Button) v;
                String operator=button.getText().toString();
                String value=newNumber.getText().toString();
                try{
                Double doubleValue=Double.valueOf(value);
                performOperation(doubleValue, operator);
                }catch(NumberFormatException exception){
                    newNumber.setText("");
                }
                pendingOperation=operator;
                displayOperator.setText(pendingOperation);
            }
        };
        buttonEquals.setOnClickListener(operatorListener);
        buttonPlus.setOnClickListener(operatorListener);
        buttonMinus.setOnClickListener(operatorListener);
        buttonMultiply.setOnClickListener(operatorListener);
        buttonDivide.setOnClickListener(operatorListener);

        Button buttonNegative=(Button) findViewById(R.id.buttonNegative);
        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=newNumber.getText().toString();
                if(value.length()==0){
                    newNumber.setText("-");
                }else{
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue*=-1;
                        newNumber.setText(doubleValue.toString());
                    }catch(NumberFormatException ex){
                        newNumber.setText("");
                    }
                }
            }
        });


    }
    private void performOperation(Double value, String operator){
        if(operand1==null){
            operand1=value;
        }else{
            operand2=value;
            if(pendingOperation.equals("=")){
                pendingOperation=operator;
            }
            switch (pendingOperation){
                case "=":
                    operand1=operand2;
                    break;
                case "/":
                    if(operand2==0){
                        operand1=0.0;
                    }else{
                        operand1/=operand2;
                    }
                    break;
                case "+":
                    operand1+=operand2;
                    break;
                case "-":
                    operand1-=operand2;
                    break;
                case "*":
                    operand1*=operand2;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}