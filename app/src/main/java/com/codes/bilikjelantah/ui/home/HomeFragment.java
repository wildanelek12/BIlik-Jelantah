package com.codes.bilikjelantah.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.codes.bilikjelantah.BonusActivity;
import com.codes.bilikjelantah.DetailArtikelActivity;
import com.codes.bilikjelantah.JadwalPenyetoran;
import com.codes.bilikjelantah.Model.User;
import com.codes.bilikjelantah.R;
import com.codes.bilikjelantah.RegisterActivity;
import com.codes.bilikjelantah.RiwayatPenyetoran;
import com.codes.bilikjelantah.SetorMinyak;
import com.codes.bilikjelantah.TarikTunaiActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ConstraintLayout btnMenuHome1;
    private ConstraintLayout btnMenuHome2;
    private ConstraintLayout btnMenuHome3;
    private ImageView btnMenuTarik;
    private ImageView btnMenuCek;
    private ConstraintLayout clArtikel;
    private TextView tvNama;
    private TextView tvSaldo;
    private TextView tvJmlLiter;
    public  static  double saldo;
    public static int jumlah_liter,checkpoint;
    public  static String nama_db,password_db,email_db;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnMenuHome1 = (ConstraintLayout) root.findViewById(R.id.btn_menu_home_1);
        btnMenuHome2 = (ConstraintLayout) root.findViewById(R.id.btn_menu_home_2);
        btnMenuHome3 = (ConstraintLayout) root.findViewById(R.id.btn_menu_home_3);
        btnMenuTarik = (ImageView) root.findViewById(R.id.btn_menu_tarik);
        btnMenuCek = (ImageView) root.findViewById(R.id.btn_menu_cek);
        clArtikel = (ConstraintLayout) root.findViewById(R.id.cl_artikel);
        tvNama = (TextView) root.findViewById(R.id.tv_nama);
        tvSaldo = (TextView) root.findViewById(R.id.tv_saldo);
        tvJmlLiter = (TextView) root.findViewById(R.id.tv_jml_liter);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        tvNama.setText("");
        tvJmlLiter.setText("");
        tvSaldo.setText("");
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(user.getUid());
        SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.orange);
        pDialog.setTitleText("Loading ...");
        pDialog.setCancelable(true);
        pDialog.show();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User post = dataSnapshot.getValue(User.class);
                tvNama.setText(post.getNama());
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                saldo = post.getSaldo();
                jumlah_liter = post.getJumlah_liter();
                checkpoint = post.getCheckpoint();
                nama_db = post.getNama();
                password_db = post.getPassword();
                email_db = post.getEmail();
                tvSaldo.setText(formatRupiah.format(post.getSaldo()));
                tvJmlLiter.setText(String.valueOf(post.getJumlah_liter()));
                pDialog.dismissWithAnimation();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismissWithAnimation();
            }
        };
        databaseReference.addValueEventListener(postListener);

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
                Intent intent = new Intent(getContext(), JadwalPenyetoran.class);
                startActivity(intent);
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
        clArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailArtikelActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }


}