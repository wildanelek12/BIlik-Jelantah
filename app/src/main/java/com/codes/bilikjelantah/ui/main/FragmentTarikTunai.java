package com.codes.bilikjelantah.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codes.bilikjelantah.Model.Penarikan;
import com.codes.bilikjelantah.R;
import com.codes.bilikjelantah.RegisterActivity;
import com.codes.bilikjelantah.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentTarikTunai extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Spinner etPilihBank;
    private EditText etPilihRekening;
    private EditText etNamaNasabah;
    private EditText etNominal;
    private TextView tvTotalSaldo;
    private Button kirim;
    DatabaseReference databaseReference,databaseReference2,databaseReference3;


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
        initView(root);

        databaseReference = FirebaseDatabase.getInstance().getReference("tarik").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference2 = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("saldo");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("tarik_riwayat");
        int value = (int)HomeFragment.saldo;
        tvTotalSaldo.setText("Saldo Anda Adalah Rp "+String.valueOf(value));
        etNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etNominal.getText().toString().equals("") ){
                    etNominal.setText("0");
                }
                if (HomeFragment.saldo < Double.parseDouble(etNominal.getText().toString()) ){
                    etNominal.setError("Nominal Anda Tidak Cukup");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomeFragment.saldo - Double.parseDouble(etNominal.getText().toString())< 0 ){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Saldo anda kurang")
                            .show();
                }else if(etPilihRekening.getText().toString().equals("")||etNominal.getText().toString().equals("")||etNamaNasabah.getText().toString().equals("")){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Isi Semua Terlebih Dahulu")
                            .show();
                }else{
                    String tanggal =  new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
                    String mGroupId = databaseReference.push().getKey();
                    Penarikan penarikan = new Penarikan(getAlphaNumericString(6),etPilihBank.getSelectedItem().toString(),etPilihRekening.getText().toString(),etNamaNasabah.getText().toString(),"waiting",Double.parseDouble(etNominal.getText().toString()),tanggal,FirebaseAuth.getInstance().getCurrentUser().getUid(),mGroupId);
                    databaseReference.child(mGroupId).setValue(penarikan);
                    databaseReference3.child(mGroupId).setValue(penarikan);
                    databaseReference2.setValue(HomeFragment.saldo-Double.parseDouble(etNominal.getText().toString()));
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Perhatian")
                            .setContentText("Penarikan Anda Berhasil")
                            .show();
                    etNamaNasabah.setText("");
                    etNominal.setText("");
                    etPilihRekening.setText("");
                }

            }
        });


        return root;
    }

    private void initView(View view) {
        etPilihBank = (Spinner) view.findViewById(R.id.et_pilih_bank);
        etPilihRekening = (EditText) view.findViewById(R.id.et_pilih_rekening);
        etNamaNasabah = (EditText) view.findViewById(R.id.et_nama_nasabah);
        etNominal = (EditText) view.findViewById(R.id.et_nominal);
        tvTotalSaldo = (TextView) view.findViewById(R.id.tv_total_saldo);
        kirim = (Button) view.findViewById(R.id.kirim);
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

}