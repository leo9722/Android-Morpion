package com.dirbuster.morpion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Multiplayer extends AppCompatActivity {

    ListView listView;
    Button button;

    List<String> roomList;

    String playerName = "";
    String roomName = "";

    FirebaseDatabase database;
    DatabaseReference roomRef;
    DatabaseReference roomsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        database = FirebaseDatabase.getInstance();


        SharedPreferences preferences= getSharedPreferences("PREFS",0);
        playerName = preferences.getString("playerName", "");
        roomName = playerName;

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button9);


        roomList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("CRAETING ROOM");
                button.setEnabled(false);
                roomName = playerName;
                roomRef = database.getReference("rooms/" + roomName + "/player1");
                addRoomEventListener();
                roomRef.setValue(playerName);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                roomName = roomList.get(position);
                roomRef = database.getReference("rooms/" + roomName +"/player2");
                addRoomEventListener();
                roomRef.setValue(playerName);


            }
        });

        addRoomsEventListener();
    }

    private void addRoomEventListener(){
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                button.setText("CREATE ROOM");
                button.setEnabled(true);
                Intent intent = new Intent(getApplicationContext(),Game.class);
                intent.putExtra("roomName",roomName);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                button.setText("CREATE ROOM");
                button.setEnabled(true);
                Toast.makeText(Multiplayer.this,"ERROR",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addRoomsEventListener(){
        roomsRef = database.getReference("rooms");
        roomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomList.clear();
                Iterable<DataSnapshot> rooms = dataSnapshot.getChildren();
                for (DataSnapshot snapshot1 : rooms){
                    roomList.add(snapshot1.getKey());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Multiplayer.this,
                            android.R.layout.simple_list_item_1,roomList);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}