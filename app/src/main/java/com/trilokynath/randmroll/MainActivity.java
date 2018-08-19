package com.trilokynath.randmroll;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    Button a,b;
    ImageView ai,bi;
    AlertDialog alertDialog;

    String cls1 = "DIV A";
    String cls2 = "DIV B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        if (v1.getParent() == null) {
            alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog))
                    .setTitle("Generate Random Numbers")
                    .setView(v1)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            finish();
                            System.exit(0);
                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            alertDialog.show();
                        }
                    })
                    .show();
        }


        a = v1.findViewById(R.id.a);
        b = v1.findViewById(R.id.b);
        ai = v1.findViewById(R.id.ai);
        bi = v1.findViewById(R.id.bi);

        SharedPreferences prefs;
        prefs = getSharedPreferences("status", MODE_PRIVATE);
        if (prefs != null) {
            cls1 = prefs.getString("Class", "DIV A");
            cls2 = prefs.getString("Class", "DIV B");
        }

        a.setText(cls1);
        b.setText(cls2);

        a.setTextColor(Color.BLACK);
        b.setTextColor(Color.BLACK);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), Generate.class);
                myIntent.putExtra("div", "A");
                startActivityForResult(myIntent, 0);
            }

        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), Generate.class);
                myIntent.putExtra("div", "B");
                startActivityForResult(myIntent, 0);
            }
        });

        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), Edit_Div.class);
                myIntent.putExtra("div", "A");
                startActivityForResult(myIntent, 0);
            }
        });
        bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), Edit_Div.class);
                myIntent.putExtra("div", "B");
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs;
        prefs = getSharedPreferences("status", MODE_PRIVATE);
        if (prefs != null) {
            cls1 = prefs.getString("Class", "DIV A");
            cls2 = prefs.getString("Class1", "DIV B");
        }
        a.setText(cls1);
        b.setText(cls2);
    }
}
