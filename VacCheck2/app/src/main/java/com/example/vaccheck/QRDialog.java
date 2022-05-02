package com.example.vaccheck;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.vaccheck.policy.Policy;

import java.util.Objects;

public class QRDialog extends AppCompatDialogFragment {

    public interface QRDialogListener{
        void createQR(Policy p);
    }
    private QRDialogListener listener;

    private CheckBox lastName;
    private CheckBox firstName;
    private CheckBox middleInitial;
    private CheckBox DOB;
    private CheckBox PatientNum;
    private CheckBox vaccineDose;
    private CheckBox productName;
    private CheckBox vacDate;
    private CheckBox site;
    private CheckBox status;
    private CheckBox image;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyQRGenDialog);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.qr_generate_dialog, null);

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Objects.requireNonNull(getDialog()).dismiss();
                    }
                })
                .setPositiveButton("Generate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Policy policy = new Policy(lastName.isChecked(), firstName.isChecked(),
                                                   middleInitial.isChecked(), DOB.isChecked(),
                                                   PatientNum.isChecked(), vaccineDose.isChecked(),
                                                   productName.isChecked(), vacDate.isChecked(),
                                                   site.isChecked(), status.isChecked(),
                                                   image.isChecked());

                        Log.d("QR Object", "Dialog onClick: " + policy);
                        listener.createQR(policy);
                        Objects.requireNonNull(getDialog()).dismiss();
                    }

                });

        lastName = view.findViewById(R.id.lastNameCheckBox);
        firstName = view.findViewById(R.id.firstNameCheckBox);
        middleInitial = view.findViewById(R.id.miCheckBox);
        DOB = view.findViewById(R.id.dobCheckBox);
        PatientNum = view.findViewById(R.id.patientNumCheckBox);
        vaccineDose = view.findViewById(R.id.vacDoseCheckBox);
        productName = view.findViewById(R.id.productNameCheckBox);
        vacDate = view.findViewById(R.id.vacDateCheckBox);
        site = view.findViewById(R.id.siteCheckBox);
        status = view.findViewById(R.id.statusCheckBox);
        image = view.findViewById(R.id.imageCheckBox);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (QRDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context +
                    "must implement Listener interface");
        }
    }
}
