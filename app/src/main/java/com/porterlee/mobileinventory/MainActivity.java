package com.porterlee.mobileinventory;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.function.Consumer;

import device.scanner.DecodeResult;
import device.scanner.IScannerService;

public class MainActivity extends AppCompatActivity {
    private Dialog dialog;
    static IScannerService iScanner = null;
    static DecodeResult mDecodeResult = new DecodeResult();
    static Consumer simpleReference = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Select Mode");
        builder.setMessage("Would you like to preload locations or start a standard inventory?");
        builder.setNegativeButton("Preload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Preload");
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this, PreloadActivity.class));
            }
        });
        builder.setPositiveButton("Standard Inventory", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Standard Inventory");
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this, InventoryActivity.class));
            }
        });
        dialog = builder.create();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!dialog.isShowing()) dialog.show();
    }
}

