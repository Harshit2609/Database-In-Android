package com.example.hj.assignment_db;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBController controller = new DBController(this);
    Button add, view, update, delete;
    EditText placeid, place, country;
    TextView infotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeid = (EditText) findViewById(R.id.edplaceid);
        place = (EditText) findViewById(R.id.edplace);
        country=(EditText) findViewById(R.id.edcountry);
        add=(Button) findViewById(R.id.btnadd);
        update = (Button) findViewById(R.id.btnupdate);
        delete = (Button) findViewById(R.id.btndelete);
        view = (Button) findViewById(R.id.btnview);
        infotext = (TextView) findViewById(R.id.txtresulttext);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(MainActivity.this, PlaceList.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    if(place.getText().toString().trim().equals("") || country.getText().toString().trim().equals(""))
                    {
                        infotext.setText("please insert place name and country..");
                    }

                    else
                    {
                        controller=new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("place", place.getText().toString());
                        cv.put("country", country.getText().toString());
                        db.insert("places", null, cv);
                        db.close();
                        infotext.setText("Place added successfully");
                    }
                }

                catch(Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }

            }});
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    if(place.getText().toString().trim().equals("") && country.getText().toString().trim().equals(""))
                    {
                        infotext.setText("Please insert values to update...");
                    }

                    else
                    {
                        controller=new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        ContentValues cv= new ContentValues();
                        cv.put("place", place.getText().toString());
                        cv.put("country", country.getText().toString());
                        db.update("places", cv, "id=" + placeid.getText().toString(), null);
                        Toast.makeText(MainActivity.this,"Updated successfully", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Updated successfully");
                    }
                }
                catch (Exception ex)
                {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (placeid.getText().toString().trim().equals("")) {
                        infotext.setText("Please insert place ID to delete..");
                    } else {
                        controller = new DBController(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();
                        db.delete("places", "id=" + placeid.getText().toString(), null);
                        Toast.makeText(MainActivity.this, "deleted successfully", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Deleted successfully");
                    }
                } catch (Exception ex)
                {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });

    }

    @Override
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
    }

}
