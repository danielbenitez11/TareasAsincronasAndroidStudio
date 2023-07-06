package iesmm.pmdm.pmdm_t4_05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progress;
    private final int DELAY = 1*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Instanciación de componentes visuales para su control
        Button start = (Button) this.findViewById(R.id.button);

        // 2. Vinculamos el control del escuchador de eventos con el componente
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                //showProgress();
                new  TareaProgressBar().execute();
                break;
        }
    }


    /* Muestra un cuadro de diálogo con barra de progreso */
    private void showProgress() {
        // OTRA OPCIÓN: Método estático de inicio: ProgressDialog.show(this, titulo, mensaje);
        progress = new ProgressDialog(this);
        progress.setTitle("Descarga simulada"); // Titulo
        progress.setMessage("Cargando"); // Contenido

        // Propiedades de configuración
        progress.setMax(100); // Valor máximo de la barra de progreso
        progress.setCancelable(true); // Permitir que se cancele por el usuario

        // Tipo de barra de progreso: ProgressDialog.STYLE_SPINNER / STYLE_HORIZONTAL
        //progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // Mostrar cuadro de diálogo
        progress.show();

        // Incrementar valor del progreso en n. unidades


            //try {

            progress.incrementProgressBy(0); // Aumenta en 1 unidad

            //} catch (InterruptedException e) {
            //e.printStackTrace();
            //}


        // Obtener valor de la barra de progreso
        progress.getProgress();

        // Obtener valor máximo de la barra de progreso
        // progress.getMax();

        // Finalización del cuadro de diálogo
        // progress.cancel();


    }


    private class TareaProgressBar extends AsyncTask<Void, Integer, Void> {
        private final  String TAG="PMDM";
        private final int MAX=100;
        private final int INCREMENTOCARGA=10;

        @Override
        protected void onPostExecute(Void unused) {
            Log.i(TAG, "Se finaliza la tarea asincrona");

        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "Se inicia la tarea asincrona");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Se actualiza onProgressUpdate");
            showProgress();
            progress.incrementProgressBy(values[0]);
            OcultarBarra(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(TAG, "Se inicia el doInBackground");

            for (int i=0 ;i<=MAX; i=i+INCREMENTOCARGA) {


                publishProgress(i);

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }


        public void OcultarBarra(int i){
            if (i>=40){
                progress.hide();
                 if(i>=90){
                    progress.show();
                }
            }
        }
    }


}