package com.sinixcode.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView calc_txt,result_txt;
    MaterialButton buttonc,buttonob,buttoncb;
    MaterialButton buttondivide,buttonmulti,buttonpuls,buttonmin,buttonequal;
    MaterialButton buttonAc,buttonDot;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_txt = findViewById(R.id.result_txt);
        calc_txt = findViewById(R.id.calc_txt);

        assignId(buttonc,R.id.button_c);
        assignId(buttonob,R.id.button_ob);
        assignId(buttoncb,R.id.button_cb);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonmulti,R.id.button_multi);
        assignId(buttonpuls,R.id.button_plus);
        assignId(buttonmin,R.id.button_minus);
        assignId(buttonequal,R.id.button_equal);
        assignId(buttonAc,R.id.button_AC);
        assignId(buttonDot,R.id.button_dot);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);

    }


    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton buttton = (MaterialButton) view;
        String btnTxt = buttton.getText().toString();

        String datatoCalculate = result_txt.getText().toString();


        if(btnTxt.equals("AC")){
            calc_txt.setText("");
            result_txt.setText("0");
            return;
        }
        if (btnTxt.equals("=")){
            calc_txt.setText(result_txt.getText());
            return;
        }

        if(btnTxt.equals("C")){
            datatoCalculate = datatoCalculate.substring(0, datatoCalculate.length()-1);
        }else{
            datatoCalculate = datatoCalculate+btnTxt;
        }

        result_txt.setText(datatoCalculate);

        String finalResult = getResult(datatoCalculate);


        if (!finalResult.equals("Err")){
            result_txt.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }

            return finalResult;
        }
        catch (Exception e){
            return "Err";
        }
    }
}