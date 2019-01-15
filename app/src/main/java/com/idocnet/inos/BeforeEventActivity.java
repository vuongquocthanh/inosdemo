package com.idocnet.inos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class BeforeEventActivity extends AppCompatActivity {

    private NumberPicker typeTime;
    private EditText edNumberPicker;
    private TextView tvTime, tvTimePicker;
    private TimePicker timePicker;
    private ImageView imgBack;

    public static final String TimeNumber = "TIME_NUMBER";
    public static final String TimeType = "TIME_TYPE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_event);
        initViews();
        String[] typeTimes = new String[]{"Phút", "Giờ", "Ngày", "Tuần", "Tháng"};
        typeTime.setMinValue(0);
        typeTime.setMaxValue(typeTimes.length-1);
        typeTime.setDisplayedValues(typeTimes);
        typeTime.setOutlineSpotShadowColor(getResources().getColor(R.color.colorAccent));
        edNumberPicker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvTime.setText(edNumberPicker.getText().toString().trim()+" "+typeTimes[typeTime.getValue()]);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        typeTime.setOnValueChangedListener((picker, oldVal, newVal) -> tvTime.setText(edNumberPicker.getText().toString().trim()+" "+typeTimes[typeTime.getValue()]));

        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> tvTimePicker.setText(hourOfDay+":"+minute));

        imgBack.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra(TimeNumber, edNumberPicker.getText().toString().trim());
            data.putExtra(TimeType, typeTimes[typeTime.getValue()]);
            setResult(Activity.RESULT_OK, data);
            finish();
        });
    }

    private void initViews(){
        typeTime = findViewById(R.id.typeTime);
        edNumberPicker = findViewById(R.id.edNumberPicker);
        tvTime = findViewById(R.id.tvTime);
        timePicker = findViewById(R.id.timePicker);
        tvTimePicker = findViewById(R.id.tvTimePicker);
        imgBack = findViewById(R.id.imgBack);
    }
}
