package com.example.bet.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bet.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

import static android.content.Context.WINDOW_SERVICE;

public class Vente_Fragment extends Fragment {
    private static final String TAG = "Qr";
    private EditText editText;
    private ImageView imageView;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmapResult;
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_vente, container,false);
        imageView = (ImageView) view.findViewById(R.id.qr_image);

        Button btnStart = (Button) view.findViewById(R.id.button_generate);
        editText=(EditText)view.findViewById(R.id.nb_qr_vente);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //calculating bitmap dimension
                WindowManager manager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = width < height ? width : height;
                smallerDimension = smallerDimension * 3 / 4;

                String qrText="Chad|"+editText.getText();
                qrgEncoder = new QRGEncoder(qrText, null, QRGContents.Type.TEXT, smallerDimension);
                try {
                    bitmapResult = qrgEncoder.encodeAsBitmap();
                    imageView.setImageBitmap(bitmapResult);
                } catch (WriterException e) {
                    Log.v(TAG, e.toString());
                }

            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
