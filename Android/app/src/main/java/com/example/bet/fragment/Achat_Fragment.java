package com.example.bet.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.bet.R;
import com.google.zxing.Result;

public class Achat_Fragment extends Fragment  {
    Button acheter_Qr;
    TextView prixAchat,nomVendeur;
    private Context mContext;
    private CodeScanner mCodeScanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_achat, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        acheter_Qr=(Button)root.findViewById(R.id.bouton_achat_qr);
        prixAchat=(TextView)root.findViewById(R.id.nomVendeur);
        nomVendeur=(TextView)root.findViewById(R.id.prix_vendeur);
        acheter_Qr.setVisibility(View.GONE);
        prixAchat.setVisibility(View.GONE);
        nomVendeur.setVisibility(View.GONE);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                        prixAchat.setVisibility(View.VISIBLE);
                        nomVendeur.setVisibility(View.VISIBLE);
                        acheter_Qr.setVisibility(View.VISIBLE);

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

}
