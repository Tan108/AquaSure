package com.sinros.myexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private Button cart;
    private ImageView plus,minus,image,cart1;
    private TextView qty,price,pname,total1,tax1,grand1;
    private static int count=1,total=0,amount=1;
    String HttpUrl=Config.IP+"/Bottle_App/cart.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //checkout=(Button)findViewById(R.id.chechout);
        cart=(Button)findViewById(R.id.cart);
        cart1=(ImageView)findViewById(R.id.cart1);
        plus=(ImageView) findViewById(R.id.plus);
        minus=(ImageView) findViewById(R.id.minus);
        image=(ImageView) findViewById(R.id.image);
        qty=(TextView) findViewById(R.id.productQuantity);
        price=(TextView) findViewById(R.id.price);
        total1=(TextView)findViewById(R.id.add);
        tax1=(TextView)findViewById(R.id.tax);
        grand1=(TextView)findViewById(R.id.grand);
        pname=(TextView) findViewById(R.id.productname);

        String img =  getIntent().getStringExtra("img");
        Glide.with(getApplicationContext())
                .load(img)
                .into(image);

        pname.setText(getIntent().getStringExtra("name"));
        amount=Integer.parseInt(getIntent().getStringExtra("price"));
        price.setText(getIntent().getStringExtra("price"));
        qty.setText(String.valueOf(count));
        total=amount;
        total1.setText(String.valueOf(total));
        tax1.setText(String.valueOf(amount*.05));
        grand1.setText(String.valueOf (amount+(amount*.05)));
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=count+1;
                total=amount*count;
                qty.setText(String.valueOf(count));
                total1.setText(String.valueOf(total));
                tax1.setText(String.valueOf(total*.05));
                grand1.setText(String.valueOf (total+(total*.05)));
                //total=amount*qty;
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    count = count - 1;
                    total=amount*count;
                    qty.setText(String.valueOf(count));
                    total1.setText(String.valueOf(total));
                    tax1.setText(String.valueOf(total*.05));
                    grand1.setText(String.valueOf (total+(total*.05)));
                }
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendcart();
            }
        });

        cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open cart activity
                startActivity(new Intent(DetailActivity.this,CartActivity.class));
            }
        });
    }

    public void sendcart(){

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
                            Toast.makeText(DetailActivity.this, msg, Toast.LENGTH_LONG).show();
                            // startActivity(new Intent(LoginActivity.this,MainActivity.class));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Showing response message coming from server.
                       // Toast.makeText(DetailActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Showing error message if something goes wrong.
                        Toast.makeText(DetailActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                params.put("Content-Type", "application/json");
                // Adding All values to Params.
                params.put("pid", getIntent().getStringExtra("id"));
                params.put("uid",shared.getString("uid",""));
                params.put("total", String.valueOf (total+(total*.05)));
                params.put("quantity",String.valueOf(count));
                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(DetailActivity.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }
}
