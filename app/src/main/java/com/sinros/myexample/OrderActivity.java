package com.sinros.myexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    private static final String URL_PRODUCTS = Config.IP+"/Bottle_App/showorder.php";
    String HttpUrl=Config.IP+"/Bottle_App/order.php";

    //a list to store all the properties
    List<Chapter> chapterList;

    Button order;
    private TextView nocart;



    //the recyclerview
    RecyclerView recyclerView;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        order=findViewById(R.id.order);
        recyclerView = findViewById(R.id.recyclerview_cart);
        progressBar=findViewById(R.id.prog);
        nocart=findViewById(R.id.nocart);
        //recyclerView.setHasFixedSize(true);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendorder();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initializing the product_list
        chapterList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }

    private void loadProducts() {

        final SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.VISIBLE);
                        try {

                            Log.i("tagconvertstr", "["+response+"]");
                            //converting the string to json array object
                            // JSONArray array = new JSONArray(response);

                            JSONObject jsnobject = new JSONObject(response);
                            JSONArray array = jsnobject.getJSONArray("ki");

                            if(array.length()>0) {

                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject product = array.getJSONObject(i);

                                    //adding the product to product list
                                    chapterList.add(new Chapter(
                                            product.getString("id"),
                                            product.getString("name"),
                                            product.getString("image"),
                                            product.getString("quantity"),
                                            product.getString("total")

                                    ));
                                }
                            }else {
                                nocart.setVisibility(View.VISIBLE);
                                // Toast.makeText(CartActivity.this, "No item in Cart", Toast.LENGTH_LONG).show();
                            }



                            progressBar.setVisibility(View.GONE);

                            //creating adapter object and setting it to recyclerview
                            ChapterAdapter adapter = new ChapterAdapter(OrderActivity.this,chapterList );
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){


            @Override
            protected Map<String, String> getParams() {
                Map<String,String> stringMap = new HashMap<String, String>();
                stringMap.put("uid",shared.getString("uid",""));
                return stringMap;
            }};

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void sendorder(){

        final SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);


        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            JSONObject jsonObject = new JSONObject(ServerResponse);
                            String msg = jsonObject.getString("message");
                            // String success = jsonObject.getString("success");
                            // String uid=jsonObject.getString("uid");
                            Toast.makeText(OrderActivity.this, msg, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(OrderActivity.this,ConfirmActivity.class));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Showing response message coming from server.
                        // Toast.makeText(CartActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Showing error message if something goes wrong.
                        Toast.makeText(OrderActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                params.put("Content-Type", "application/json");
                // Adding All values to Params.
                params.put("uid",shared.getString("uid",""));

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(OrderActivity.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }
}
