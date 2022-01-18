package com.dirbuster.morpion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void play(View v) {
        EditText editText1 = findViewById(R.id.username);
        String valA = editText1.getText().toString();
        if (editText1.getText().toString().isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Enter a username");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
    }
}