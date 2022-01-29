package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.comp2000cw2.Projects_ListAdapter;
import com.example.comp2000cw2.R;
import com.example.comp2000cw2.databinding.ActivityMainBinding;
import com.example.comp2000cw2.databinding.ActivityViewProjectsPageBinding;
import com.example.comp2000cw2.databinding.ProjectsLayoutBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class View_Projects_Page extends AppCompatActivity {

    ActivityViewProjectsPageBinding binding;

    //Variables
    private RequestQueue requestQueue;
    private String UsersID;
    private String UsersName;



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
        binding = ActivityViewProjectsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestQueue = Volley.newRequestQueue(this);

        String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";
         ArrayList<Projects> projectsArrayList = new ArrayList<>();


        //Api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            Log.d("ProjArray At Start", "Response: " + projectsArrayList.toString());

                        // Interpret Values from get request

                        for (int i = 0; i < response.length(); i ++)
                        {
                            try
                            {
                                JSONObject jsonObject = response.getJSONObject(i);
                                    String UserID = jsonObject.get("studentID").toString();
                                    String projName =  jsonObject.get("title").toString();
                                    String projUsers = jsonObject.get("first_Name").toString() + " " + jsonObject.get("second_Name").toString();
                                    String projDesc =  jsonObject.get("description").toString();
                                    String projCode =  jsonObject.get("projectID").toString();;
                                    String projDate = jsonObject.get("year").toString();;
                                    int imgID = R.drawable.ic_baseline_assessment_24;


                                    if (projName == null)
                                    {
                                        projName = "none assigned";
                                    }
                                    if (projUsers == null)
                                    {
                                        projUsers = "none assigned";
                                    }
                                    if (projDesc == null)
                                    {
                                        projDesc = "none assigned";
                                    }
                                    if (projCode == null)
                                    {
                                        projCode = "none assigned";
                                    }
                                    if (projDate == null)
                                    {
                                        projDate = "none assigned";
                                    }
                                    if (imgID == 0)
                                    {
                                        imgID = R.drawable.ic_baseline_event_available_24;
                                    }

                                    // Create an item for the list

                                    //Toast.makeText(View_Projects_Page.this, UserID, Toast.LENGTH_SHORT).show();
                                    Projects proj = new Projects(projName,projUsers,projDesc,projCode,projDate,imgID);
                                    projectsArrayList.add(proj);
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                Log.d("ProjArray At Error", "Response: Error");
                                Toast.makeText(View_Projects_Page.this, "Error Converting Data", Toast.LENGTH_SHORT).show();
                            }
                            Log.d("ProjArray At End", "Response: " + projectsArrayList.get(i).projectName.toString());
                        }
                        ListAdapter listAdapter = new Projects_ListAdapter(View_Projects_Page.this, projectsArrayList);
                        binding.ProjectsList.setAdapter(listAdapter);
                        binding.ProjectsList.setClickable(true);
                        // Change Pages on Item Click and passes through values
                        binding.ProjectsList.setOnItemClickListener((parent, view, position, id) ->
                        {
                            Intent ProjectDetails = new Intent(View_Projects_Page.this,Home_Page.class);
                            //Project Details
                            ProjectDetails.putExtra("Name",projectsArrayList.get(position).projectName.toString());
                            ProjectDetails.putExtra("Users",projectsArrayList.get(position).projectUsers.toString());
                            ProjectDetails.putExtra("Description",projectsArrayList.get(position).projectDescription.toString());
                            ProjectDetails.putExtra("Code",projectsArrayList.get(position).projectCode.toString());
                            ProjectDetails.putExtra("Date",projectsArrayList.get(position).projectDate.toString());
                            ProjectDetails.putExtra("imgID",projectsArrayList.get(position).imgID);

                            //User Details
                            ProjectDetails.putExtra("UserID", UsersID);
                            ProjectDetails.putExtra("UserName", UsersName);
                            startActivity(ProjectDetails);
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Response: " + error.toString());
                }
        });
        requestQueue.add(jsonArrayRequest);
    }
}