package com.dirbuster.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Game extends AppCompatActivity {

    private DatabaseReference refJoueur;
    private DatabaseReference refPeutJouer;
    private DatabaseReference refGagne;
    private DatabaseReference refCases;
    String playerName = "";
    String roomName ="";
    String role ="";



    private int [] tv = {0,0,0,0,0,0,0,0,0};
    private Button[] tb = new Button[9];

    private boolean peutjouer = true;
    private int joueur = 0;
    private boolean fini = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refCases = database.getReference("cases");
        refCases.child("0").setValue(2);
        refCases.child("1").setValue(2);
        refCases.child("2").setValue(2);
        refCases.child("3").setValue(2);
        refCases.child("4").setValue(2);
        refCases.child("5").setValue(2);
        refCases.child("6").setValue(2);
        refCases.child("7").setValue(2);
        refCases.child("8").setValue(2);

        refGagne = database.getReference("gagne");
        refGagne.setValue(1);
        refJoueur = database.getReference("joueur");
        refJoueur.child("j1").setValue(1);
        refJoueur.child("j2").setValue(1);
        refPeutJouer = database.getReference("peutjouer");
        refPeutJouer.setValue(2);

        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        playerName = preferences.getString("playerName", "");

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            roomName = extras.getString("roomName");
            if(roomName.equals(playerName)){
                role = "host";
            } else{
                role= "guest";
            }
        }





    }

}