package com.mk.searchtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mk.searchtest.R.array;
import static com.mk.searchtest.R.id;
import static com.mk.searchtest.R.layout;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    MaterialSearchView materialSearchView ;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle ("Search");
        toolbar.setTitleTextColor(Color.parseColor ("#FFFFFF"));


        listView =
                (ListView) findViewById(id.listViewCountries);
        final ArrayList<String> strings =
                new ArrayList<>();
        strings.addAll(
                Arrays.asList(
                        getResources().getStringArray(
                                array.countries_array)));
        arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                strings);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = listView.getItemAtPosition (position).toString();
                Toast.makeText (MainActivity.this, "Hello In " + s, Toast.LENGTH_LONG).show ();
            }
        });



        materialSearchView = (MaterialSearchView)findViewById (R.id.search_View);
        materialSearchView.setOnSearchViewListener (new MaterialSearchView.SearchViewListener () {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                ListView listView =
                        (ListView) findViewById(id.listViewCountries);
                ArrayList<String> strings =
                        new ArrayList<>();
                strings.addAll(
                        Arrays.asList(
                                getResources().getStringArray(
                                        array.countries_array)));
                arrayAdapter = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        strings);
                listView.setAdapter(arrayAdapter);
            }
        });
        materialSearchView.setOnQueryTextListener (new MaterialSearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter ().filter (newText);
              /**  if(newText != null && !newText.isEmpty () ){
                    List<String> strings1 = new ArrayList<> ();
                    for(String string:strings){
                        if (string.contains (newText))
                            strings1.add (string);
                    }
                    arrayAdapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            strings1);
                    listView.setAdapter(arrayAdapter);
                }else {
                    arrayAdapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            strings);
                    listView.setAdapter(arrayAdapter);
                }*/
                return false;
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem (id.app_bar_search);
        materialSearchView.setMenuItem (menuItem);


        return true;
    }



}
