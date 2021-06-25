package com.example.bet.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.example.bet.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class Achat extends AppCompatActivity  {
    Button scanBtn;
    TextView messageText, messageFormat;
    private DecoratedBarcodeView mDecoratedBarcodeView;
    private CaptureManager capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat);
        mDecoratedBarcodeView = (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);

        capture = new CaptureManager(Achat.this, mDecoratedBarcodeView);
        //capture.initializeFromIntent(getIntent(), savedInstanceState);
        //capture.decode();
    }
    @Override
    protected void onResume() {
        super.onResume();

        // If API 23 permissions will be asked and onResume it will ask to allow/deny
        // After choice onResume is run again, however scanner get stuck if run immediately.
        // Thus for API 23+ we run onResume() with a small delay to bypass this glitch with
        // a small tradeoff in efficiency
        // For API <23 permissions are checked on installation thus no dialog/no getting stuck
        // TODO Revisit for cleaner solution
        if(Build.VERSION.SDK_INT >= 23) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    capture.onResume();
                }
            }, 100);
        } else {
            capture.onResume();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDecoratedBarcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


}