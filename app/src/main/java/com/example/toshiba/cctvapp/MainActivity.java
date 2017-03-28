package com.example.toshiba.cctvapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {
    private Button buttonlogin;
    EditText user;
    EditText password;
    RadioButton userRG;
    RadioButton adminRG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonlogin = (Button) findViewById(R.id.login_button);
        user = (EditText) findViewById(R.id.user_id);
        password = (EditText) findViewById(R.id.pass_id);
        userRG = (RadioButton) findViewById(R.id.radio_user_login);
        adminRG = (RadioButton) findViewById(R.id.radio_admin_login);


    }
    public void signup(View v)
    {
        Intent i=new Intent(MainActivity.this,signup.class );
        startActivity(i);
        finish();

    }

    public void Login(View v) {

        String struser = user.getText().toString();
        String strpassword = password.getText().toString();


        if (struser.equals(""))

        {
            Toast.makeText(MainActivity.this, "Getusername", Toast.LENGTH_SHORT).show();
            return;
        }
        if (strpassword.equals(""))

        {
            Toast.makeText(MainActivity.this, "GetPassword", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( ! userRG.isChecked() && ! adminRG.isChecked() )

        {
            Toast.makeText(MainActivity.this, "select user type", Toast.LENGTH_SHORT).show();
            return;
        }

         String type = "";

        if(userRG.isChecked())
        {
            type = "user";
        }

        if(adminRG.isChecked())
        {
            type = "admin";
        }

        JSONObject json_data = new JSONObject();
        try {
            json_data.put("name", struser);
            json_data.put("password", strpassword);
            json_data.put("type",type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String finalType = type;
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.0.38/postData.php",
                json_data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("r").equals("done"))
                    {
                        if(finalType.equals("Admin"))
                        {

                        }
                        Toast.makeText(MainActivity.this, " registered successfully", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(30000, 2, 2));

        AppController ap = new AppController(MainActivity.this);
        ap.addToRequestQueue(jobjreq);
    }


}






