package com.dirbuster.morpion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int basique = Color.parseColor("#FF3F51B5");

                Button bouton0=(Button) findViewById(R.id.button0);
                if(snapshot.child(String.valueOf(0)).getValue(int.class)==2){
                    bouton0.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(0)).getValue(int.class)==1){
                    bouton0.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(0)).getValue(int.class)==0){
                    bouton0.setBackgroundColor(basique);
                }
                Button bouton1=(Button) findViewById(R.id.button1);
                if(snapshot.child(String.valueOf(1)).getValue(int.class)==2){
                    bouton1.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(1)).getValue(int.class)==1){
                    bouton1.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(1)).getValue(int.class)==0){
                    bouton1.setBackgroundColor(basique);
                }
                Button bouton2=(Button) findViewById(R.id.button2);
                if(snapshot.child(String.valueOf(2)).getValue(int.class)==2){
                    bouton2.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(2)).getValue(int.class)==1){
                    bouton2.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(2)).getValue(int.class)==0){
                    bouton2.setBackgroundColor(basique);
                }
                Button bouton3=(Button) findViewById(R.id.button3);
                if(snapshot.child(String.valueOf(3)).getValue(int.class)==2){
                    bouton3.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(3)).getValue(int.class)==1){
                    bouton3.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(3)).getValue(int.class)==0){
                    bouton3.setBackgroundColor(basique);
                }
                Button bouton4=(Button) findViewById(R.id.button4);
                if(snapshot.child(String.valueOf(4)).getValue(int.class)==2){
                    bouton4.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(4)).getValue(int.class)==1){
                    bouton4.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(4)).getValue(int.class)==0){
                    bouton4.setBackgroundColor(basique);
                }
                Button bouton5=(Button) findViewById(R.id.button5);
                if(snapshot.child(String.valueOf(5)).getValue(int.class)==2){
                    bouton5.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(5)).getValue(int.class)==1){
                    bouton5.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(5)).getValue(int.class)==0){
                    bouton5.setBackgroundColor(basique);
                }
                Button bouton6=(Button) findViewById(R.id.button6);
                if(snapshot.child(String.valueOf(6)).getValue(int.class)==2){
                    bouton6.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(6)).getValue(int.class)==1){
                    bouton6.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(6)).getValue(int.class)==0){
                    bouton6.setBackgroundColor(basique);
                }
                Button bouton7=(Button) findViewById(R.id.button7);
                if(snapshot.child(String.valueOf(7)).getValue(int.class)==2){
                    bouton7.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(7)).getValue(int.class)==1){
                    bouton7.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(7)).getValue(int.class)==0){
                    bouton7.setBackgroundColor(basique);
                }
                Button bouton8=(Button) findViewById(R.id.button8);
                if(snapshot.child(String.valueOf(8)).getValue(int.class)==2){
                    bouton8.setBackgroundColor(Color.RED);
                }else if(snapshot.child(String.valueOf(8)).getValue(int.class)==1){
                    bouton8.setBackgroundColor(Color.GREEN);
                }else if(snapshot.child(String.valueOf(8)).getValue(int.class)==0){
                    bouton8.setBackgroundColor(basique);
                }

                int testcaserempli=0;

                for (int i = 0; i < 9; i++) {
                    tv[i]=snapshot.child(String.valueOf(i)).getValue(int.class);
                    if (tv[i]==0){
                        testcaserempli+=1;
                    }

                }

                TextView player=(TextView) findViewById(R.id.textPLayer);
                if((tv[0]==tv[1]&& tv[0]==tv[2] )&&tv[0]!=0){ //lignes
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[0]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[0]+" a gagné");


                }else if((tv[3]==tv[4]&& tv[3]==tv[5] )&&tv[3]!=0){
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[0]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[3]+" a gagné");
                }else if((tv[6]==tv[7]&& tv[6]==tv[8] )&&tv[6]!=0){
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[0]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[6]+" a gagné");
                }

                if((tv[0]==tv[3]&& tv[0]==tv[6] )&&tv[0]!=0){ //colonnes
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[0]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[0]+" a gagné");


                }else if((tv[1]==tv[4]&& tv[1]==tv[7] )&&tv[1]!=0){
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[1]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[1]+" a gagné");
                }else if((tv[2]==tv[5]&& tv[2]==tv[8] )&&tv[2]!=0){
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[2]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[2]+" a gagné");
                }

                if((tv[0]==tv[4]&& tv[0]==tv[8] )&&tv[0]!=0){ //diagonales
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[0]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[0]+" a gagné");


                }else if((tv[2]==tv[4]&& tv[2]==tv[6] )&&tv[2]!=0){
                    peutjouer=false;
                    fini = true;
                    refGagne.setValue(tv[2]);
                    refPeutJouer.setValue(0);
                    joueur=0;
                    player.setTextColor(Color.GREEN);
                    player.setText("Le joueur "+tv[2]+" a gagné");

                }
                if(testcaserempli==0 && peutjouer!=false){
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
        refGagne.setValue(1);
        refJoueur = database.getReference("joueur");
        refJoueur.child("j1").setValue(1);
        refJoueur.child("j2").setValue(1);
        refPeutJouer = database.getReference("peutjouer");
        refPeutJouer.setValue(2);

        refPeutJouer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.d("valeur peut jouer",snapshot.getValue().toString());
                joueur=snapshot.getValue(int.class);
                TextView player=(TextView) findViewById(R.id.textPLayer);
                if (role.equals("host") && joueur==1) {
                    player.setText("A vous de jouer");
                }else if (role.equals("guest") && joueur==2) {
                    player.setText("A vous de jouer");
                }else if (joueur!=0){
                    player.setText("En attente du deuxième joueur");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SharedPreferences preferences = getSharedPreferences("users",0);
        playerName = preferences.getString("playerName", "");

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            roomName = extras.getString("roomName");
            System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+roomName);
            System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+playerName);

            if(roomName.equals(playerName)){
                role = "host";

            } else{
                role= "guest";

            }
        }


    }


    public void clickButton( View view){
        Button bouton =(Button) findViewById(view.getId());

        Log.d("ref joueur",role);
        Log.d("peut jouer",String.valueOf(joueur));
        Log.d("num Button",String.valueOf(bouton.getText()));
        if(peutjouer){
            if (role.equals("host") && joueur==1) {
                if(tv[Integer.valueOf(bouton.getText().toString())]==0) {
                    //bouton.setBackgroundColor(Color.GREEN);
                    refPeutJouer.setValue(2);
                    refCases.child(bouton.getText().toString()).setValue(1);
                }

            }else if (role.equals("guest") && joueur==2){
                if(tv[Integer.valueOf(bouton.getText().toString())]==0){
                   // bouton.setBackgroundColor(Color.RED);
                    refPeutJouer.setValue(1);
                    refCases.child(bouton.getText().toString()).setValue(2);
                }



            }
        }


    }

    public void clickReset( View view){
        game();
        TextView player=(TextView) findViewById(R.id.textPLayer);
        player.setTextColor(Color.WHITE);
        peutjouer=true;
        refGagne.setValue(1);
        refPeutJouer.setValue(2);

    }

}