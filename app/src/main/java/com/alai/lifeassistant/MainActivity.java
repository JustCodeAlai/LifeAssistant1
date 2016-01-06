package com.alai.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnCal;
    private Button mBtnNote;
    private Button mBtnContacts;
    private Button mBtnWeather;
    private Button mBtnExpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResource();
    }

    private void initResource() {
        mBtnCal = (Button) findViewById(R.id.btn_calculator);
        mBtnContacts = (Button) findViewById(R.id.btn_contacts);
        mBtnExpress = (Button) findViewById(R.id.btn_express);
        mBtnNote = (Button) findViewById(R.id.btn_note);
        mBtnWeather = (Button)findViewById(R.id.btn_weather);

        mBtnWeather.setOnClickListener(this);
        mBtnNote.setOnClickListener(this);
        mBtnContacts.setOnClickListener(this);
        mBtnExpress.setOnClickListener(this);
        mBtnNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_note:
                intent.setClass(MainActivity.this, com.alai.lifeassistant.ui.NoteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_contacts:
                intent.setClass(MainActivity.this, com.alai.lifeassistant.ui.ContactActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_calculator:
                intent.setClass(MainActivity.this, com.alai.lifeassistant.ui.CalculatorActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_express:
                intent.setClass(MainActivity.this, com.alai.lifeassistant.ui.ExpressActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_weather:
                intent.setClass(MainActivity.this, com.alai.lifeassistant.ui.WeatherActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/
}