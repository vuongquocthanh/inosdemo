package com.idocnet.inos.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.api.model.Exception;
import com.idocnet.inos.di.component.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {
    AlertDialog detaiServerErrorDialog, warringDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getComponent(){
        return ((MyApplication) getApplicationContext()).getApplicationComponent();
    }

    protected void showDialogDetailServerError(Exception error){
        if (detaiServerErrorDialog != null && detaiServerErrorDialog.isShowing()) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewGroup = LayoutInflater.from(this).inflate(R.layout.layout_server_error,null,false);
        TextView textView = viewGroup.findViewById(R.id.tv_document);
        TextView tvMessage = viewGroup.findViewById(R.id.tv_message_error);
        tvMessage.setText(error.getErrorCode());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(error.getErrorDetail(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(error.getErrorDetail()));
        }
        builder.setView(viewGroup)
                .setPositiveButton("Ok", (dialog, which) -> {});

        detaiServerErrorDialog = builder.create();
        detaiServerErrorDialog.show();
    }

    protected void showDialogWarring(String message){
        if (warringDialog != null && warringDialog.isShowing()) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cảnh báo")
                .setMessage(message)
                .setPositiveButton("Ok", ((dialog1, which) -> {}));

        warringDialog = builder.create();
        warringDialog.show();
    }
}
