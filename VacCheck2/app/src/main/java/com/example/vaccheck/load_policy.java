package com.example.vaccheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class load_policy extends AppCompatActivity implements View.OnClickListener{

    private Button returnButton;
    private Button loadButton;
    private RadioGroup allRGButtons;
    private TextView warning;
    private String filename;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_policy);

        returnButton = findViewById(R.id.returnButton);
        loadButton = findViewById(R.id.loadButton);
        allRGButtons = findViewById(R.id.radiogroup);
        warning = findViewById(R.id.warningTextView);

        File[] files = getListFiles(getFilesDir().listFiles());
        if (files != null){
            addRadioButtons(files);
        }
        else{
            warning.setVisibility(View.VISIBLE);
        }

        returnButton.setOnClickListener(view -> startActivity(new Intent(load_policy.this, qr_generator.class)));
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String permissions = null;
                if ( checked ){
                    FileInputStream fis = null;
                    try{
                        fis = openFileInput(filename);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();

                        while((permissions = br.readLine()) != null){
                            sb.append(permissions);
                        }

                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Intent intent = getIntent();
                        intent.putExtra("policy", sb.toString());
                        setResult(RESULT_OK, intent);
                        finish();

                    } catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        if (fis != null){
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else{
                    Toast toast = Toast.makeText(load_policy.this, "A policy must be selected to load", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        });
    }

    private File[] getListFiles(File[] files) {
        ArrayList<File> inFiles = new ArrayList<File>();

        if (files == null){
            return null;
        }

        for (File file : files) {
            if(file.getName().endsWith(".txt")){
                inFiles.add(file);
            }
        }

        File[] f = new File[inFiles.size()];
        f = inFiles.toArray(f);
        return f;
    }

    public void addRadioButtons(File[] files){

         for (File file : files) {
            String name = file.getName();
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(name);
            rdbtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
            rdbtn.setOnClickListener(this);
            allRGButtons.addView(rdbtn);

        }

    }

    @Override
    public void onClick(View view) {
        filename = String.valueOf(((RadioButton)view).getText());
        checked = ((RadioButton) view).isChecked();
    }
}