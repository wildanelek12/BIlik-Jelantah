package com.codes.bilikjelantah.ui.jelantah;

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
import com.codes.bilikjelantah.SetorMinyak;
import com.codes.bilikjelantah.TarikTunaiActivity;
import com.codes.bilikjelantah.ui.home.HomeViewModel;

public class JelantahFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jelantah, container, false);

        return root;
    }



}