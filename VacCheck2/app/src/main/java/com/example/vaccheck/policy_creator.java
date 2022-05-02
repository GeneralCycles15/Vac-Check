package com.example.vaccheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vaccheck.policy.Policy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class policy_creator extends AppCompatActivity {

    private CheckBox lname;
    private CheckBox fname;
    private CheckBox mInitial;
    private CheckBox DOB;
    private CheckBox patientNum;
    private CheckBox dose;
    private CheckBox product;
    private CheckBox vacDate;
    private CheckBox site;
    private CheckBox status;
    private CheckBox idImage;

    private EditText filename;

    private Button buttonCancel;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_creator);

        lname = findViewById(R.id.lastNameCheckBox);
        fname = findViewById(R.id.firstNameCheckBox);
        mInitial = findViewById(R.id.miCheckBox);
        DOB = findViewById(R.id.dobCheckBox);
        patientNum = findViewById(R.id.patientNumCheckBox);
        dose = findViewById(R.id.vacDoseCheckBox);
        product = findViewById(R.id.productNameCheckBox);
        vacDate = findViewById(R.id.vacDateCheckBox);
        site = findViewById(R.id.siteCheckBox);
        status = findViewById(R.id.statusCheckBox);
        idImage = findViewById(R.id.imageCheckBox);

        filename = findViewById(R.id.policyName);

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(view -> startActivity(new Intent(policy_creator.this, qr_generator.class)));

        buttonCreate = findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(view -> createPol());
    }

    private void createPol(){

        Policy pol = new Policy(lname.isChecked(), fname.isChecked(),
                mInitial.isChecked(), DOB.isChecked(),
                patientNum.isChecked(), dose.isChecked(),
                product.isChecked(), vacDate.isChecked(),
                site.isChecked(), status.isChecked(),
                idImage.isChecked());

        String name = filename.getText().toString().trim();

        if (pol.isEmpty()){
            Toast toast = Toast.makeText(policy_creator.this, R.string.no_permissions_set, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
        else if(name.isEmpty()){
            Toast toast = Toast.makeText(policy_creator.this, "You need to provide a name for your policy.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
        else{
            String filepath = name + ".txt";
            FileOutputStream fos = null;
            try{
                fos = openFileOutput(filepath, Context.MODE_PRIVATE);
                String data = pol.toString();
                fos.write(data.getBytes());
                fos.flush();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if (fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Toast toast = Toast.makeText(policy_creator.this, "File: " + name + " has been created.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }

    }

}