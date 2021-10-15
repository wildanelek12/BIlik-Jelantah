package com.codes.bilikjelantah.ui.jelantah;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codes.bilikjelantah.R;
import com.codes.bilikjelantah.WebviewActivity;

public class JelantahFragment extends Fragment {


    private Button btnDaftarPenjual;
    private Button btnBukaJelantah;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jelantah, container, false);
        initView(root);
        btnBukaJelantah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.putExtra("url","https://bilikjelantah.bukaolshop.site/");
                startActivity(intent);

            }
        });
        btnDaftarPenjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://wa.me/message/K4VLEFNWDNNIA1";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return root;
    }


    private void initView(View view) {
        btnDaftarPenjual = (Button) view.findViewById(R.id.btn_daftar_penjual);
        btnBukaJelantah = (Button) view.findViewById(R.id.btn_buka_jelantah);
    }
}