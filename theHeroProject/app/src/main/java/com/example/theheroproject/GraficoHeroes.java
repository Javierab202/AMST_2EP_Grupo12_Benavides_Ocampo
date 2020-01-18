package com.example.theheroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
//import android.widget.BarChart;
import com.android.volley.RequestQueue;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;

public class GraficoHeroes extends AppCompatActivity {

    public BarChart graficoBarras;
    private RequestQueue request = null;


    private Map<String, TextView> temperaturasTVs;
    private Map<String, TextView> fechasTVs;
    //private Registros contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_heroes);

        setTitle("Grafico de Heroes");

        this.iniciarGrafico();
    }

    public void iniciarGrafico() {
        graficoBarras = findViewById(R.id.barChart);
        graficoBarras.getDescription().setEnabled(false);
        graficoBarras.setMaxVisibleValueCount(60);
        graficoBarras.setPinchZoom(false);
        graficoBarras.setDrawBarShadow(false);
        graficoBarras.setDrawGridBackground(false);
        XAxis xAxis = graficoBarras.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        graficoBarras.getAxisLeft().setDrawGridLines(false);
        graficoBarras.animateY(1500);
        graficoBarras.getLegend().setEnabled(false);
    }

    private void actualizarGrafico(){


    }

    private void llenarGrafico(ArrayList<BarEntry> dato_temp) {
        BarDataSet heroesDataSet;
        if (graficoBarras.getData() != null && graficoBarras.getData().getDataSetCount() > 0) {
            heroesDataSet = (BarDataSet) graficoBarras.getData().getDataSetByIndex(0);
            heroesDataSet.setValues(dato_temp);
            graficoBarras.getData().notifyDataChanged();
            graficoBarras.notifyDataSetChanged();
        } else {
            heroesDataSet = new BarDataSet(dato_temp, "Data Set");
            heroesDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
            heroesDataSet.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(heroesDataSet);
            BarData data = new BarData(dataSets);
            graficoBarras.setData(data);
            graficoBarras.setFitBars(true);
        }
        graficoBarras.invalidate();
    }
}
