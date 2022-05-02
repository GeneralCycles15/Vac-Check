package com.example.vaccheck;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vaccheck.policy.Policy;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qr_generator extends AppCompatActivity implements QRDialog.QRDialogListener {

    private Policy pol = new Policy();
    private Button buttonQRCode;
    private Button buttonAbout;
    private Button buttonPolicies;
    private Button buttonLoadPolicies;
    private final int REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);

        buttonQRCode = findViewById(R.id.buttonQRCode);
        buttonAbout = findViewById(R.id.buttonAbout);
        buttonPolicies = findViewById(R.id.buttonPolicies);
        buttonLoadPolicies = findViewById(R.id.loadPolicyButton);

        buttonAbout.setOnClickListener(view -> about_app_dialog());
        buttonQRCode.setOnClickListener(view -> generate_qr_code_dialog());
        buttonPolicies.setOnClickListener(view -> startActivity(new Intent(qr_generator.this, policy_creator.class)));
        buttonLoadPolicies.setOnClickListener(view -> startActivityForResult(new Intent(qr_generator.this, load_policy.class), REQUEST_CODE));
    }

    private void about_app_dialog(){
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.about_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        ImageView closeButton = dialog.findViewById(R.id.btn_close);
        closeButton.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    private void write_qr_code_to_dialog(String value){
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.qr_code_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background2);
        ImageView exitButton = dialog.findViewById(R.id.btn_exit);
        ImageView qrcode = dialog.findViewById(R.id.qrcode);
        exitButton.setOnClickListener(view -> dialog.dismiss());

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(value, BarcodeFormat.QR_CODE,
                    350, 350);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        dialog.show();
    }

    public void generate_qr_code_dialog(){
        QRDialog qrDialog = new QRDialog();
        qrDialog.show(getSupportFragmentManager(), "qr dialog");
        Log.d("QR Object", "generate_qr_code: " + pol.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null) {
                write_qr_code_to_dialog(data.getStringExtra("policy"));
            }else{
                Toast toast = Toast.makeText(qr_generator.this, "File contents were not loaded", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }

    }

    @Override
    public void createQR(Policy p) {
        pol.setPolicy(p);
        Log.d("QR Object", "returned createQR: " + pol.toString());
        if (pol.isEmpty()){
            Toast toast = Toast.makeText(qr_generator.this, R.string.no_permissions_set, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }else{
            write_qr_code_to_dialog(pol.toString());
        }
    }
}