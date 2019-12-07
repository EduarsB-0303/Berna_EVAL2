package com.example.eval2_2411;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eval2_2411.dal.EquipoDAL;
import com.example.eval2_2411.dto.Equipo;

public class editEquipo extends AppCompatActivity {
    private EditText editMarca;
    private EditText editDescripcion;
    private Button btnActualizar;
    private EquipoDAL equipoDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_equipo);

        this.editMarca = (EditText) findViewById(R.id.editMarca);
        this.editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        this.btnActualizar = (Button) findViewById(R.id.btnActualizar);

        this.equipoDAL = new EquipoDAL(getApplicationContext(), (Equipo) getIntent().getSerializableExtra("equipo"));


        this.editMarca.setText(equipoDAL.getEquipo().getMarca());
        this.editDescripcion.setText(equipoDAL.getEquipo().getDescripcion());

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Equipo e = equipoDAL.getEquipo();
                e.setMarca(String.valueOf(editMarca.getText()));
                e.setDescripcion(String.valueOf(editDescripcion.getText()));

                if(equipoDAL.actualizar(e)) {
                    Toast.makeText(getApplicationContext(), "Actualizado!", Toast.LENGTH_LONG).show();
                    volverMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "NO Actualizado!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void volverMainActivity() {
        Intent intento = new Intent(editEquipo.this, MainActivity.class);
        startActivity(intento);
    }
}
