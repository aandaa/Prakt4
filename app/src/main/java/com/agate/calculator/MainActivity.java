package com.agate.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView info;
    private TextView result;
    private final char EQUALS = 0;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = 'x';
    private final char DIVISION = '/';
    private double val1 = Double.NaN;
    private double val2;
    private char ACTION;
    private boolean canManipulate = false;
    private boolean canCalculate = false;
    private boolean canCopy = false;
    private boolean canPaste = false;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPrefResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("prefID", MODE_PRIVATE);
        sharedPrefResult = getSharedPreferences("prefID", MODE_PRIVATE);
        editor = sharedPref.edit();

        Button one = (Button)findViewById(R.id.btn1);
        Button two = (Button)findViewById(R.id.btn2);
        Button three = (Button)findViewById(R.id.btn3);
        Button four = (Button)findViewById(R.id.btn4);
        Button five = (Button)findViewById(R.id.btn5);
        Button six = (Button)findViewById(R.id.btn6);
        Button seven = (Button)findViewById(R.id.btn7);
        Button eight = (Button)findViewById(R.id.btn8);
        Button nine = (Button)findViewById(R.id.btn9);
        Button zero = (Button)findViewById(R.id.btn0);
        Button add = (Button)findViewById(R.id.btnPlus);
        Button sub = (Button)findViewById(R.id.btnSub);
        Button mult = (Button)findViewById(R.id.btnX);
        Button div = (Button)findViewById(R.id.btnDiv);
        Button ms = (Button)findViewById(R.id.btnMs);
        Button mr = (Button)findViewById(R.id.btnMr);
        Button mc = (Button)findViewById(R.id.btnMc);
        Button clear = (Button)findViewById(R.id.btnClear);
        Button equals = findViewById(R.id.btnEq);
       Button ce = findViewById(R.id.btnCe);
        info = (TextView)findViewById(R.id.textView);
        result = (TextView)findViewById(R.id.textView3);

        one.setOnClickListener((View.OnClickListener) this);
        two.setOnClickListener((View.OnClickListener) this);
        three.setOnClickListener((View.OnClickListener) this);
        four.setOnClickListener((View.OnClickListener) this);
        five.setOnClickListener((View.OnClickListener) this);
        six.setOnClickListener((View.OnClickListener) this);
        seven.setOnClickListener((View.OnClickListener) this);
        eight.setOnClickListener((View.OnClickListener) this);
        nine.setOnClickListener((View.OnClickListener) this);
        zero.setOnClickListener((View.OnClickListener) this);
        add.setOnClickListener((View.OnClickListener) this);
        sub.setOnClickListener((View.OnClickListener) this);
        mult.setOnClickListener((View.OnClickListener) this);
        div.setOnClickListener((View.OnClickListener) this);
        ms.setOnClickListener((View.OnClickListener) this);
        mr.setOnClickListener((View.OnClickListener) this);
        mc.setOnClickListener((View.OnClickListener) this);
        clear.setOnClickListener((View.OnClickListener) this);
        equals.setOnClickListener((View.OnClickListener) this);
        ce.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                info.setText(info.getText().toString() + "1");
                skaitluIevads();
                break;
            case R.id.btn2:
                info.setText(info.getText().toString() + "2");
                skaitluIevads();
                break;
            case R.id.btn3:
                info.setText(info.getText().toString() + "3");
                skaitluIevads();
                break;
            case R.id.btn4:
                info.setText(info.getText().toString() + "4");
                skaitluIevads();
                break;
            case R.id.btn5:
                info.setText(info.getText().toString() + "5");
                skaitluIevads();
                break;
            case R.id.btn6:
                info.setText(info.getText().toString() + "6");
                skaitluIevads();
                break;
            case R.id.btn7:
                info.setText(info.getText().toString() + "7");
                skaitluIevads();
                break;
            case R.id.btn8:
                info.setText(info.getText().toString() + "8");
                skaitluIevads();
                break;
            case R.id.btn9:
                info.setText(info.getText().toString() + "9");
                skaitluIevads();
                break;
            case R.id.btn0:
                info.setText(info.getText().toString() + "0");
                skaitluIevads();
                break;
            case R.id.btnPlus:
                if (canManipulate) {
                    kalkulet();
                    ACTION = ADDITION;
                    result.setText(String.valueOf(val1) + " + ");
                    info.setText(null);
                    canCalculate = true;
                    canManipulate = false;
                }
                break;
            case R.id.btnSub:
                if (canManipulate) {
                    kalkulet();
                    ACTION = SUBTRACTION;
                    result.setText(String.valueOf(val1) + " - ");
                    info.setText(null);
                    canCalculate = true;
                    canManipulate = false;
                }
                break;
            case R.id.btnX:
                if (canManipulate) {
                    kalkulet();
                    ACTION = MULTIPLICATION;
                    result.setText(String.valueOf(val1) + " x ");
                    info.setText(null);
                    canCalculate = true;
                    canManipulate = false;
                }
                break;
            case R.id.btnDiv:
                if (canManipulate) {
                    kalkulet();
                    ACTION = DIVISION;
                    result.setText(String.valueOf(val1) + " / ");
                    info.setText(null);
                    canCalculate = true;
                    canManipulate = false;
                }
                break;
            case R.id.btnEq:
                if (canCalculate && canManipulate) {
                    kalkulet();
                    ACTION = EQUALS;
                    result.setText(result.getText().toString() + String.valueOf(val2) + " = " + String.valueOf(val1));
                    info.setText(null);
                    canCalculate = false;
                    canManipulate = false;
                    canCopy = true;
                }
                break;
            case R.id.btnClear:
                if(info.getText().length() > 0) {
                    CharSequence name = info.getText().toString();
                    info.setText(name.subSequence(0, name.length() - 1));
                } else {
                    val1 = Double.NaN;
                    val2 = Double.NaN;
                    info.setText(null);
                    result.setText(null);
                    canManipulate = false;
                    canCalculate = false;
                    canCopy = false;
                }
                break;
           case R.id.btnCe:
                val1 = Double.NaN;
                val2 = Double.NaN;
                info.setText(null);
                result.setText(null);
                canManipulate = false;
                canCalculate = false;
                canCopy = false;
                break;
            case R.id.btnMs:
                if (canCopy) {
                    editor.putString("Text", String.valueOf(val1));
                    editor.commit();
                    canPaste = true;
                    Toast.makeText(this, "Result are saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Nothing to save!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMr:
                if (canPaste) {
                    info.setText(sharedPrefResult.getString("Text", "0"));
                    result.setText(null);
                    canManipulate = true;
                    canCopy = false;
                    val1 = Double.NaN;
                    val2 = Double.NaN;
                    Toast.makeText(this, "Result is given!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Nothing to read!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMc:
                editor.putString("Text", "");
                editor.commit();
                canPaste = false;
                Toast.makeText(this, "Memory cleared!", Toast.LENGTH_SHORT).show();
        }
    }
    private void skaitluIevads() {
        canManipulate = true;
        if(canCopy) {
            val1 = Double.NaN;
            val2 = Double.NaN;
            result.setText(null);
            canCopy = false;
        }
    }

    private void kalkulet() {
        if (!Double.isNaN(val1)) {
            val2 = Double.parseDouble(info.getText().toString());

            switch(ACTION) {
                case ADDITION:
                    val1 = val1 + val2;
                    break;
                case SUBTRACTION:
                    val1 = val1 - val2;
                    break;
                case MULTIPLICATION:
                    val1 = val1 * val2;
                    break;
                case DIVISION:
                    val1 = val1 / val2;
                    break;
                case EQUALS:
                    break;
            }
        } else {
            val1 = Double.parseDouble(info.getText().toString());
        }
    }
}