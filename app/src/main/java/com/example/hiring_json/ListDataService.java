package com.example.hiring_json;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListDataService {

    public static final String QUERY_FOR_LIST_ID = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    public static final String QUERY_FOR_OUR_OUTPUT_ID = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    String listID;

    Context context;

    public ListDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse (String listID);
    }

    public void getListID(String ListName, VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_LIST_ID;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                listID = "";

                try {
                    JSONObject listInfo = response.getJSONObject(0);
                    listID = listInfo.getString("listId");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //This worked but it didnt return the id number to MainActivity
                Toast.makeText(context, "listId = " + listID, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(listID);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something Wrong");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);


    }

    public void getListID(String ListName){
        List<ListReportModel> report = new ArrayList<>();

        String url = QUERY_FOR_OUR_OUTPUT_ID + listID;
        // get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

            // get the property

        // get each item in the array and assign it to the new getListID method
        MySingleton.getInstance(context).addToRequestQueue(request);
    }



}
