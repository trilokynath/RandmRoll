package com.trilokynath.randmroll;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Edit_Div extends Activity {

    EditText a,b,c;
    AlertDialog alertDialog;
    int High=0;
    int Low= 0;
    String div = "A";
    String cls = "Class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent iin= getIntent();
        Bundle bundle = iin.getExtras();
        if(bundle!=null)
            div = (String) bundle.get("div");

        final View v1 = LayoutInflater.from(Edit_Div.this).inflate(R.layout.edit_div, null);
        a = v1.findViewById(R.id.a);
        b = v1.findViewById(R.id.b);
        c = v1.findViewById(R.id.name);
        if (v1.getParent() == null) {
            alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(Edit_Div.this, R.style.myDialog))
                    .setView(v1)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            finish();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            finish();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                    })
                    .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String Low = String.valueOf(a.getText());
                            String High = String.valueOf(b.getText());
                            String cl = String.valueOf(c.getText());
                            if(Integer.parseInt(High)>Integer.parseInt(Low)) {
                                if(!cl.isEmpty()) {
                                    SharedPreferences.Editor editor =
                                            getSharedPreferences("status", MODE_PRIVATE).edit();
                                    if (div.equalsIgnoreCase("A")) {
                                        editor.putInt("High", Integer.parseInt(High));
                                        editor.putInt("Low", Integer.parseInt(Low));
                                        editor.putString("Class", cl);
                                    } else {
                                        editor.putInt("High1", Integer.parseInt(High));
                                        editor.putInt("Low1", Integer.parseInt(Low));
                                        editor.putString("Class1", cl);
                                    }
                                    editor.apply();
                                    finish();
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Name should not be empty", Toast.LENGTH_SHORT).show();
                                    alertDialog.show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"Incorrect Values", Toast.LENGTH_SHORT).show();
                                alertDialog.show();
                            }
                        }
                    })
                 //   .setNegativeButton(android.R.string.no, null)
                    .show();
        }

        a.setTextColor(Color.BLACK);
        b.setTextColor(Color.BLACK);
        c.setTextColor(Color.BLACK);


        SharedPreferences prefs;
        prefs = getSharedPreferences("status", MODE_PRIVATE);
        if (prefs != null) {
            if(div.equalsIgnoreCase("A")) {
                High = prefs.getInt("High", 1);
                Low = prefs.getInt("Low", 0);
                cls = prefs.getString("Class", "DIV A");
            }else{
                High = prefs.getInt("High1", 1);
                Low = prefs.getInt("Low1", 0);
                cls = prefs.getString("Class1", "DIV B");
            }
        }

        a.setText(String.format("%d", Low));
        b.setText(String.format("%d", High));
        c.setText(cls);

    }


}
