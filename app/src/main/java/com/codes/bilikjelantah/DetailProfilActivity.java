package com.codes.bilikjelantah;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.codes.bilikjelantah.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetailProfilActivity extends AppCompatActivity {

    private Button btnEditProfil;
    private TextView tvNama;
    private TextView tvNamaDetail;
    private TextView tvEmailDetail;
    private TextView tvTotalSaldoDetail;
    private TextView tvTotalLiter;
    DatabaseReference databaseReference,databaseReference2;
    private Button btnEditPassword;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);
        initView();
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("nama");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("password");

        tvNama.setText(HomeFragment.nama_db);
        tvEmailDetail.setText(HomeFragment.email_db);
        tvNamaDetail.setText(HomeFragment.nama_db);
        tvTotalLiter.setText(String.valueOf(HomeFragment.jumlah_liter));
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvTotalSaldoDetail.setText(formatRupiah.format(HomeFragment.saldo));
        user = FirebaseAuth.getInstance().getCurrentUser();

        btnEditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateDialogue();
            }
        });
        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateDialogue2();
            }
        });
    }

    private void initView() {
        btnEditProfil = (Button) findViewById(R.id.btn_edit_profil);
        tvNama = (TextView) findViewById(R.id.tv_nama);
        tvNamaDetail = (TextView) findViewById(R.id.tv_nama_detail);
        tvEmailDetail = (TextView) findViewById(R.id.tv_email_detail);
        tvTotalSaldoDetail = (TextView) findViewById(R.id.tv_total_saldo_detail);
        tvTotalLiter = (TextView) findViewById(R.id.tv_total_liter);
        btnEditPassword = (Button) findViewById(R.id.btn_edit_password);
    }

    public void inflateDialogue() {
        LayoutInflater layoutInflater = DetailProfilActivity.this.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.item_dialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Edit Profil");

        alertDialog.setCancelable(false);

        EditText et_nama = (EditText) view.findViewById(R.id.et_nama);
        et_nama.setText(HomeFragment.nama_db);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseReference.setValue(et_nama.getText().toString());
                new SweetAlertDialog(DetailProfilActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Perhatian")
                        .setContentText("Nama Berhasil Diganti")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                Intent intent = new Intent(DetailProfilActivity.this, MainMenu.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .show();


            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }
    public void inflateDialogue2() {
        LayoutInflater layoutInflater = DetailProfilActivity.this.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.item_edit_pw, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Edit Password");

        alertDialog.setCancelable(false);

        EditText et_password = (EditText) view.findViewById(R.id.et_pw);
        EditText et_konfirm = (EditText) view.findViewById(R.id.et_konfirm_password);




        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               if (!et_password.getText().toString().equals(HomeFragment.password_db)){
                   new SweetAlertDialog(DetailProfilActivity.this, SweetAlertDialog.ERROR_TYPE)
                           .setTitleText("Perhatian")
                           .setContentText("Password Tidak Sama")
                           .show();
               }else if(et_password.getText().toString().equals("")||et_konfirm.getText().toString().equals("")){
                   new SweetAlertDialog(DetailProfilActivity.this, SweetAlertDialog.ERROR_TYPE)
                           .setTitleText("Perhatian")
                           .setContentText("Isi Semua")
                           .show();
               }else {
                   AuthCredential credential = EmailAuthProvider
                           .getCredential(HomeFragment.email_db, HomeFragment.password_db);

// Prompt the user to re-provide their sign-in credentials
                   user.reauthenticate(credential)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()) {
                                       user.updatePassword(et_konfirm.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               if (task.isSuccessful()) {
                                                   databaseReference2.setValue(et_konfirm.getText().toString());
                                                   new SweetAlertDialog(DetailProfilActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                           .setTitleText("Perhatian")
                                                           .setContentText("Password Berhasil Diubah")
                                                           .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                               @Override
                                                               public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                   Intent intent = new Intent(DetailProfilActivity.this,MainMenu.class);
                                                                   startActivity(intent);
                                                               }
                                                           })
                                                           .show();
                                               } else {
                                                   new SweetAlertDialog(DetailProfilActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                           .setTitleText("Perhatian")
                                                           .setContentText("Error Type")
                                                           .show();
                                               }
                                           }
                                       });
                                   } else {
                                       new SweetAlertDialog(DetailProfilActivity.this, SweetAlertDialog.ERROR_TYPE)
                                               .setTitleText("Perhatian")
                                               .setContentText("Error Type")
                                               .show();
                                   }
                               }
                           });

               }

            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }
}