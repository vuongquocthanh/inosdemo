package com.idocnet.inos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.idocnet.inos.model.Message;
import com.idocnet.inos.view.adapter.MessengerPrivateAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MessengerPrivateActivity extends AppCompatActivity implements MessengerPrivateAdapter.Listener {

    private RelativeLayout rlToolbar;
    private Toolbar toolbarMessenger;
    private ImageView imgBack;
    private ImageView imgInfo;
    private RecyclerView rvMessenger;
    private CardView controlBar;
    private ImageView imgCamera;
    private ImageView imgFile;
    private LinearLayout lnPin;
    private TextView tvPinName, tvPinMessage;
    private RelativeLayout rlInputMessage;
    private EditText edInputMessage;
    private ImageView imgEmoji, imgCompleteEdit;
    private ImageView imgSent, imgPinClose, imgPinComplete;
    private View viewDialogMessage, viewDialogDelete, viewDialogPinMessage;
    private Dialog dialogMessage, dialogDelete, dialogPinMessage;

//    dialogMessage
    private TextView tvChinhsua;
    private TextView tvTrichdan;
    private TextView tvXoaTinNhan;
    private TextView tvHuy;
    private TextView tvGhim;

//    dialogDelete
    private TextView tvDeleteMessage;
    private TextView tvHuyMessage;

//    dialogPinMessage
    private TextView tvDialogPinMessage;
    private TextView tvDialogCancelPinMessage;

    private List<Message> listMessage = new ArrayList<>();
    MessengerPrivateAdapter adapter = new MessengerPrivateAdapter(listMessage, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_private);
        initViews();
        imgBack.setOnClickListener(v -> finish());
        imgSent.setOnClickListener(v -> {
            String message = edInputMessage.getText().toString().trim();
            if (message.equals("")){
                edInputMessage.setText("");
            }else{
                listMessage.add(0, new Message(R.drawable.avatar1, message, setTime(System.currentTimeMillis())));
                adapter.notifyDataSetChanged();
                edInputMessage.setText("");
            }
        });

        imgInfo.setOnClickListener(v -> startActivity(new Intent(MessengerPrivateActivity.this, UserInfoActivity.class)));
    }

    private void initViews(){
        rlToolbar = findViewById(R.id.rlToolbar);
        toolbarMessenger = findViewById(R.id.toolbarMessenger);
        imgBack = findViewById(R.id.imgBack);
        imgInfo = findViewById(R.id.imgInfo);
        rvMessenger = findViewById(R.id.rvMessenger);
        controlBar = findViewById(R.id.controlBar);
        imgCamera = findViewById(R.id.img_camera);
        imgFile = findViewById(R.id.img_file);
        rlInputMessage = findViewById(R.id.rlInputMessage);
        edInputMessage = findViewById(R.id.edInputMessage);
        imgEmoji = findViewById(R.id.imgEmoji);
        imgSent = findViewById(R.id.imgSent);
        rvMessenger.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lnPin = findViewById(R.id.lnPin);
        tvPinName = findViewById(R.id.tvPinName);
        tvPinMessage = findViewById(R.id.tvPinMessage);
        imgCompleteEdit = findViewById(R.id.imgCompleteEdit);
        imgPinClose = findViewById(R.id.imgPinClose);
        imgPinComplete = findViewById(R.id.imgPinComplete);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        rvMessenger.setLayoutManager(layoutManager);
    }

    private String setTime(long time){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(time);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onItemClick(int position) {
        hideKeyboard(this);
    }

    @Override
    public void onItemLongClick(int position) {
        hideKeyboard(this);
        displayDialogMessage(position);
    }

    private void displayDialogMessage(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        viewDialogMessage = inflater.inflate(R.layout.dialog_message_text, null);
        initViewDialogMessage();
        builder.setView(viewDialogMessage);
        dialogMessage = builder.create();
        Window window  = dialogMessage.getWindow();
        WindowManager.LayoutParams wlp  = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        tvGhim.setOnClickListener(v -> {
            Toast.makeText(MessengerPrivateActivity.this, "Ghim Tin Nhan", Toast.LENGTH_SHORT).show();
            if (lnPin.getVisibility() == View.VISIBLE){
                displayDialogPinMessage(position);
            }else{
                pinMessage(position);
            }
            dialogMessage.dismiss();
        });
        tvChinhsua.setOnClickListener(v -> {
            Toast.makeText(MessengerPrivateActivity.this, "Chinh sua", Toast.LENGTH_SHORT).show();
            edInputMessage.setText(listMessage.get(position).getMessage());
            imgCompleteEdit.setVisibility(View.VISIBLE);
            imgSent.setVisibility(View.GONE);
            imgCompleteEdit.setOnClickListener(v1 -> {
                listMessage.get(position).setMessage(edInputMessage.getText().toString().trim());
                adapter.notifyDataSetChanged();
                edInputMessage.setText("");
                imgSent.setVisibility(View.VISIBLE);
                imgCompleteEdit.setVisibility(View.GONE);
            });
            dialogMessage.dismiss();
        });
        tvXoaTinNhan.setOnClickListener(v -> {
            Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();
            displayDialogDelete(position);
            dialogMessage.dismiss();
        });
        tvTrichdan.setOnClickListener(v -> {
            Toast.makeText(MessengerPrivateActivity.this, "Trich dan", Toast.LENGTH_SHORT).show();
            dialogMessage.dismiss();
        });
        tvHuy.setOnClickListener(v -> dialogMessage.dismiss());
        dialogMessage.show();
    }

    private void displayDialogDelete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        viewDialogDelete = inflater.inflate(R.layout.dialog_delete_message, null);
        initViewDialogDelete();
        builder.setView(viewDialogDelete);
        dialogDelete = builder.create();
        Window window  = dialogDelete.getWindow();
        WindowManager.LayoutParams wlp  = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        tvDeleteMessage.setOnClickListener(v -> {
            Toast.makeText(MessengerPrivateActivity.this, position+"", Toast.LENGTH_SHORT).show();
            listMessage.remove(position);
            adapter.notifyDataSetChanged();
            dialogDelete.dismiss();
        });

        tvHuyMessage.setOnClickListener(v -> dialogDelete.dismiss());
        dialogDelete.show();
    }

    private void displayDialogPinMessage(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        viewDialogPinMessage = inflater.inflate(R.layout.dialog_pin_message, null);
        initViewDialogPinMessage();
        builder.setView(viewDialogPinMessage);
        dialogPinMessage = builder.create();
        Window window  = dialogPinMessage.getWindow();
        WindowManager.LayoutParams wlp  = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        tvDialogPinMessage.setOnClickListener(v -> {
            pinMessage(position);
            dialogPinMessage.dismiss();
        });

        tvDialogCancelPinMessage.setOnClickListener(v -> dialogPinMessage.dismiss());
        dialogPinMessage.show();
    }

    private void pinMessage(int position){
        lnPin.setVisibility(View.VISIBLE);
        tvPinName.setText("Cao Van Hieu");
        tvPinMessage.setText(listMessage.get(position).getMessage());
        imgPinClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnPin.setVisibility(View.GONE);
            }
        });
    }

    private void initViewDialogMessage() {
        tvGhim = viewDialogMessage.findViewById(R.id.tvGhim);
        tvChinhsua = viewDialogMessage.findViewById(R.id.tvChinhsua);
        tvXoaTinNhan = viewDialogMessage.findViewById(R.id.tvXoaTinNhan);
        tvHuy = viewDialogMessage.findViewById(R.id.tvHuy);
        tvTrichdan = viewDialogMessage.findViewById(R.id.tvTrichdan);
    }

    private void initViewDialogDelete(){
        tvDeleteMessage = viewDialogDelete.findViewById(R.id.tvDeleteMessage);
        tvHuyMessage = viewDialogDelete.findViewById(R.id.tvHuyMessage);
    }

    private void initViewDialogPinMessage(){
        tvDialogPinMessage = viewDialogPinMessage.findViewById(R.id.tvDialogPinMessage);
        tvDialogCancelPinMessage = viewDialogPinMessage.findViewById(R.id.tvDialogCancelPinMessage);

    }
}
