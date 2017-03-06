package com.example.android.expensemanager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class DayActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mainLayout;
    private PieChart mChart;
    private double[] yData = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String[] xData = {"health", "car", "house", "entertainment", "gifts", "clothing", "shopping", "pets", "eating out"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        DataBaseHelper ob = new DataBaseHelper(this);
        final Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Cursor c = ob.getRequiredData("day", day);
        if (c.getCount() == 0) {
            Toast.makeText(this, "Database Empty", Toast.LENGTH_SHORT).show();
            //   showAll("Error","No Data");
        } else
            Toast.makeText(this, "" + c.getCount(), Toast.LENGTH_SHORT).show();


        StringBuffer buffer = new StringBuffer();
        String s;
        while (c.moveToNext()) {

            s = c.getString(5);
            if (s.equals("health"))
                yData[0] += Double.parseDouble(c.getString(7));
            else if (s.equals("car"))
                yData[1] += Double.parseDouble(c.getString(7));
            else if (s.equals("house"))
                yData[2] += Double.parseDouble(c.getString(7));
            else if (s.equals("entertainment"))
                yData[3] += Double.parseDouble(c.getString(7));
            else if (s.equals("gifts"))
                yData[4] += Double.parseDouble(c.getString(7));
            else if (s.equals("clothing"))
                yData[5] += Double.parseDouble(c.getString(7));
            else if (s.equals("shopping"))
                yData[6] += Double.parseDouble(c.getString(7));
            else if (s.equals("pets"))
                yData[7] += Double.parseDouble(c.getString(7));
            else if (s.equals("food"))
                yData[8] += Double.parseDouble(c.getString(7));
        }
        ImageButton b = (ImageButton) findViewById(R.id.button1);
        b.setOnClickListener(DayActivity.this);
        ImageButton b2 = (ImageButton) findViewById(R.id.button2);
        b2.setOnClickListener(DayActivity.this);


        //piechart code
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mChart = new PieChart(this);
        // add pie chart to main layout
        mainLayout.addView(mChart, 1000, 1000);
        mainLayout.setBackgroundColor(Color.parseColor("#55656C"));

        // configure pie chart
        mChart.setUsePercentValues(true);


        // enable hole and configure

        mChart.setDrawHoleEnabled(true);
        //mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(5);
        mChart.setTransparentCircleRadius(10);
        //omChart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(DayActivity.this,
                        xData[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        // add data
        addData();

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }


    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry((float) yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Market Share");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent i = new Intent(this, Calc.class);
                i.putExtra("messageTag", "plus");
                startActivity(i);
                finish();
                break;
            case R.id.button2:
                //        Toast.makeText(this,"No button clicked",Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(this, Calc.class);
                i1.putExtra("messageTag", "minus");
                startActivity(i1);
                finish();
                break;
            default:
                Toast.makeText(this, "No button clicked", Toast.LENGTH_SHORT).show();


        }
    }
}
