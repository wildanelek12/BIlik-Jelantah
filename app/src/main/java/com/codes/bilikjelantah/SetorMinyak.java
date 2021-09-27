package com.codes.bilikjelantah;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Set;

public class SetorMinyak extends AppCompatActivity {

    private EditText etNamaPenyetor;
    private EditText etAlamatPenyetor;
    private EditText etHandphonePenyetor;
    private EditText etTglPenyetor;
    private EditText etJumlahSetor;
    private CardView btnPilihMtdPenyetoran;
    private CardView btnPilihMtdPembayaran;
    private ImageView btnLanjutSetor;
    private TextView tvMetodeSetor;
    private TextView tvMetodeBayar;
    private ConstraintLayout clMetodeSetor;
    private ConstraintLayout clMetodeBayar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_minyak);
        initView();
        btnPilihMtdPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialogFragment = new BottomSheetDialog(SetorMinyak.this);
                bottomSheetDialogFragment.setContentView(R.layout.partial_metode_pembayaran);
                RadioGroup radioGroup = bottomSheetDialogFragment.findViewById(R.id.radioGroup);
                ImageView btn_pilih = bottomSheetDialogFragment.findViewById(R.id.btn_pilih);
                btn_pilih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        if (selectedId==0){
                            bottomSheetDialogFragment.dismiss();
                        }else{
                            RadioButton radioButton = (RadioButton) bottomSheetDialogFragment.findViewById(selectedId);
                            tvMetodeBayar.setText(radioButton.getText());
                            clMetodeBayar.setBackgroundColor(Color.GREEN);
                            bottomSheetDialogFragment.dismiss();
                        }
                    }
                });
                bottomSheetDialogFragment.show();
            }
        });
        btnPilihMtdPenyetoran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialogFragment = new BottomSheetDialog(SetorMinyak.this);
                bottomSheetDialogFragment.setContentView(R.layout.partial_metode_setor);
                RadioGroup radioGroup = bottomSheetDialogFragment.findViewById(R.id.radioGroup);
                ImageView btn_pilih = bottomSheetDialogFragment.findViewById(R.id.btn_pilih);
                btn_pilih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        if (selectedId==0){
                            bottomSheetDialogFragment.dismiss();
                        }else{
                            RadioButton radioButton = (RadioButton) bottomSheetDialogFragment.findViewById(selectedId);
                            tvMetodeSetor.setText(radioButton.getText());
                            clMetodeSetor.setBackgroundColor(Color.GREEN);
                            bottomSheetDialogFragment.dismiss();
                        }
                    }
                });
                bottomSheetDialogFragment.show();
            }
        });
        btnLanjutSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetorMinyak.this,KonfirmasiSetor.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        etNamaPenyetor = (EditText) findViewById(R.id.et_nama_penyetor);
        etAlamatPenyetor = (EditText) findViewById(R.id.et_alamat_penyetor);
        etHandphonePenyetor = (EditText) findViewById(R.id.et_handphone_penyetor);
        etTglPenyetor = (EditText) findViewById(R.id.et_tgl_penyetor);
        etJumlahSetor = (EditText) findViewById(R.id.et_jumlah_setor);
        btnPilihMtdPenyetoran = (CardView) findViewById(R.id.btn_pilih_mtd_penyetoran);
        btnPilihMtdPembayaran = (CardView) findViewById(R.id.btn_pilih_mtd_pembayaran);
        btnLanjutSetor = (ImageView) findViewById(R.id.btn_lanjut_setor);
        tvMetodeSetor = (TextView) findViewById(R.id.tv_metode_setor);
        tvMetodeBayar = (TextView) findViewById(R.id.tv_metode_bayar);
        clMetodeSetor = (ConstraintLayout) findViewById(R.id.cl_metode_setor);
        clMetodeBayar = (ConstraintLayout) findViewById(R.id.cl_metode_bayar);
    }
}