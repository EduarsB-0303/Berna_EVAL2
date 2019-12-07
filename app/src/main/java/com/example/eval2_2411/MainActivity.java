package com.example.eval2_2411;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eval2_2411.dal.EquipoDAL;
import com.example.eval2_2411.dto.Equipo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitulo;
    private ListView listEquipo;
    private EquipoDAL equipoDAL;
    private ArrayList<Equipo> listaEquipo;
    private ArrayAdapter<Equipo> adapter;
    private int codPosicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.equipoDAL = new EquipoDAL(getApplicationContext(), new Equipo());
        this.listaEquipo = new EquipoDAL(getBaseContext()).seleccionar();

        this.txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        this.listEquipo = (ListView) findViewById(R.id.listEquipo);

        this.adapter = new ArrayAdapter<Equipo>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                this.listaEquipo
        ){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                textView.setTextColor(Color.BLACK);

                return view;
            }
        }
        ;

        this.listEquipo.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea borrar el equipo?");
        builder.setPositiveButton("Si, borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id = ((Equipo) listaEquipo.get(codPosicion)).getId();
                boolean r = equipoDAL.eliminar(id);
                if(r){
                    Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                    actualizarLista();
                } else {
                    Toast.makeText(getApplicationContext(), "No es posible eliminar el equipo", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog dialog = builder.create();

        listEquipo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                dialog.show();
                return true;
            }
        });

        listEquipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                abrirEditEquipoActivity();
            }
        });

    }

    private void abrirEditEquipoActivity() {
        Intent intento = new Intent(MainActivity.this, editEquipo.class);
        Equipo e = (Equipo) listaEquipo.get(codPosicion);

        intento.putExtra("equipo", e);
        startActivityForResult(intento, 100);
    }

    private void actualizarLista() {
        adapter.clear();
        adapter.addAll(equipoDAL.seleccionar());
        adapter.notifyDataSetChanged();
    }

}
