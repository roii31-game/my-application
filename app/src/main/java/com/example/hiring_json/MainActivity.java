package com.example.hiring_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button_JSON;
    ListView _dynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_JSON = findViewById(R.id.button_JSON);
        _dynamic = findViewById(R.id._dynamic);

       final ListDataService listDataService = new ListDataService(MainActivity.this);
        // Click Listeners for each button

        button_JSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //This didnt return anything.
                listDataService.getListID(button_JSON.getText().toString(), new ListDataService.listReportModels() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<ListReportModel> listReportModels) {
//                        Toast.makeText(MainActivity.this, "Returned an ID of " + listID, Toast.LENGTH_SHORT).show();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, listReportModels);
                        _dynamic.setAdapter(arrayAdapter);
                    }
                });


            }
        });


    }
}