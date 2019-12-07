package com.example.eval2_2411.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eval2_2411.dto.Equipo;
import com.example.eval2_2411.helpers.DatabaseHelper;

import java.util.ArrayList;

public class EquipoDAL {
    private DatabaseHelper dbHelper;
    private Equipo equipo;

    //Constructores
    public EquipoDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.equipo = new Equipo();
    }

    public EquipoDAL(Context context, Equipo equipo){
        this.dbHelper = new DatabaseHelper(context);
        this.equipo = equipo;
    }

    //Métodos CRUD
    //Actualizar
    public boolean actualizar(Equipo equipo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("id", equipo.getId());
        c.put("marca", equipo.getMarca());
        c.put("descripcion", equipo.getDescripcion());

        try {
            int filasAfectadas;
            filasAfectadas = db.update("equipo", c, "id = ?", new String[] { String.valueOf(equipo.getId())});
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }
        return false;
    }

    //Ver
    public ArrayList<Equipo> seleccionar() {
        ArrayList<Equipo> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM equipo", null);

        if(consulta.moveToFirst()) {
            do {
                int id = consulta.getInt(0);
                String marca = consulta.getString(1);
                String descripcion = consulta.getString(2);

                Equipo equipo = new Equipo(id,marca,descripcion);
                lista.add(equipo);

            } while(consulta.moveToNext());
        }
        return lista;
    }

    //Eliminar
    public boolean eliminar(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int filasAfectadas;

        try {
            filasAfectadas = db.delete("equipo","id = ?", new String[] { String.valueOf(id)});
        } catch (Exception e) {
            return false;
        }

        return (filasAfectadas == 1);
    }

    //????
    public Equipo getEquipo() {
        return this.equipo;
    }
}
