package com.example.CelisParcial1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.CelisParcial1.DBMODELS.MyDataBase;

import java.util.ArrayList;

public class ListContacts extends AppCompatActivity {
    ArrayList<Contact> list;
    CustomAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact_activity);
        setTitle("Contacts");

        lv = findViewById(R.id.contact_list);
        adapter = new CustomAdapter(this, getData());
        lv.setAdapter(adapter);

        registerForContextMenu(lv);
    }

    public ArrayList<Contact> getData() {
        list = new ArrayList<Contact>();
        try {
            MyDataBase basedatos = new MyDataBase(this);
            SQLiteDatabase db = basedatos.getWritableDatabase();

            Cursor cursor = basedatos.allContactos(db);

            while (cursor.moveToNext()) {
                Contact s = new Contact();
                s.setId(cursor.getString(0));
                s.setName(cursor.getString(1));
                s.setLastname(cursor.getString(2));
                s.setPhone(cursor.getString(3));
                s.setEmail(cursor.getString(4));
                s.setBday(cursor.getString(5));
                s.setGroup(cursor.getString(6));
                list.add(s);
            }

        } catch (Exception e) {
        }
        return list;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        int id = v.getId();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cm_contactos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;

        switch (item.getItemId()){
            case R.id.editar_contacto:
                Intent i = new Intent(this,UpdateContactoActivity.class);
                i.putExtra("contact", list.get(pos));
                startActivity(i);
                return true;
            case R.id.eliminar_contacto:
                try {
                    MyDataBase basedatos = new MyDataBase(this);
                    SQLiteDatabase db = basedatos.getWritableDatabase();


                    basedatos.borrarContacto(db,list.get(pos));
                    list.remove(pos);
                    updateList();

                    Toast.makeText(this,"Contacto Eliminado", Toast.LENGTH_LONG).show();

                }catch (Exception e){ }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void updateList(){
        ((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
    }
}
