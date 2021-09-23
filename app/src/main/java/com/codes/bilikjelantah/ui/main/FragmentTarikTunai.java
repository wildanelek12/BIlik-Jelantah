package com.codes.bilikjelantah.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codes.bilikjelantah.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentTarikTunai extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static FragmentTarikTunai newInstance(int index) {
        FragmentTarikTunai fragment = new FragmentTarikTunai();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tarik_tunai, container, false);

        return root;
    }
}