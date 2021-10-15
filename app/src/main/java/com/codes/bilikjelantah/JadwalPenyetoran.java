package com.codes.bilikjelantah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.codes.bilikjelantah.Adapter.AdapterJadwal;
import com.codes.bilikjelantah.Model.Jadwal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class JadwalPenyetoran extends AppCompatActivity {

    private RecyclerView rvJadwal;
    ArrayList<Jadwal> jadwalArrayList ;
    AdapterJadwal adapterJadwal;
    DatabaseReference databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_penyetoran);
        initView();
        jadwalArrayList=new ArrayList<>();
        rvJadwal.setLayoutManager(new LinearLayoutManager(JadwalPenyetoran.this));
        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("jadwal");
        SweetAlertDialog pDialog = new SweetAlertDialog(JadwalPenyetoran.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jadwalArrayList.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        Jadwal l=npsnapshot.getValue(Jadwal.class);
                        jadwalArrayList.add(l);
                    }
                    pDialog.dismissWithAnimation();
                    adapterJadwal= new AdapterJadwal(JadwalPenyetoran.this,jadwalArrayList);
                    adapterJadwal.notifyDataSetChanged();
                    rvJadwal.setAdapter(adapterJadwal);
                }else {
                    pDialog.dismissWithAnimation();
                    new SweetAlertDialog(JadwalPenyetoran.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Perhatian")
                            .setContentText("Tidak ada data !")
                            .show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void initView() {
        rvJadwal = (RecyclerView) findViewById(R.id.rv_jadwal);
    }



}