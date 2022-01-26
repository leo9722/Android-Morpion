package com.dirbuster.morpion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

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

    private boolean peutjouer=true;
    private int joueur = 1;
    private boolean fini = false;
    int joueur1;
    int joueur2;
    int message;
    int couleurgagnant;
    TextView player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        joueur1 = Color.parseColor("#EF6461");
        joueur2 = Color.parseColor("#E4B363");
        message=Color.parseColor("#687478");
        tb[0]=(Button) findViewById(R.id.button0);
        tb[1]=(Button) findViewById(R.id.button1);
        tb[2]=(Button) findViewById(R.id.button2);
        tb[3]=(Button) findViewById(R.id.button3);
        tb[4]=(Button) findViewById(R.id.button4);
        tb[5]=(Button) findViewById(R.id.button5);
        tb[6]=(Button) findViewById(R.id.button6);
        tb[7]=(Button) findViewById(R.id.button7);
        tb[8]=(Button) findViewById(R.id.button8);
        player=(TextView) findViewById(R.id.textPLayer);


        game();
    }
    public  void game(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refCases = database.getReference("cases");
        refCases.child("0").setValue(0);
        refCases.child("1").setValue(0);
        refCases.child("2").setValue(0);
        refCases.child("3").setValue(0);
        refCases.child("4").setValue(0);
        refCases.child("5").setValue(0);
        refCases.child("6").setValue(0);
        refCases.child("7").setValue(0);
        refCases.child("8").setValue(0);


        refCases.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                /************************************ Colorie les cases en fonctions de leurs valeurs ****************************************/

                int basique = Color.parseColor("#313638");
                for (int j = 0; j < 9; j++) {
                    if(snapshot.child(String.valueOf(j)).getValue(int.class)==2){
                        tb[j].setBackgroundColor(joueur1);
                    }else if(snapshot.child(String.valueOf(j)).getValue(int.class)==1){
                        tb[j].setBackgroundColor(joueur2);
                    }else if(snapshot.child(String.valueOf(j)).getValue(int.class)==0){
                        tb[j].setBackgroundColor(basique);
                    }

                }

                /************************************ test si toutes les cases sont remplie ****************************************/

                int testcaserempli=0;
                for (int i = 0; i < 9; i++) { // test si toute les cases sont rempl
                    tv[i]=snapshot.child(String.valueOf(i)).getValue(int.class);
                    if (tv[i]==0){
                        testcaserempli+=1;
                    }
                }

                /************************************ détermine le gagant ****************************************/

                if((tv[0]==tv[1]&& tv[0]==tv[2] )&&tv[0]!=0){ //ligne 1
                    wingame(tv[0]);
                }else if((tv[3]==tv[4]&& tv[3]==tv[5] )&&tv[3]!=0){//ligne 2
                    wingame(tv[3]);
                }else if((tv[6]==tv[7]&& tv[6]==tv[8] )&&tv[6]!=0){//ligne 3
                    wingame(tv[6]);
                }else if((tv[0]==tv[3]&& tv[0]==tv[6] )&&tv[0]!=0){ //colonne 1
                    wingame(tv[0]);
                }else if((tv[1]==tv[4]&& tv[1]==tv[7] )&&tv[1]!=0){//colonne 2
                    wingame(tv[1]);
                }else if((tv[2]==tv[5]&& tv[2]==tv[8] )&&tv[2]!=0){//colonne 3
                    wingame(tv[2]);
                } else if((tv[0]==tv[4]&& tv[0]==tv[8] )&&tv[0]!=0){ //diagonale 1
                    wingame(tv[0]);
                }else if((tv[2]==tv[4]&& tv[2]==tv[6] )&&tv[2]!=0){ //diagonale 2
                    wingame(tv[2]);
                }else if(testcaserempli==0 && peutjouer!=false){ // égalité
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(0);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setText("Egalité ! ");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        refGagne = database.getReference("gagne");
        refGagne.setValue(-1);
        refGagne.addValueEventListener(new ValueEventListener() {
            @Override
            /************************************ Détermine si on peut jouer ou non****************************************/

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int gagne =snapshot.getValue(int.class);
                if (gagne==1||gagne==2||gagne==0){
                    peutjouer=false;
                }else{
                    peutjouer=true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        refJoueur = database.getReference("joueur");
        refJoueur.child("j1").setValue(1);
        refJoueur.child("j2").setValue(1);
        refPeutJouer = database.getReference("peutjouer");
        refPeutJouer.setValue(2);

        refPeutJouer.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            /************************************ Gestion des messages****************************************/

                Log.d("valeur peut jouer",snapshot.getValue().toString());
                joueur=snapshot.getValue(int.class);


                if (role.equals("host") && joueur==1) {
                    player.setTextColor(message);
                    player.setText("A vous de jouer");
                }else if (role.equals("guest") && joueur==2) {
                    player.setTextColor(message);
                    player.setText("A vous de jouer");
                }else if (joueur!=0){
                    player.setTextColor(message);
                    player.setText("En attente du deuxième joueur");
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /************************************ Attribution des roles****************************************/
        SharedPreferences preferences = getSharedPreferences("users",0);
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

    /************************************ Interaction avec les boutons du jeu****************************************/

    public void clickButton( View view){
        Button bouton =(Button) findViewById(view.getId());

        Log.d("ref joueur",role);
        Log.d("peut jouer",String.valueOf(joueur));
        Log.d("num Button",String.valueOf(bouton.getText()));
        if(peutjouer){
            if (role.equals("host") && joueur==1) {
                if(tv[Integer.valueOf(bouton.getText().toString())]==0) {
                    //bouton.setBackgroundColor(joueur2);
                    refPeutJouer.setValue(2);
                    refCases.child(bouton.getText().toString()).setValue(1);
                }

            }else if (role.equals("guest") && joueur==2){
                if(tv[Integer.valueOf(bouton.getText().toString())]==0){
                   // bouton.setBackgroundColor(joueur1);
                    refPeutJouer.setValue(1);
                    refCases.child(bouton.getText().toString()).setValue(2);
                }



            }
        }


    }

    /************************************ bouton reset****************************************/

    @SuppressLint("ResourceAsColor")
    public void clickReset(View view){
        game();
        player.setTextColor(message);
        peutjouer=true;
        refPeutJouer.setValue(2);
        refGagne.setValue(-1);

    }

    /************************************ gestion des paramètres lors de la victoire d'un joueur****************************************/

    public void wingame(int nbwinner){
        peutjouer=false;
        fini = true;
        refGagne.setValue(nbwinner);
        refPeutJouer.setValue(0);
        joueur=0;
        couleurgagnant=(nbwinner==1)?joueur2:joueur1;
        player.setTextColor(couleurgagnant);
        player.setText("Le joueur "+nbwinner+" a gagné");


    }

}