package com.example.CelisParcial1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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

public class UpdateContactoActivity extends AppCompatActivity {

    public final Calendar c = Calendar.getInstance();

    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    Contact contact;

    EditText etName;
    EditText etLastname;
    EditText etPhone;
    EditText etEmail;
    EditText etDate;
    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact_activity);
        setTitle("Actualizar Contacto");

        Intent i = getIntent();
        contact = i.getParcelableExtra("contact");

        etName = findViewById(R.id.etName);
        etLastname = findViewById(R.id.etLastname);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etDate = findViewById(R.id.etCumple);
        opciones = findViewById(R.id.spinnerGrupo);

        etName.setText(contact.getName());
        etLastname.setText(contact.getLastname());
        etPhone.setText(contact.getPhone());
        etEmail.setText(contact.getEmail());
        etDate.setText(contact.getBday());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_group, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        int spinpos = adapter.getPosition(contact.getGroup());
        opciones.setSelection(spinpos);
    }

    public void calendarClick(View v) {
        switch (v.getId()){
            case R.id.etCumple:
                closeKeyboard();
                obtenerFecha();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int month, int day) {
                final int mesActual = month + 1;
                String diaFormateado = (day < 10)? "0" + String.valueOf(day):String.valueOf(day);
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);
                System.out.println(year + "-" + mesFormateado + "-" + diaFormateado);
                etDate.setText(year + "-" + mesFormateado + "-" + diaFormateado);

            }
        },anio, mes, dia);
        recogerFecha.show();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void actualizarClick(View v) {

        if(TextUtils.isEmpty(etName.getText()) || TextUtils.isEmpty(etPhone.getText())){
            Toast.makeText(this, R.string.campos_minimos, Toast.LENGTH_LONG).show();
        }else{

            try {
                MyDataBase basedatos = new MyDataBase(this);
                SQLiteDatabase db = basedatos.getWritableDatabase();

                String nombre = etName.getText().toString();
                String apellido = etLastname.getText().toString();
                String tel = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                String cumple = etDate.getText().toString();
                String grupo = opciones.getSelectedItem().toString();

                contact.setName(nombre);
                contact.setLastname(apellido);
                contact.setPhone(tel);
                contact.setEmail(email);
                contact.setBday(cumple);
                contact.setGroup(grupo);

                basedatos.updateContacto(db,contact);

                Toast.makeText(this, R.string.contact_actualizado, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
            catch (Exception e){
            }

        }

    }
}
