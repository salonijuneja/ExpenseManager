package com.example.android.expensemanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class Income extends AppCompatActivity {
String s,cat;
    int day,month,year,week;
    double res;
    TextView res_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_income);
        Intent i=getIntent();
        s=i.getExtras().getString("messageTag");
        StringTokenizer ss=new StringTokenizer(s,"#");
        day=Integer.parseInt(ss.nextToken());
        month=Integer.parseInt(ss.nextToken());
        year=Integer.parseInt(ss.nextToken());
        week=Integer.parseInt(ss.nextToken());
        res=Double.parseDouble(ss.nextToken());
        res_tv=(TextView)findViewById(R.id.income);
        res_tv.setText("" + res);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_income, menu);
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
            case R.id.sal_b:
            cat="salary";
                break;
            case R.id.dep_b:
                cat="deposit";
                break;
            case R.id.save_b:
                cat="savings";
                break;
        }
        DataBaseHelper obj=new DataBaseHelper(this);
        boolean x=obj.insertData(day,week,month,year,cat,res,0.0);
        if(x)
            Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show();
        Cursor c=obj.getAllData();

        if(c.getCount()==0) {
            Toast.makeText(this, "Database Empty", Toast.LENGTH_SHORT).show();
         //   showAll("Error","No Data");
        }
        else
            Toast.makeText(this, "" + c.getCount(), Toast.LENGTH_SHORT).show();


            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("Id :" + c.getString(0) + "\n");
                buffer.append("day :" + c.getString(1) + "\n");
                buffer.append("week :" + c.getString(2) + "\n");
                buffer.append("month :" + c.getString(3) + "\n");
                buffer.append("year :" + c.getString(4) + "\n");
                buffer.append("category : "+c.getString(5)+"\n");
                buffer.append("income :" + c.getString(6) + "\n");
                buffer.append("expense :" + c.getString(7) + "\n\n");

            }
        showAll("Data", buffer.toString());
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

        }


   public void showAll(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
   AlertDialog alert = builder.create();
   alert.show();
    }
    }


