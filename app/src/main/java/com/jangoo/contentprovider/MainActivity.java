package com.jangoo.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText e1, e2;
    ContentValues values = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.eName);
        e2 = (EditText) findViewById(R.id.eCity);
    }

    public void doSaveNow(View view) {
        values.put("e_name", e1.getText().toString());
        values.put("e_city", e2.getText().toString());
        Uri uri = getContentResolver().insert(DetailProvider.CONTENT_URI,
                values);
        Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void doLoadNow(View view) {
        StringBuilder SB;
        try (Cursor cr = getContentResolver().query(DetailProvider.CONTENT_URI, null, null, null, "id")) {
            SB = new StringBuilder();
            while (cr.moveToNext()) {
                int id = cr.getInt(0);
                String s1 = cr.getString(1);
                String s2 = cr.getString(2);
                SB.append(id).append(" ").append(s1).append(" ").append(s2).append("\n");
            }
        }
        Toast.makeText(this, SB.toString(), Toast.LENGTH_LONG).show();
    }
}