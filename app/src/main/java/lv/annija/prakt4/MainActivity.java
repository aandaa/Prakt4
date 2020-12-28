package lv.annija.prakt4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView input;
    TextView calculation;
    Integer savedValue;

    Integer firstV;
    Integer secondV;

    boolean plus = false;
    boolean minus = false;
    boolean mul = false;
    boolean div = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_0 = findViewById(R.id.btn_0);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_7 = findViewById(R.id.btn_7);
        Button btn_8 = findViewById(R.id.btn_8);
        Button btn_9 = findViewById(R.id.btn_9);

        Button btn_plus = findViewById(R.id.btn_plus);
        Button btn_min = findViewById(R.id.btn_minus);
        Button btn_mul = findViewById(R.id.btn_mul);
        Button btn_div = findViewById(R.id.btn_div);
        Button btn_eq = findViewById(R.id.btn_eq);
        Button btn_ms = findViewById(R.id.btn_ms);
        Button btn_mr = findViewById(R.id.btn_mr);
        Button btn_mc = findViewById(R.id.btn_mc);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_min.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
        btn_ms.setOnClickListener(this);
        btn_mr.setOnClickListener(this);
        btn_mc.setOnClickListener(this);

        input = findViewById(R.id.input);
        calculation = findViewById(R.id.calculation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                validateValueCount("0");
                break;
            case R.id.btn_1:
                validateValueCount("1");
                break;
            case R.id.btn_2:
                validateValueCount("2");
                break;
            case R.id.btn_3:
                validateValueCount("3");
                break;
            case R.id.btn_4:
                validateValueCount("4");
                break;
            case R.id.btn_5:
                validateValueCount("5");
                break;
            case R.id.btn_6:
                validateValueCount("6");
                break;
            case R.id.btn_7:
                validateValueCount("7");
                break;
            case R.id.btn_8:
                validateValueCount("8");
                break;
            case R.id.btn_9:
                validateValueCount("9");
                break;
            case R.id.btn_plus:
                if (!minus && !mul && !div){
                    plus = true;
                    saveNumbers(Integer.parseInt(input.getText().toString()), "+");
                } else {
                    plus = true;
                    minus = mul = div = false;
                    calculation.setText(calculation.getText().toString().substring(0, calculation.length() - 1));
                    calculation.append("+");
                }
                break;
            case R.id.btn_minus:
                if (!plus && !mul && !div){
                    minus = true;
                    saveNumbers(Integer.parseInt(input.getText().toString()), "-");
                } else {
                    minus = true;
                    plus = mul = div = false;
                    calculation.setText(calculation.getText().toString().substring(0, calculation.length() - 1));
                    calculation.append("-");
                }
                break;
            case R.id.btn_mul:
                if (!plus && !minus && !div){
                    mul = true;
                    saveNumbers(Integer.parseInt(input.getText().toString()), "*");
                } else {
                    mul = true;
                    plus = minus = div = false;
                    calculation.setText(calculation.getText().toString().substring(0, calculation.length() - 1));
                    calculation.append("*");
                }
                break;
            case R.id.btn_div:
                if (!plus && !minus && !mul){
                    div = true;
                    saveNumbers(Integer.parseInt(input.getText().toString()), "/");
                } else {
                    div = true;
                    plus = minus = mul = false;
                    calculation.setText(calculation.getText().toString().substring(0, calculation.length() - 1));
                    calculation.append("/");
                }
                break;
            case R.id.btn_eq:
                if (input.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter a value", Toast.LENGTH_LONG).show();
                } else {
                    calculate();
                }
                break;
            case R.id.btn_ms:
                savedValue = Integer.parseInt(input.getText().toString());
                break;
            case R.id.btn_mr:
                if (savedValue != null){
                    input.setText(String.valueOf(savedValue));
                }
                break;
            case R.id.btn_mc:
                savedValue = null;
                break;
            default:
                Toast.makeText(MainActivity.this, "Error detected", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void saveNumbers(int value, String s) {
        if (firstV == null){
            firstV = value;
            calculation.append(input.getText());
            input.setText("");
            calculation.append(s);
            return;
        } else if (secondV == null) {
            Toast.makeText(MainActivity.this, "No additional values allowed. Please press =", Toast.LENGTH_LONG).show();
            return;
        } else {
            return;
        }
    }

    private void validateValueCount(String s){
        if(firstV == null || secondV == null){
            input.append(s);
        } else {
            Toast.makeText(MainActivity.this, "No more values allowed", Toast.LENGTH_LONG).show();
        }
        return;
    }

    private void calculate(){
        if (firstV == null){
            displayResult(Integer.parseInt(input.getText().toString()));
        } else if (firstV != null && secondV == null){
            secondV = Integer.parseInt(input.getText().toString());

            if(plus){
                displayResult(firstV + secondV);
                plus = false;
                return;
            }
            if(minus){
                displayResult(firstV - secondV);
                minus = false;
                return;
            }
            if(mul){
                displayResult(firstV * secondV);
                mul = false;
                return;
            }
            if(div){
                displayResult(firstV / secondV);
                div = false;
                return;
            }
        }
    }

    public void displayResult (int result){
        input.setText(result + "");
        calculation.setText("");
        firstV = null;
        secondV = null;
        return;
    }
}