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
import android.widget.ListView;
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
import static androidx.core.content.ContextCompat.getSystemService;

public class Profil_Fragment extends Fragment {
    private static final String TAG = "Qr";
    private EditText editText;
    private ImageView imageView;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmapResult;
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profil, container,false);
        imageView = (ImageView) view.findViewById(R.id.qr_image);
        final String qrText="thongsangchad@gmail.com|24/04/2001|3000";
        Button btnStart = (Button) view.findViewById(R.id.button_generate);
        final Button btnSave = (Button) view.findViewById(R.id.button_save);

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

                    qrgEncoder = new QRGEncoder(qrText, null, QRGContents.Type.TEXT, smallerDimension);
                    try {
                        bitmapResult = qrgEncoder.encodeAsBitmap();
                        imageView.setImageBitmap(bitmapResult);
                        btnSave.setVisibility(View.VISIBLE);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean save;
                String result;
                try {
                    String savePath = Environment.getExternalStorageDirectory() + "/QRCode/";
                    save = QRGSaver.save(savePath, "WebSite QR code", bitmapResult, QRGContents.ImageType.IMAGE_JPEG);

                    result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
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
