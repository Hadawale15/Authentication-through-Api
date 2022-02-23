package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    EditText e1,e2;
    Button login;



    String Url="http://authenticationapp1.000webhostapp.com/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.idno1);
        e2=findViewById(R.id.idno2);
        login=findViewById(R.id.idno3);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Login succeeds"))
                        {
                            Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent1);
                            finish();
                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Account details not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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

    public void SignUPMethod(View view) {
        Intent intent=new Intent(MainActivity.this,SignUPActivity.class);
        startActivity(intent);
        finish();
    }
}
