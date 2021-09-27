package com.codes.bilikjelantah.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.codes.bilikjelantah.BonusActivity;
import com.codes.bilikjelantah.R;
import com.codes.bilikjelantah.RiwayatPenyetoran;
import com.codes.bilikjelantah.SetorMinyak;
import com.codes.bilikjelantah.TarikTunaiActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ConstraintLayout btnMenuHome1;
    private ConstraintLayout btnMenuHome2;
    private ConstraintLayout btnMenuHome3;
    private ImageView btnMenuTarik;
    private ImageView btnMenuCek;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnMenuHome1 = (ConstraintLayout) root.findViewById(R.id.btn_menu_home_1);
        btnMenuHome2 = (ConstraintLayout) root.findViewById(R.id.btn_menu_home_2);
        btnMenuHome3 = (ConstraintLayout) root.findViewById(R.id.btn_menu_home_3);
        btnMenuTarik = (ImageView) root.findViewById(R.id.btn_menu_tarik);
        btnMenuCek = (ImageView) root.findViewById(R.id.btn_menu_cek);
        btnMenuHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SetorMinyak.class);
                startActivity(intent);
            }
        });
        btnMenuHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnMenuHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RiwayatPenyetoran.class);
                startActivity(intent);
            }
        });
        btnMenuCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BonusActivity.class);
                startActivity(intent);
            }
        });
        btnMenuTarik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TarikTunaiActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }



}