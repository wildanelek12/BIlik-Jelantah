package com.codes.bilikjelantah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class KonfirmasiSetor extends AppCompatActivity {

    private Button btnLanjutSetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_setor);
        initView();
        btnLanjutSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KonfirmasiSetor.this,SuksesSetorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnLanjutSetor = (Button) findViewById(R.id.btn_lanjut_setor);
    }
}