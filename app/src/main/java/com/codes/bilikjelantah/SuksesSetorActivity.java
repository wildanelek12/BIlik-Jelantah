package com.codes.bilikjelantah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class SuksesSetorActivity extends AppCompatActivity {

    private Button btnLanjutSetor;
    private TextView tvDinNamaPenyetor;
    private TextView tvDinAlamat;
    private TextView tvDinTanggal;
    private TextView tvDinJumlahSetor;
    private TextView tvDinSaldo;
    private TextView tvDinMetodeSetor;
    private TextView tvDinMetodeBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_setor);
        initView();

        String nama = getIntent().getStringExtra("nama2");
        String alamat = getIntent().getStringExtra("alamat2");
        String tanggal = getIntent().getStringExtra("tanggal2");
        String jumlah_setor = getIntent().getStringExtra("jumlah2");
        double saldo_didapat = getIntent().getDoubleExtra("total_didapat2", 0);
        String metode_setor = getIntent().getStringExtra("metode_setor2");
        String metode_bayar = getIntent().getStringExtra("metode_bayar2");
        String hp = getIntent().getStringExtra("hp2");

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
                Intent intent = new Intent(SuksesSetorActivity.this, MainMenu.class);
                startActivity(intent);
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
}