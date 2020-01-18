package com.example.theheroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText barra;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barra=(EditText) findViewById(R.id.editText);
    }

    public void buscarHeroe(View view){
        Intent intent = new Intent(this, Heroes.class);
        intent.putExtra("texto", barra.getText().toString());
        startActivity(intent);
    }
}
