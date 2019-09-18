package com.sinros.myexample;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    private Button confirm;
    private EditText name,number,card;
   // private EditText contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

       // contact=findViewById(R.id.email);


        name=findViewById(R.id.nameoncard);
        number=findViewById(R.id.email);
        card=findViewById(R.id.creditcard);

        confirm=findViewById(R.id.completeorder);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().trim().isEmpty()) {
                    name.setError("Empty Fields not Allowed");
                } else if (card.getText().toString().trim().isEmpty() || card.getText().toString().trim().length() != 16) {

                    card.setError("Card Number should be of 16 digits");
                }else if (number.getText().toString().trim().isEmpty() || number.getText().toString().trim().length() != 10) {

                    number.setError("Card Number should be of 10 digits");
                }else {


                    Intent intent = new Intent(PaymentActivity.this, ConfirmActivity.class);
                    startActivity(intent);

                }



            }
        });
    }



}
