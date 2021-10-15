package com.codes.bilikjelantah.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.bilikjelantah.Adapter.AdapterRiwayatBonus;
import com.codes.bilikjelantah.Adapter.AdapterRiwayatTarik;
import com.codes.bilikjelantah.Model.Bonus;
import com.codes.bilikjelantah.Model.Penarikan;
import com.codes.bilikjelantah.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRiwayatBonus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRiwayatBonus extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvRiwayatBonus;
    AdapterRiwayatBonus adapterRiwayatBonus;
    ArrayList<Bonus>bonusArrayList;

    public FragmentRiwayatBonus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRiwayatBonus.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRiwayatBonus newInstance(String param1, String param2) {
        FragmentRiwayatBonus fragment = new FragmentRiwayatBonus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_riwayat_bonus, container, false);
        initView(root);
        bonusArrayList=new ArrayList<>();
        rvRiwayatBonus.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("bonus").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bonusArrayList.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        Bonus l=npsnapshot.getValue(Bonus.class);
                        bonusArrayList.add(l);
                    }
                    adapterRiwayatBonus=new AdapterRiwayatBonus(getContext(),bonusArrayList);
                    adapterRiwayatBonus.notifyDataSetChanged();
                    rvRiwayatBonus.setAdapter(adapterRiwayatBonus);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return root;
    }

    private void initView(View view) {
        rvRiwayatBonus = (RecyclerView) view.findViewById(R.id.rv_riwayat_bonus);
    }
}