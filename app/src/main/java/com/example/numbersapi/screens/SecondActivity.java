package com.example.numbersapi.screens;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.numbersapi.App;
import com.example.numbersapi.R;
import com.example.numbersapi.model.SearchQuery;

import org.json.JSONException;
import org.json.JSONObject;



public class SecondActivity extends AppCompatActivity {

    private String jsonUrl = "http://numbersapi.com/random/math?json";

    private SearchQuery searchQuery = new SearchQuery();

    Button buttonBack;


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.fact);

        buttonBack = findViewById(R.id.goBackButton);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toFirstPage(view);
            }
        });



        if (getIntent().hasExtra("oldFact")) { // Old fact
            Bundle arguments = getIntent().getExtras();
            String oldFact = arguments.getString("oldFact");
            textView.setText(oldFact);

        } else if (getIntent().hasExtra("newFact")) { //New Fact
            Bundle arguments = getIntent().getExtras();
            jsonUrl = "http://numbersapi.com/" + arguments.get("newFact").toString() + "/math?json";
            loadJSONFromUrl(jsonUrl);


        } else loadJSONFromUrl(jsonUrl); //Random


    }

    private void loadJSONFromUrl(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);

                            searchQuery.fact = String.valueOf(object.get("text"));
                            searchQuery.number = String.valueOf(object.get("number"));
                            App.getInstance().getSqDao().insert(searchQuery);

                            textView.setText(searchQuery.fact);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void toFirstPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}