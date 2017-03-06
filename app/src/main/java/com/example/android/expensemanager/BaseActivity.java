package com.example.android.expensemanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BaseActivity extends ActionBarActivity {
    Button cal;
    int month_x, year_x, week_x, day_x;
    static final int DIALOG_ID = 0;

    SharedPreferences sharedpref ;
    SharedPreferences.Editor editor;
    @Override
    protected void onPostCreate(Bundle savedState) {
        super.onPostCreate(savedState);
        sharedpref= getSharedPreferences("state", MODE_PRIVATE);
        editor=sharedpref.edit();

        findViewById(R.id.day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("selected","day");
                editor.apply();

                startActivity(new Intent(BaseActivity.this, DayActivity.class));
                finish();

            }
        });
        findViewById(R.id.month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("selected","month");
                   editor.apply();
                startActivity(new Intent(BaseActivity.this, MainActivity.class));
                finish();

            }
        });

        findViewById(R.id.week).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("selected","week");
                editor.apply();
                startActivity(new Intent(BaseActivity.this, WeekActivity.class));
                finish();
            }
        });
        findViewById(R.id.year).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("selected","year");
                editor.apply();
                startActivity(new Intent(BaseActivity.this, YearActivity.class));
                finish();
            }
        });
        cal = (Button) findViewById(R.id.calen);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        week_x = cal.get(Calendar.WEEK_OF_YEAR);
        showDialogOnButtonClick();

    }


    public void showDialogOnButtonClick() {

        cal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }

        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Calendar c = new GregorianCalendar();
            c.set(Calendar.YEAR, year_x);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, day_x);
            week_x = c.get(Calendar.WEEK_OF_YEAR);
            editor.putString("date", day_x + "/" + month_x + "/" + year_x + "/" + week_x);
            editor.apply();

        }
    };
}
