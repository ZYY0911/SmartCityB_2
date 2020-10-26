package com.example.smartcityb_2.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 17:14
 */
public class JzjcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private PieChart pieChart;
    private BarChart barChart;
    private LineChart lineChart;
    private LineChart lineChart1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jzjc_layout);
        initView();
        title.setText("集中监测");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setPieChart();
        setBarChart();
        setLineChart();
        setLineChart1();
    }

    private void setLineChart1() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            entries.add(new BarEntry(i, random.nextInt(4) + 1));
        }
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            integers.add(getColor());
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(getColor());
        dataSet.setLineWidth(6);
        dataSet.setCircleRadius(3);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleColors(integers);
        LineData data = new LineData(dataSet);
        List<String> strings = new ArrayList<>();
        strings.add("22日");
        strings.add("23日");
        strings.add("24日");
        strings.add("25日");
        strings.add("26日");
        lineChart1.setData(data);
        XAxis xAxis = lineChart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        lineChart1.getAxisRight().setEnabled(false);
        lineChart1.getDescription().setEnabled(false);
        lineChart1.getLegend().setEnabled(false);
    }

    private void setLineChart() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            entries.add(new BarEntry(i, random.nextInt(1000)));
        }
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            integers.add(getColor());
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(getColor());
        dataSet.setLineWidth(6);
        dataSet.setCircleRadius(3);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleColors(integers);
        LineData data = new LineData(dataSet);
        List<String> strings = new ArrayList<>();
        strings.add("22日");
        strings.add("23日");
        strings.add("24日");
        strings.add("25日");
        strings.add("26日");
        lineChart.setData(data);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
    }

    private void setBarChart() {
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            barEntries.add(new BarEntry(i, random.nextInt(100)));
        }
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < barEntries.size(); i++) {
            integers.add(getColor());
        }
        List<String> strings = new ArrayList<>();
        strings.add("60~70");
        strings.add("70~80");
        strings.add("80~90");
        strings.add("90+");
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(integers);
        dataSet.setBarBorderWidth(0.3f);
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
    }

    private void setPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(70.5f, "已入住"));
        pieEntries.add(new PieEntry(29.5f, "未入住"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(new int[]{getColor(), getColor()});
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueFormatter(new PercentFormatter());
        PieData data = new PieData(pieDataSet);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        Legend legend = pieChart.getLegend();
        pieChart.setDrawHoleEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setUsePercentValues(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }


    Random random = new Random();

    private int getColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        pieChart = findViewById(R.id.pie_chart);
        barChart = findViewById(R.id.bar_chart);
        lineChart = findViewById(R.id.line_chart);
        lineChart1 = findViewById(R.id.line_chart1);
    }
}
