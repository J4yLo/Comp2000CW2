package com.example.comp2000cw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.comp2000cw2.Projects;
import com.example.comp2000cw2.R;

import java.util.ArrayList;

public class Projects_ListAdapter extends ArrayAdapter<Projects> {

    public Projects_ListAdapter(Context context, ArrayList<Projects> projectsArrayList)
    {
        super(context,R.layout.projects_layout,projectsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Projects projects = getItem(position);


        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.projects_layout,parent, false);
        }

        ImageView projImg = convertView.findViewById(R.id.ProjectPicture);
        TextView projTitle = convertView.findViewById(R.id.ProjectName);
        TextView projdesc = convertView.findViewById(R.id.ProjectDescription);
        TextView projCode = convertView.findViewById(R.id.LProjectCode);
        TextView projDate = convertView.findViewById(R.id.ProjectUploadDate);

        projImg.setImageResource(projects.imgID);
        projTitle.setText(projects.projectName);
        projdesc.setText(projects.projectDescription);
        projCode.setText(projects.projectCode);
        projDate.setText(projects.projectDate);


        return convertView;
    }
}
