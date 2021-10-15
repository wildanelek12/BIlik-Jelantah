package com.codes.bilikjelantah;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class DetailArtikelActivity extends AppCompatActivity {

    private PDFView pdfArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        initView();
        pdfArtikel.fromAsset("pdf_artikel.pdf").load();
    }

    private void initView() {
        pdfArtikel = (PDFView) findViewById(R.id.pdf_artikel);
    }
}