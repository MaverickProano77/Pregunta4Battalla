package com.example.batallapst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtCodigo, txtTitulo, txtAno, txtUrl, txtDescipcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCodigo=(EditText)findViewById(R.id.edtCodigo);
        txtTitulo=(EditText)findViewById(R.id.edtTitulo);
        txtAno=(EditText)findViewById(R.id.edtAno);
        txtUrl=(EditText)findViewById(R.id.edtUrl);
        txtDescipcion=(EditText)findViewById(R.id.edtDescripcion);
    }
    public void registrar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = txtCodigo.getText().toString();
        String tit = txtTitulo.getText().toString();
        String ano = txtAno.getText().toString();
        String url = txtUrl.getText().toString();
        String descri = txtDescipcion.getText().toString();
        bd.execSQL("insert into peliculas (codigo,titulo,anio,url,descripcion) values ('"+cod+"','"+tit+"','"+ano+"','"+url+"','"+descri+"')");
        bd.close();
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtAno.setText("");
        txtUrl.setText("");
        txtDescipcion.setText("");
        Toast.makeText(this, "Se cargaron los datos de la pelicula",
                Toast.LENGTH_SHORT).show();
    }
    public void consultar(View v){
        Intent i = new Intent (this, Consultar.class);
        i.putExtra("codigo",txtCodigo.getText().toString());
        i.putExtra("titulo",txtTitulo.getText().toString());
        i.putExtra("anio",txtAno.getText().toString());
        i.putExtra("urlportada",txtUrl.getText().toString());
        i.putExtra("descipcion",txtDescipcion.getText().toString());
        startActivity(i);
    }
    /*
    public void editarUrl(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom= txtTitulo.getText().toString();
        String link = txtUrl.getText().toString();
            bd.execSQL("update peliculas set url="+link+" where titulo="+nom);
            Toast.makeText(this, "Cambio de URL exitoso",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }*/
}