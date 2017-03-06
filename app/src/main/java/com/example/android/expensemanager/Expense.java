package com.example.android.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class Expense extends AppCompatActivity {
    int day,month,year,week;
    double res;
    String s,cat;
    TextView res_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Intent i=getIntent();
        s=i.getExtras().getString("messageTag");
        StringTokenizer ss=new StringTokenizer(s,"#");
        day=Integer.parseInt(ss.nextToken());
        month=Integer.parseInt(ss.nextToken());
        year=Integer.parseInt(ss.nextToken());
        week=Integer.parseInt(ss.nextToken());
        res=Double.parseDouble(ss.nextToken());
        res_tv=(TextView)findViewById(R.id.expense);
        res_tv.setText(""+res);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense, menu);
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
    public void insert(View v)
    {
        switch(v.getId())
        {
            case R.id.health_b:
                cat="health";
                break;
            case R.id.house_b:
                cat="house";
                break;
            case R.id.car_b:
                cat="car";
                break;
            case R.id.food_b:
                cat="food";
                break;
            case R.id.gifts_b:
                cat="gifts";
                break;
            case R.id.clothing_b:
                cat="clothing";
                break;
            case R.id.enter_b:
                cat="entertainment";
                break;
            case R.id.shop_b:
                cat="shopping";
                break;
            case R.id.pet_b:
                cat="pets";
                break;
        }
        DataBaseHelper obj=new DataBaseHelper(this);
       boolean x=obj.insertData(day,week,month,year,cat,0.0,res);
        if(x)
            Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
