package com.example.toshiba.cctvapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity
{
    private EditText username;
    private EditText password;
    private EditText Emailid;
    private EditText Mobileno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.user_name_id);
        password = (EditText) findViewById(R.id.pass_id);
        Emailid = (EditText) findViewById(R.id.email_address_id);
        Mobileno = (EditText) findViewById(R.id.mobile_no_id);
    }
    public void Submit(View v)
    {
        String Name = username.getText().toString();
        String Password = password.getText().toString();
        String Email = Emailid.getText().toString();
        String Mobile = Mobileno.getText().toString();

        if (Name.equals("")) {
            Toast toast = new Toast(signup.this);

            toast.makeText(signup.this, "enter username", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", Name);
            jsonObject.put("password", Password);
            jsonObject.put("emailid", Email);
            jsonObject.put("mobile", Mobile);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.0.12/Signup.php", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                System.out.println(response);

                try
                { if(response.getString("key").equals("0"))
                {
                    Toast.makeText(signup.this,"Email already exist",Toast.LENGTH_SHORT).show();

                }
                else if(response.getString("key").equals("1")) {
                    Toast.makeText(signup.this ,"done" , Toast.LENGTH_SHORT).show();

                }

                else {
                    Toast.makeText(signup.this ,"error" , Toast.LENGTH_SHORT).show();

                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                System.out.println(error);

            }

});
        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000,2,2));
        AppController app=new AppController(signup.this);
        app.addToRequestQueue(jobjreq);

    }


    }

