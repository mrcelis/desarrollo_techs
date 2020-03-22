package com.example.CelisParcial1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.CelisParcial1.DBMODELS.MyDataBase;

import java.util.Calendar;

public class NewContact extends AppCompatActivity {

    public final Calendar c = Calendar.getInstance();

    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int year = c.get(Calendar.YEAR);

    EditText etName;
    EditText etLastname;
    EditText etPhone;
    EditText etEmail;
    EditText etDate;
    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_activity);
        setTitle("Add Contact");

        etName = findViewById(R.id.etName);
        etLastname = findViewById(R.id.etLastname);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etDate = findViewById(R.id.etBday);
        opciones = findViewById(R.id.spinnerGrupo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_group, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
    }

    public void calendarClick(View v) {
        switch (v.getId()){
            case R.id.etBday:
                closeKeyboard();
                getDate();
                break;
        }
    }

    private void getDate(){
        DatePickerDialog pickDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int month, int day) {
                final int monthActual = month + 1;
                String dayFormateado = (day < 10)? "0" + String.valueOf(day):String.valueOf(day);
                String monthFormateado = (monthActual < 10)? "0" + String.valueOf(monthActual):String.valueOf(monthActual);
                System.out.println(year + "-" + monthFormateado + "-" + dayFormateado);
                etDate.setText(year + "-" + monthFormateado + "-" + dayFormateado);

            }
        },year, month, day);
        pickDate.show();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void addClick(View v) {

        if(TextUtils.isEmpty(etName.getText()) || TextUtils.isEmpty(etPhone.getText())){
            Toast.makeText(this, R.string.campos_minimos, Toast.LENGTH_LONG).show();
        }else{

            try {
                MyDataBase basedatos = new MyDataBase(this);
                SQLiteDatabase db = basedatos.getWritableDatabase();

                String name = etName.getText().toString();
                String lastname = etLastname.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                String bday = etDate.getText().toString();
                String group = opciones.getSelectedItem().toString();

                Contact contact = new Contact();
                contact.setName(name);
                contact.setLastname(lastname);
                contact.setPhone(phone);
                contact.setEmail(email);
                contact.setBday(bday);
                contact.setGroup(group);

                basedatos.insertContacto(db,contact);

                Toast.makeText(this, R.string.contact_registrado, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
            catch (Exception e){
            }

        }

    }

}
