package com.xg.mycustomdatepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPickerDailog dataPickerDailog = new DataPickerDailog(MainActivity.this, new DataPickerDailog.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String sex) {
                        Toast.makeText(MainActivity.this,sex,Toast.LENGTH_SHORT).show();
                    }
                });

                dataPickerDailog.show();
            }
        });

    }
}
