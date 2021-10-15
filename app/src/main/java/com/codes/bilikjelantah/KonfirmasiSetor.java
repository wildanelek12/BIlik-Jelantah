package com.codes.bilikjelantah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.codes.bilikjelantah.Model.SetorMinyak;
import com.codes.bilikjelantah.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class KonfirmasiSetor extends AppCompatActivity {

    private Button btnLanjutSetor;
    private TextView tvDinNamaPenyetor;
    private TextView tvDinAlamat;
    private TextView tvDinTanggal;
    private TextView tvDinJumlahSetor;
    private TextView tvDinSaldo;
    private TextView tvDinMetodeSetor;
    private TextView tvDinMetodeBayar;
    double saldo_awal;
    DatabaseReference databaseReference,databaseReference2,databaseReference3,databaseReference4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_setor);
        initView();

        databaseReference = FirebaseDatabase.getInstance().getReference("setor").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference2 = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("saldo");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("jumlah_liter");
        databaseReference4 = FirebaseDatabase.getInstance().getReference("setor_riwayat");

        String nama = getIntent().getStringExtra("nama");
        String alamat = getIntent().getStringExtra("alamat");
        String tanggal = getIntent().getStringExtra("tanggal");
        String jumlah_setor = getIntent().getStringExtra("jumlah");
        double saldo_didapat = getIntent().getDoubleExtra("total_didapat",0);
        String metode_setor = getIntent().getStringExtra("metode_setor");
        String metode_bayar = getIntent().getStringExtra("metode_bayar");
        String hp = getIntent().getStringExtra("hp");
        String code = getAlphaNumericString(5);

        tvDinNamaPenyetor.setText(nama);
        tvDinAlamat.setText(alamat);
        tvDinTanggal.setText(tanggal);
        tvDinJumlahSetor.setText(jumlah_setor);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvDinSaldo.setText(String.valueOf(formatRupiah.format(saldo_didapat)));
        tvDinMetodeSetor.setText(metode_setor);
        tvDinMetodeBayar.setText(metode_bayar);

        btnLanjutSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SweetAlertDialog(KonfirmasiSetor.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Perhatian !")
                        .setContentText("Apakah Data Sudah Benar ? ")
                        .setCancelText("Belum ")
                        .setConfirmText("Ya saya yakin ! ")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                com.codes.bilikjelantah.Model.SetorMinyak setorMinyak = new SetorMinyak(code,nama,alamat,hp,tanggal,jumlah_setor,metode_setor,metode_bayar,saldo_didapat);
                                databaseReference.push().setValue(setorMinyak);
                                databaseReference4.push().setValue(setorMinyak);
                                if (metode_bayar.equals("Masuk ke Saldo")){
                                    databaseReference2.setValue(HomeFragment.saldo+saldo_didapat);
                                }
                                databaseReference3.setValue(HomeFragment.jumlah_liter+Integer.parseInt(jumlah_setor));
                                Intent intent = new Intent(KonfirmasiSetor.this, SuksesSetorActivity.class);
                                intent.putExtra("nama2",nama);
                                intent.putExtra("alamat2",alamat);
                                intent.putExtra("hp2",hp);
                                intent.putExtra("tanggal2",tanggal);
                                intent.putExtra("jumlah2",jumlah_setor);
                                intent.putExtra("total_didapat2",saldo_didapat);
                                intent.putExtra("metode_bayar2",metode_bayar);
                                intent.putExtra("metode_setor2",metode_setor);
                                startActivity(intent);
                            }
                        })
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();


            }
        });
    }

    private void initView() {
        btnLanjutSetor = (Button) findViewById(R.id.btn_lanjut_setor);
        tvDinNamaPenyetor = (TextView) findViewById(R.id.tv_din_nama_penyetor);
        tvDinAlamat = (TextView) findViewById(R.id.tv_din_alamat);
        tvDinTanggal = (TextView) findViewById(R.id.tv_din_tanggal);
        tvDinJumlahSetor = (TextView) findViewById(R.id.tv_din_jumlah_setor);
        tvDinSaldo = (TextView) findViewById(R.id.tv_din_saldo);
        tvDinMetodeSetor = (TextView) findViewById(R.id.tv_din_metode_setor);
        tvDinMetodeBayar = (TextView) findViewById(R.id.tv_din_metode_bayar);
    }
    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        databaseReference2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                Double value = snapshot.getValue(Double.class);
//
//                // after getting the value we are setting
//                // our value to our text view in below line.
//                saldo_awal = value;
//                Toast.makeText(KonfirmasiSetor.this, String.valueOf(saldo_awal), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // calling on cancelled method when we receive
//                // any error or we are not able to get the data.
//                Toast.makeText(KonfirmasiSetor.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}