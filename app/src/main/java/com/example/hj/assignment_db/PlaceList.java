package com.example.hj.assignment_db;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class PlaceList extends ActionBarActivity {

    DBController controller = new DBController(this);
    ListView ls;
    TextView infotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        ls=(ListView) findViewById(R.id.placeslist);
        infotext=(TextView) findViewById(R.id.textView2);
        try {
            List<HashMap<String,String>> data = controller.getAllPlace();
            if(data.size() !=0) {
                //Srno, RMCode, Fileno, Loc, FileDesc, TAGNos
                SimpleAdapter adapter = new SimpleAdapter (

                        PlaceList.this, data, R.layout.raw,
                        new String[]{"id", "place", "country"}, new int[]{
                        R.id.txtplaceid, R.id.txtplace, R.id.txtcountry});

                ls.setAdapter(adapter);
                String length = String.valueOf(data.size());
                infotext.setText(length + " places");
            }
            else {
                infotext.setText("No data in database");
            }
        }
        catch(Exception ex) {
            infotext.setText(ex.getMessage().toString());
        }
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

