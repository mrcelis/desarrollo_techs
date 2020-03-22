package com.example.CelisParcial1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainMenu extends AppCompatActivity {
    String items[] = new String[] {"Contacts","Add","Quit"};
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        setTitle("Main Menu");
    }

    public void listado(View v){
        startActivity(new Intent(getApplicationContext(), ListContacts.class));
    }

    public void nuevo(View v){
        startActivity(new Intent(getApplicationContext(), NewContact.class));
    }

    public void salir(View v){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
