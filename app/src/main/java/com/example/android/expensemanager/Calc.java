package com.example.android.expensemanager;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calc extends AppCompatActivity {
    Button plus,minus,multiply,divide,category;
    TextView input_et;
    TextView result_tv;
    String s,cat_string;
    double a=0,res=0;
    boolean set=false,isdeci=false,op=false;
    char operation='0';
    //Calender objects here
    Button btn;
    int year_x,month_x,day_x,week_x;
    static final int DIALOG_ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Intent i1=getIntent();
        s=i1.getExtras().getString("messageTag");
        plus=(Button)findViewById(R.id.add);
        minus=(Button)findViewById(R.id.subtract);
        multiply=(Button)findViewById(R.id.multiply);
        divide=(Button)findViewById(R.id.divide);
        input_et=(TextView)findViewById(R.id.input_et);
        result_tv=(TextView)findViewById(R.id.result_tv);
        category=(Button)findViewById(R.id.cat);

        //For Calender
        final Calendar cal = Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH) + 1;
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        week_x=cal.get(Calendar.WEEK_OF_YEAR);
        showDialogOnButtonClick();

    }
    //Calender code....>

    public void showDialogOnButtonClick(){
        btn = (Button)findViewById(R.id.cal);
        btn.setOnClickListener(
                new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       showDialog(DIALOG_ID);
                                   }
                               }

        );
    }

    @Override
     protected Dialog onCreateDialog(int id){
        if(id==DIALOG_ID){
            return new DatePickerDialog(this,dpickerListner,year_x,month_x,day_x);

        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x=year;
            month_x=monthOfYear + 1;
            day_x=dayOfMonth;
            Calendar c =new GregorianCalendar();
            c.set(Calendar.YEAR,year_x);
            c.set(Calendar.MONTH,monthOfYear);
            c.set(Calendar.DAY_OF_MONTH,day_x);
            week_x = c.get(Calendar.WEEK_OF_YEAR);
            Toast.makeText(Calc.this,day_x + "/" + month_x + "/" + year_x + "/" + week_x,Toast.LENGTH_LONG).show();
        }
    };

    public void value1(View view){
        String s= String.valueOf(input_et.getText());
       s= s+"1";
        input_et.setText(s);
    }
    public void value2(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"2";
        input_et.setText(s);
    }
    public void value3(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"3";
        input_et.setText(s);
    }
    public void value4(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"4";
        input_et.setText(s);
    }
    public void value5(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"5";
        input_et.setText(s);
    }
    public void value6(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"6";
        input_et.setText(s);
    }
    public void value7(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"7";
        input_et.setText(s);
    }
    public void value8(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"8";
        input_et.setText(s);
    }
    public void value9(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"9";
        input_et.setText(s);
    }
    public void value0(View view){
        String s= String.valueOf(input_et.getText());
        s= s+"0";
        input_et.setText(s);
    }
    public void deci(View view){
        String s= String.valueOf(input_et.getText());
       if(!isdeci) {
           s = s + ".";
           isdeci=true;
       }
        input_et.setText(s);

    }
    public void add(View view) {

    if(!input_et.getText().toString().isEmpty())
    {
            a = Double.parseDouble(input_et.getText().toString());
            isdeci = false;
            if (!set) {

                res = a;

                set = true;
            } else {
                res = res + a;
            }
            result_tv.setText("" + res);
            operation = '+';
            input_et.setText("");
    }
    }
    public void subtract(View view) {

        if(!input_et.getText().toString().isEmpty())
        {
        a = Double.parseDouble(input_et.getText().toString());
        isdeci = false;
        if (!set) {

            res = a;

            set = true;
        } else {
            res = res - a;
        }
        result_tv.setText("" + res);
        operation = '-';
        input_et.setText("");
    }
    }
    public void multiply(View view) {


        if(!input_et.getText().toString().isEmpty()) {
            a = Double.parseDouble(input_et.getText().toString());
            isdeci = false;
            if (!set) {
                result_tv.setText("" + a);
                res = a;

                set = true;
            } else {
                res = res * a;
            }
            operation = '*';
            result_tv.setText("" + res);
            input_et.setText("");
        }
    }  public void divide(View view) {

        if(!input_et.getText().toString().isEmpty()) {
            a = Double.parseDouble(input_et.getText().toString());
            isdeci = false;
            if (!set) {

                res = a;
                set = true;
            } else {
                res = res / a;
            }
            operation = '/';
            result_tv.setText("" + res);
            input_et.setText("");
        }
    }

public void delete(View view){
    String s=input_et.getText().toString();
    if(s.length()!=0) {
        if (s.charAt(s.length() - 1) == '.')
            isdeci = false;
        s = s.substring(0, s.length() - 1);
    }
    input_et.setText(s);

}
    public void clear(View view){
        res=0;
        a=0;
        set=false;
    operation='0';
        isdeci=false;
        result_tv.setHint("0");
        result_tv.setText("");
        input_et.setText("");
        input_et.setHint("0");
    }
    public void display(View view) {
        if(!input_et.getText().toString().isEmpty()) {
            a = Double.parseDouble(input_et.getText().toString());
            isdeci = false;
            if (!set)
                res = a;
            switch (operation) {
                case '+':
                    res += a;
                    break;
                case '-':
                    res -= a;
                    break;
                case '*':
                    res *= a;
                    break;
                case '/':
                    res /= a;
                    break;
                default:
                    break;
            }
            a = 0;

            result_tv.setText("" + res);
            input_et.setHint("0");
        }
    }
    public void category(View v) throws InterruptedException {
   if(res!=0)
   {
       if (s.equals("plus"))
       {
           Intent i = new Intent(this, Income.class);
           i.putExtra("messageTag", ""+day_x+"#"+month_x+"#"+year_x+"#"+week_x+"#"+res);
           startActivity(i);
           finish();
       }
       else if (s.equals("minus"))
       {
           Intent i1 = new Intent(this, Expense.class);
           i1.putExtra("messageTag", ""+day_x+"#"+month_x+"#"+year_x+"#"+week_x+"#"+res);
           startActivity(i1);
           finish();
       }
   }
        else {
            ValueAnimator colorAnim = ObjectAnimator.ofInt(result_tv, "backgroundColor", 0, -65536);
            colorAnim.setDuration(500);
            colorAnim.setEvaluator(new ArgbEvaluator());
            colorAnim.reverse();
   }
    }
}

