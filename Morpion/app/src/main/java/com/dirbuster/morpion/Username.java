package com.dirbuster.morpion;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Username extends AppCompatActivity {

    Button button;
    FirebaseDatabase database;
    String playerName ="";
    DatabaseReference playRef;

    private static final String LOG_TAG = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main3);
    }

    public void play(View v) {
        EditText editText1 = findViewById(R.id.username);
        String valA = editText1.getText().toString();
        if (editText1.getText().toString().isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(Username.this).create();
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
            database = FirebaseDatabase.getInstance();
            button =findViewById(R.id.buttonplay);

            SharedPreferences preferences = getSharedPreferences("PREFS",0);
            playerName =  preferences.getString("playerName", "");
            if(!playerName.equals("")){
                playRef = database.getReference("players/" + playerName);
                addEventListener();
                playRef.setValue("");

            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playerName = editText1.getText().toString();
                    editText1.setText("");
                    if (!playerName.equals("")){
                        button.setText("OKEY BOSS");
                        button.setEnabled(false);
                        playRef = database.getReference("players/" + playerName);
                        addEventListener();
                        playRef.setValue("");


                    }
                }
            });

        }
        }


    private void addEventListener(){
        playRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!playerName.equals("")){
                    SharedPreferences preferences = getSharedPreferences("users", 0);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("playerName",playerName);
                    edit.apply();


                    startActivity(new Intent (getApplicationContext(), Multiplayer.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                button.setText("OKEY BOSS");
                button.setEnabled(true);
                Toast.makeText(Username.this,"ERROR",Toast.LENGTH_SHORT).show();
            }
        });
    }
}