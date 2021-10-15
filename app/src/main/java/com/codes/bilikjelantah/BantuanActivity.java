package com.codes.bilikjelantah;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class BantuanActivity extends AppCompatActivity {

    private PDFView pdfBantuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
        initView();
        pdfBantuan.fromAsset("faq_bantuan.pdf")
                .load();
    }

    private void initView() {
        pdfBantuan = (PDFView) findViewById(R.id.pdf_bantuan);
    }
}