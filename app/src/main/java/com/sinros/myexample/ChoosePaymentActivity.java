package com.sinros.myexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChoosePaymentActivity extends AppCompatActivity {

    String paymentMethod;
    RadioGroup paymentMethodsGroup;
    Intent intent;
    Button makePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);

        paymentMethodsGroup=findViewById(R.id.paymentMethodsGroup);
        makePayment=findViewById(R.id.makePayment);
        makePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 moveNext();
            }
        });


    }

    private void moveNext() {
        switch (paymentMethodsGroup.getCheckedRadioButtonId()) {

            case R.id.cod:
                paymentMethod = "cod";

                intent = new Intent(ChoosePaymentActivity.this, ConfirmActivity. class );
                startActivity( intent );

                break;
            case R.id. razorPay :
                paymentMethod = "razorPay" ;
                intent = new Intent(ChoosePaymentActivity.this, PaymentActivity. class );
                startActivity( intent );
                break ;
            default:
                paymentMethod = "";

                Toast.makeText(ChoosePaymentActivity.this,"Select your payment method to make payment",Toast.LENGTH_LONG);

                break;


        }

        Log.d("paymentMethod", paymentMethod);
    }

}
