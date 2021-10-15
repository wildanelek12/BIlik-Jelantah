package com.codes.bilikjelantah;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.bilikjelantah.Adapter.AdapterRiwayatSetorMinyak;
import com.codes.bilikjelantah.Model.SetorMinyak;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RiwayatPenyetoran extends AppCompatActivity {

    private RecyclerView rvRiwayatSetor;
    ArrayList<SetorMinyak> setorMinyakArrayList ;
    AdapterRiwayatSetorMinyak adapterRiwayatSetorMinyak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_penyetoran);
        initView();
        setorMinyakArrayList=new ArrayList<>();
        rvRiwayatSetor.setHasFixedSize(true);
        rvRiwayatSetor.setLayoutManager(new LinearLayoutManager(this));

        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("setor").child(FirebaseAuth.getInstance().getCurrentUser().getUid());    reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setorMinyakArrayList.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        com.codes.bilikjelantah.Model.SetorMinyak l=npsnapshot.getValue(com.codes.bilikjelantah.Model.SetorMinyak.class);
                        setorMinyakArrayList.add(l);
                    }
                    adapterRiwayatSetorMinyak=new AdapterRiwayatSetorMinyak(RiwayatPenyetoran.this,setorMinyakArrayList);
                    adapterRiwayatSetorMinyak.notifyDataSetChanged();
                    rvRiwayatSetor.setAdapter(adapterRiwayatSetorMinyak);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void initView() {
        rvRiwayatSetor = (RecyclerView) findViewById(R.id.rv_riwayat_setor);
    }
}