package com.example.theheroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Heroes extends AppCompatActivity {

    String token = "https://www.superheroapi.com/api.php/3915057375233725/search/Batman";
    String frase;
    TextView txtResultados;
    RequestQueue queue = null;
    LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);

        txtResultados = (TextView) findViewById(R.id.txtResultados);
        contenedor = (LinearLayout) findViewById(R.id.heroes);
        queue = Volley.newRequestQueue(this);
        Bundle bundle = getIntent().getExtras();
        frase = bundle.getString("texto");

        this.buscarHeroe();
    }

    public void buscarHeroe() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, token,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        presentarHeroe(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void presentarHeroe(JSONObject heroes) {
        JSONArray results;
        JSONObject superHeroe;
        try {

            results = heroes.getJSONArray("results");
            txtResultados.setText("Resultado: " + results.length());
            for (int i = 0; i < results.length(); i++) {
                superHeroe = (JSONObject) results.get(i);
                System.out.println();

                final JSONObject cualidades = (JSONObject) superHeroe.get("powerstats");
                final JSONObject nombre = (JSONObject) superHeroe.get("biography");

                TextView variable = new TextView(this);
                variable.setText(superHeroe.getString("name"));
                variable.setTextSize(50);
                final JSONObject finalPersonaje = superHeroe;
                variable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            Intent intent = new Intent(Heroes.this , GraficoHeroes.class );
                            intent.putExtra("power",(cualidades.getString("power")));
                            intent.putExtra("intelligence",(cualidades.getString("intelligence")));
                            intent.putExtra("strength",(cualidades.getString("strength")));
                            intent.putExtra("speed",(cualidades.getString("speed")));
                            intent.putExtra("durability",(cualidades.getString("durability")));
                            intent.putExtra("combat",(cualidades.getString("combat")));
                            intent.putExtra("completo",nombre.getString("full-name"));
                            intent.putExtra("nombre", finalPersonaje.getString("name"));
                            System.out.println(cualidades.getString("power"));
                            System.out.println(cualidades.getString("intelligence"));
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                contenedor.addView(variable);
            }
            } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}

