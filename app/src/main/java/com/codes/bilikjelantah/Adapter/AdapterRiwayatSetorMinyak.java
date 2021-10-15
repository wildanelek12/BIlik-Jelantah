package com.codes.bilikjelantah.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.bilikjelantah.Model.SetorMinyak;
import com.codes.bilikjelantah.R;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterRiwayatSetorMinyak extends RecyclerView.Adapter<AdapterRiwayatSetorMinyak.SetorMinyakViewHolder> {

    ArrayList<SetorMinyak> setorMinyakArrayList;
    Context context;


    public AdapterRiwayatSetorMinyak(Context context, ArrayList<SetorMinyak> setorMinyakArrayList) {
        this.setorMinyakArrayList = setorMinyakArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public SetorMinyakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_setor, parent, false);
        SetorMinyakViewHolder holder = new SetorMinyakViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SetorMinyakViewHolder holder, int position) {
        SetorMinyak setorMinyak = setorMinyakArrayList.get(position);
        holder.tvJumlahLiter.setText(setorMinyak.getJumlah());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvBonusSaldo.setText(formatRupiah.format(setorMinyak.getTotal_saldo()));
        holder.tvKodeSetor.setText(setorMinyak.getCode());
        holder.tvTglSetor.setText(setorMinyak.getTanggal());
    }

    @Override
    public int getItemCount() {
        return setorMinyakArrayList.size();
    }



    public class SetorMinyakViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJumlahLiter;
        private TextView tvBonusSaldo;
        private TextView tvTglSetor;
        private TextView tvKodeSetor;
        public SetorMinyakViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJumlahLiter = (TextView)itemView.findViewById(R.id.tv_jumlah_liter);
            tvBonusSaldo = (TextView)itemView.findViewById(R.id.tv_bonus_saldo);
            tvTglSetor = (TextView)itemView.findViewById(R.id.tv_tgl_setor);
            tvKodeSetor = (TextView)itemView.findViewById(R.id.tv_kode_setor);
        }
    }
}
