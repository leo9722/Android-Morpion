package com.dirbuster.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class MainActivity2 extends AppCompatActivity {

    private DatabaseReference refJoueur;
    private DatabaseReference refPeutJouer;
    private DatabaseReference refGagne;
    private DatabaseReference refCases;

    private int [] tv = {0,0,0,0,0,0,0,0,0};
    private Button[] tb = new Button[9];

    private boolean peutjouer = true;
    private int joueur = 0;
    private boolean fini = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

}