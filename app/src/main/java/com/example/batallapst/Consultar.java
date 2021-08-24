package com.example.batallapst;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Consultar extends AppCompatActivity {
    EditText nombre;
    TextView ano, descrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        nombre=(EditText)findViewById(R.id.edtNombre);
        ano=(TextView)findViewById(R.id.txtAno);
        descrip=(TextView)findViewById(R.id.txtDescipcion);
        /*
        //String codigo = getIntent().getStringExtra("codigo");
        String titulo = getIntent().getStringExtra("titulo");
        String anio = getIntent().getStringExtra("anio");
        //String url = getIntent().getStringExtra("urlportada");
        String descripcion = getIntent().getStringExtra("descripcion");

        //nombre.setText(titulo);
        //ano.setText(anio);
        //descrip.setText(descripcion); */
    }
    public void consulta(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String nom= nombre.getText().toString();
        Cursor fila = bd.rawQuery(
                "select anio,url,descripcion from peliculas where titulo like '" + nom +"'", null);
        if (fila.moveToFirst()) {
            ano.setText(fila.getString(0));
            descrip.setText(fila.getString(2));
            String link = fila.getString(1);
            WebView web = (WebView) findViewById(R.id.web);
            WebSettings opciones = web.getSettings();
            opciones.setJavaScriptEnabled(true);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("https://"+link);
            Toast.makeText(this, "Consulta exitosa de pelicula",
                    Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "No existe una pelicula con dicho titulo",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void regresar(View v){
        finish();
    }
}