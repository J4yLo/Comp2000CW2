package com.example.comp2000cw2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.comp2000cw2.R;

import java.util.HashMap;
import java.util.Map;

public class Remove_Program extends AppCompatActivity {

    private String UsersID;
    private String UsersName;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        UsersID = intent.getStringExtra("UserID");
        UsersName = intent.getStringExtra("UserName");



        // Full Screen Window
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Render Page Content
        setContentView(R.layout.activity_remove_program);

        //Variables
        ImageButton Back = findViewById(R.id.BackBtn);
        TextView Delete = findViewById(R.id.Delete);
        EditText ProjID = findViewById(R.id.ProjectCode);

        //Back Button Function
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHomePage();
            }
        });



        Delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + UsersID;
                StringRequest RemoveProgramme = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Remove_Program.this, "Success", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Remove_Program.this, "Failure to remove Programme", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("projectID",ProjID.getText().toString() );
                        return super.getParams();
                    }
                };
                requestQueue = Volley.newRequestQueue(Remove_Program.this);
                requestQueue.add(RemoveProgramme);


            }




        });
    }


    // Change Pages
    public void OpenHomePage(){
        Intent HomePage = new Intent(this, Home_Page.class);
        HomePage.putExtra("UserID", UsersID);
        HomePage.putExtra("UserName", UsersName);
        startActivity(HomePage);

    }

}
