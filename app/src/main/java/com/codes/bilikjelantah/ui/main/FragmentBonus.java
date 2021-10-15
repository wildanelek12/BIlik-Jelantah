package com.codes.bilikjelantah.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.codes.bilikjelantah.KonfirmasiSetor;
import com.codes.bilikjelantah.Model.Bonus;
import com.codes.bilikjelantah.Model.SetorMinyak;
import com.codes.bilikjelantah.R;
import com.codes.bilikjelantah.SuksesSetorActivity;
import com.codes.bilikjelantah.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBonus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBonus extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView btnKlaim;
    DatabaseReference databaseReference,databaseReference2,databaseReference3,databaseReference4;

    public FragmentBonus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBonus.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBonus newInstance(String param1, String param2) {
        FragmentBonus fragment = new FragmentBonus();
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
        View root = inflater.inflate(R.layout.fragment_bonus, container, false);
        initView(root);
        databaseReference = FirebaseDatabase.getInstance().getReference("bonus").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference2 = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("saldo");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("checkpoint");
        databaseReference4 = FirebaseDatabase.getInstance().getReference("bonus_riwayat");

        if (HomeFragment.jumlah_liter < HomeFragment.checkpoint){
            btnKlaim.setEnabled(false);
            btnKlaim.setImageResource(R.drawable.ic_group_klaim_off);
        }else {
            btnKlaim.setEnabled(true);
            btnKlaim.setImageResource(R.drawable.ic_group_98);
        }
        btnKlaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal =  new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
                Bonus bonus = new Bonus(getAlphaNumericString(5), HomeFragment.checkpoint,tanggal);
                databaseReference.push().setValue(bonus);
                databaseReference4.push().setValue(bonus);
                databaseReference2.setValue(HomeFragment.saldo+5000);
                databaseReference3.setValue(HomeFragment.checkpoint+25);

                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Perhatian !")
                        .setContentText("Bonus Berhasil Di Klaim ")
                        .setConfirmText("Oke ")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                              getActivity().finish();
                              sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .show();


            }
        });
        return root;
    }

    private void initView(View view) {
        btnKlaim = (ImageView) view.findViewById(R.id.btn_klaim);
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