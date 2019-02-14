package com.example.jose.intentimplicito;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    Button mBotonCompartir;
    EditText mTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBotonCompartir = findViewById(R.id.botonCompartir);
        mTexto = findViewById(R.id.cuadroTexto);

        mBotonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verPaginaWeb();
            }
        });


    }



    private void verPaginaWeb() {

        String url = mTexto.getText().toString();

          if(URLUtil.isValidUrl(url)){ // Si formato de la url es correcto

            Uri paginaWeb = Uri.parse(mTexto.getText().toString());

            // Intent implicito, sólo indicamos la acción a realizar. En este caso la acción
            // es una de las predefinidas por Android (Intent.ACTION_VIEW), que al indicar
            // adicionalmente en el objeto Uri que se trata de una url de página web, lo que
            // estamos haciendo es indicar al sistema Android que busque una actitividad entre
            // las apps que hay en el dispositivo que sea capaz de llamar y visualizar una pag. web.

            Intent intent = new Intent(Intent.ACTION_VIEW,paginaWeb);
            // Verificamos si el itent puede ser despachado por alguna actividad
            if (intent.resolveActivity(getPackageManager()) != null){
                // Enviamos intent al systema Android para que arranque la activity(ies) que corresponda
                startActivity(intent);
            } else {
                Toast.makeText(this,"No hay ninguna actividad disponible",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"Formato URL incorrecto. Debe escribire http://nombre...",Toast.LENGTH_LONG).show();
        }
    }
}
