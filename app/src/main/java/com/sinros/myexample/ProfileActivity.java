package com.sinros.myexample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView cart,name,email;
    private Button logout;
    AlertDialog alertdialog;
    SharedPreferences shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        cart=findViewById(R.id.cart2);
        logout=findViewById(R.id.btn_logout);
        name=findViewById(R.id.user_profile_name);
        email=findViewById(R.id.user_profile_email);
        shared= getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,CartActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialog = new AlertDialog.Builder(ProfileActivity.this).create();

                alertdialog.setTitle("Logout");
                alertdialog.setMessage("Are you sure ! logout ?");
                alertdialog.setCancelable(false);
                alertdialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alertdialog.dismiss();

                    }
                });

                alertdialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = shared.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                alertdialog.show();

            }
        });

        String name1 = shared.getString("name", "");
        String email1 = shared.getString("email", "");

        email.setText(email1);
        name.setText(name1);

    }
}
