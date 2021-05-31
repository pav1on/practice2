package ru.mirea.kuzmin.independentwork;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Button btn;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(MainActivity.this);
                progress.setMax(100);
                progress.setMessage("Its Loading....");
                progress.setTitle("Download");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progress.getProgress() <= progress.getMax()) {
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if (progress.getProgress() == progress.getMax()) {
                                    progress.dismiss();
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }

            @SuppressLint("HandlerLeak")
            Handler handle = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progress.incrementProgressBy(1);
                }
            };


        });

    }
}

