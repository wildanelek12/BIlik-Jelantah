package com.codes.bilikjelantah.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codes.bilikjelantah.BantuanActivity;
import com.codes.bilikjelantah.DetailProfilActivity;
import com.codes.bilikjelantah.LoginActivity;
import com.codes.bilikjelantah.R;
import com.codes.bilikjelantah.TentangAplikasi;
import com.codes.bilikjelantah.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private LinearLayout accountSetting;
    private LinearLayout help;
    private LinearLayout about;
    private LinearLayout logout;
    private TextView tvNama;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        initView(root);
        tvNama.setText(HomeFragment.nama_db);
        accountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailProfilActivity.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BantuanActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TentangAplikasi.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }

    private void initView(View view) {
        accountSetting = (LinearLayout) view.findViewById(R.id.account_setting);
        help = (LinearLayout) view.findViewById(R.id.help);
        about = (LinearLayout) view.findViewById(R.id.about);
        logout = (LinearLayout) view.findViewById(R.id.logout);
        tvNama = (TextView) view.findViewById(R.id.tv_nama);
    }
}