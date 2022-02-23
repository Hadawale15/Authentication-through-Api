package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUPActivity extends AppCompatActivity {

    EditText e1,e2;
    Button signUp;



    String Url="http://authenticationapp1.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);



        e1=findViewById(R.id.signup_id1);
        e2=findViewById(R.id.signup_id2);
        signUp=findViewById(R.id.signup_button_id);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(SignUPActivity.this);
                StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("inserted successfully"))
                        {
                            Toast.makeText(SignUPActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(SignUPActivity.this,HomeActivity.class);
                            startActivity(intent1);
                            finish();
                        }

                        else
                        {
                            Toast.makeText(SignUPActivity.this, "Account details not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUPActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> params=new HashMap<String,String>();
                        params.put("email",e1.getText().toString());
                        params.put("password",e2.getText().toString());

                        return params;
                    }
                };

                requestQueue.add(request);
            }
        });








}
}