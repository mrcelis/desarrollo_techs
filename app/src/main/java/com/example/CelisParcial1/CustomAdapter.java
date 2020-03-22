package com.example.CelisParcial1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Contact> mitem;

    public CustomAdapter(Context c, ArrayList<Contact> mitem) {
        this.c = c;
        this.mitem = mitem;
    }

    @Override
    public int getCount() {
        return mitem.size();
    }

    @Override
    public Object getItem(int i) {
        return mitem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.contact_item_model,viewGroup,false);
        }

        final Contact contact= (Contact) this.getItem(i);

        TextView name = view.findViewById(R.id.nombre);
        TextView tel= view.findViewById(R.id.telefono);
        TextView email= view.findViewById(R.id.email);

        name.setText(contact.getName()+" "+contact.getLastname());
        tel.setText(contact.getPhone());
        email.setText(contact.getEmail());

        return view;
    }

}
