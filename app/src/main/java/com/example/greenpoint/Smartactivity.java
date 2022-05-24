package com.example.greenpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Smartactivity extends AppCompatActivity {

    Button bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartactivity);

//        bt_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gologing();
//            }
//        });
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            switch (item.getItemId()) {
//                case android.R.id.home:
//                    // Your desired class
//                    startActivity(new Intent(ThisActivity.this, NextActivity.class));
//                    break;
//            }
//            return true;
//        }


    }

//    private void gologing() {
//        Intent i = new Intent(this, Act_menuprincipal.class);
//        //BANDERAS PARA QUE NO SE CREEN ACTIVIDADES INNECESARIAS
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
//    }

}