package com.codes.bilikjelantah;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

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
    private TextView tvJumlahSaldo;
    double total_jumlah = 0,total_didapat;
    String nama,alamat,hp,tanggal,jumlah,metode_setor,metode_bayar;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_minyak);
        initView();



        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etTglPenyetor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SetorMinyak.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        etJumlahSetor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etJumlahSetor.getText().toString().equals("")){
                    tvJumlahSaldo.setText("Rp 0");
                }else {
                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                    double jumlah = Integer.parseInt(etJumlahSetor.getText().toString());
                    total_jumlah = jumlah * 2500;
                    tvJumlahSaldo.setText(formatRupiah.format(total_jumlah));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                        if (selectedId == 0) {
                            bottomSheetDialogFragment.dismiss();
                        } else {
                            RadioButton radioButton = (RadioButton) bottomSheetDialogFragment.findViewById(selectedId);
                            tvMetodeBayar.setText(radioButton.getText());
                            tvMetodeBayar.setTextColor(Color.parseColor("#ffffff"));
                            clMetodeBayar.setBackgroundColor(Color.parseColor("#F4A22B"));
                            metode_bayar = radioButton.getText().toString();
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
                        if (selectedId == 0) {
                            bottomSheetDialogFragment.dismiss();
                        } else {
                            RadioButton radioButton = (RadioButton) bottomSheetDialogFragment.findViewById(selectedId);
                            tvMetodeSetor.setText(radioButton.getText());
                            tvMetodeSetor.setTextColor(Color.parseColor("#ffffff"));
                            clMetodeSetor.setBackgroundColor(Color.parseColor("#F4A22B"));
                            metode_setor = radioButton.getText().toString();
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


                if (etNamaPenyetor.getText().toString().equals("")||etAlamatPenyetor.getText().toString().equals("")||etHandphonePenyetor.getText().toString().equals("")||etTglPenyetor.getText().toString().equals("")||etJumlahSetor.getText().toString().equals("")||metode_bayar==null||metode_setor==null){
                    new SweetAlertDialog(SetorMinyak.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Semua Wajib Di Isi")
                            .show();
                }else if(etJumlahSetor.getText().toString().equals("0")){
                    new SweetAlertDialog(SetorMinyak.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Minimal 1 Liter !")
                            .show();
                }else {
                    nama = etNamaPenyetor.getText().toString();
                    alamat = etAlamatPenyetor.getText().toString();
                    hp = etHandphonePenyetor.getText().toString();
                    tanggal = etTglPenyetor.getText().toString();
                    jumlah = etJumlahSetor.getText().toString();
                    total_didapat = total_jumlah;
                    Intent intent = new Intent(SetorMinyak.this, KonfirmasiSetor.class);
                    intent.putExtra("nama",nama);
                    intent.putExtra("alamat",alamat);
                    intent.putExtra("hp",hp);
                    intent.putExtra("tanggal",tanggal);
                    intent.putExtra("jumlah",jumlah);
                    intent.putExtra("total_didapat",total_didapat);
                    intent.putExtra("metode_bayar",metode_bayar);
                    intent.putExtra("metode_setor",metode_setor);
                    startActivity(intent);

                }


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
        tvJumlahSaldo = (TextView) findViewById(R.id.tv_jumlah_saldo);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etTglPenyetor.setText(sdf.format(myCalendar.getTime()));
    }
}