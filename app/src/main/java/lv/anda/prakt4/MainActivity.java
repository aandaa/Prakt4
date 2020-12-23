package lv.anda.prakt4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    double valueOne;
    double valueTwo;
    boolean sum;
    boolean sub;
    boolean mult;
    boolean div;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        editor.remove("myKey");
        editor.clear();
        editor.commit();
    }
    private void updateText(String toAdd) {
        String old = display.getText().toString();
        int cursor = display.getSelectionStart();
        String left = old.substring(0, cursor);
        String right = old.substring(cursor);
        if (display.getText().toString().equals("")) {
            display.setText(toAdd);
        } else {
            display.setText(String.format("%s%s%s", left, toAdd, right));
        }
        display.setSelection(cursor + 1);

    }

    public void zero_btn(View view) {
        updateText("0");
    }
    public void one_btn(View view) {
        updateText("1");
    }
    public void two_btn(View view) {
        updateText("2");
    }
    public void three_btn(View view) {
        updateText("3");
    }
    public void four_btn(View view) {
        updateText("4");
    }
    public void five_btn(View view) {
        updateText("5");
    }
    public void six_btn(View view) {
        updateText("6");
    }
    public void seven_btn(View view) {
        updateText("7");
    }
    public void eight_btn(View view) {
        updateText("8");
    }
    public void nine_btn(View view) {
        updateText("9");
    }

    public void multiply_btn(View view) {
        try {
            valueOne = Double.parseDouble(display.getText() + "");
            mult = true;
            display.setText(null);
        } catch (Exception e) {
            Toast.makeText(this, "No value!", Toast.LENGTH_SHORT).show();
        }
    }
    public void sum_btn(View view) {
        try {
            valueOne = Double.parseDouble(display.getText() + "");
            sum = true;
            display.setText(null);
        } catch (Exception e) {
            Toast.makeText(this, "No value!", Toast.LENGTH_SHORT).show();
        }
    }
    public void sub_btn(View view) {
        try {
            valueOne = Double.parseDouble(display.getText() + "");
            sub = true;
            display.setText(null);
        } catch (Exception e) {
            Toast.makeText(this, "No value!", Toast.LENGTH_SHORT).show();
        }
    }
    public void divide_btn(View view) {
        try {
            valueOne = Double.parseDouble(display.getText() + "");
            div = true;
            display.setText(null);
        } catch (Exception e) {
            Toast.makeText(this, "No value!", Toast.LENGTH_SHORT).show();
        }
    }
    public void equals_btn(View view) {
        try {
            valueTwo = Double.parseDouble(display.getText() + "");
            DecimalFormat df = new DecimalFormat("#.##############");
            if (sum) {
                display.setText(df.format(valueOne + valueTwo) + "");
                sum = false;
            }
            if (sub) {
                display.setText(df.format(valueOne - valueTwo) + "");
                sub = false;
            }
            if (mult) {
                display.setText(df.format(valueOne * valueTwo) + "");
                mult = false;
            }
            if (div) {
                display.setText(df.format(valueOne / valueTwo) + "");
                div = false;
            }
            display.setSelection(display.getText().length());
        } catch (Exception e) {
            Toast.makeText(this, "No value!", Toast.LENGTH_SHORT).show();
        }
    }
    public void clear_btn(View view) {
        display.setText("");
    }
    public void point_btn(View view) {
        String s = display.getText().toString();
        if (s.endsWith(".") || s.contains(".")) {} else {
            updateText(".");
        }
    }
    public void ms_btn(View view) {
        sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        editor = sharedPref.edit();
        value = sharedPref.getString("new_value", null);
        String val = display.getText().toString().trim();
        editor.putString("new_value", val);
        editor.apply();
        display.getText().clear();
    }
    public void mr_btn(View view) {
        try {
            String value = sharedPref.getString("new_value", null);
            display.setText(value);
            display.setSelection(display.getText().length());
        } catch (Exception e) {
            Toast.makeText(this, "No value stored!", Toast.LENGTH_SHORT).show();
        }
    }
    public void mc_btn(View view) {
        editor.clear();
        editor.commit();
    }
    public void backspace_btn(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

}