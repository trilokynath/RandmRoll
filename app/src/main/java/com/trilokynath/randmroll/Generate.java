package com.trilokynath.randmroll;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.math.MathUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Generate extends Activity {

    Button button;
    int High =1;
    int Low = 0;
    String div = "A";
    ImageView c;
    Integer index =0;
    ArrayList<Integer> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent iin= getIntent();
        Bundle bundle = iin.getExtras();
        if(bundle!=null)
            div = (String) bundle.get("div");

        View v1 = LayoutInflater.from(Generate.this).inflate(R.layout.generate, null);
        if (v1.getParent() == null) {
            Dialog alertDialog = new Dialog(this);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(v1);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();
            alertDialog.setCancelable(false);
        }


        SharedPreferences prefs;
        prefs = getSharedPreferences("status", MODE_PRIVATE);
        if (prefs != null) {
            if(div.equalsIgnoreCase("A")) {
                High = prefs.getInt("High", 1);
                Low = prefs.getInt("Low", 0);
            }else{
                High = prefs.getInt("High1", 1);
                Low = prefs.getInt("Low1", 0);
            }
        }

        button = (Button) v1.findViewById(R.id.button);
        c = (ImageView) v1.findViewById(R.id.close);
        c.setImageResource(R.drawable.ic_close);
        button.setTextColor(Color.BLACK);
        final Random random = new Random();
        button.setText("Tap");
        arrayList = new ArrayList<Integer>();
        arrayList = getArrayList(Low, High+1);

        index = 0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index==High-Low+1) index = 0;
                final int val =arrayList.get(index);
                index++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setText(String.format("%s", val));
                    }
                });
            }

        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

    public ArrayList<Integer> getArrayList(int min, int max){
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(min);
        for(int i = min; i < max-1; i++) {
            numbers.add(i+1);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

}
