package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AuthenticationRequiredException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {
public EditText user,pass;
public Button login;
public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.login);
        clickEvent();

    }

    private void clickEvent() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=user.getText().toString();
                String password=pass.getText().toString();

                if (TextUtils.isEmpty(email)){
                    user.setError("Enter Email");
                }
                else if (TextUtils.isEmpty(password)){
                    pass.setError("Enter password");
                }
                else {
                    RequestQueue queue=Volley.newRequestQueue(login.this);
                    String url="http://patelconsultancy2005.com/staging/public/api/login";
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Respose",response);
                            try {
                                JSONObject obj=new JSONObject(response);
                                if (!obj.getBoolean("error")){
                                    JSONObject data=obj.getJSONObject("data");
                                    User user = new User(
                                            data.getInt("id"),
                                            data.getString("email"),
                                            data.getString("password")
                                    );
                                    String msg = "";
                                    if (!msg.contains("Unauthorised access")) {
                                    } else {
                                        Toast.makeText(login.this, "not Working", Toast.LENGTH_SHORT).show();
                                    }
                                    String emailTest=data.getString("id");
                                    String passTest=data.getString("api_token");
                                    Toast.makeText(getApplicationContext(),emailTest,Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("error", error.getMessage());
                        }
                    }
                    ){
                        protected Map<String,String>getParams()throws AuthFailureError{
                            Map<String,String> params=new HashMap<String, String>();
                            params.put("id","1");
                            params.put("email","123@hdfj.com");
                            params.put("password","1234");

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
        });
    }
}