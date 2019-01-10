package com.example.bobby.afinal;

import android.app.Notification;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.bobby.afinal.NotificationUtils.ANDROID_CHANNEL_ID;

public class SignUpActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID = 1;
    DatabaseHelper db;
    EditText edtRegUser,edtRegPass,edtRegPassConf;
    Button btRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DatabaseHelper(this);
        edtRegUser = (EditText)findViewById(R.id.edtSignUpUser);
        edtRegPass = (EditText)findViewById(R.id.edtSignUpPass);
        edtRegPassConf = (EditText)findViewById(R.id.edtSignUpPassConf);
        btRegister = (Button)findViewById(R.id.btnRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String s1 = edtRegUser.getText().toString();
              String s2 = edtRegPass.getText().toString();
              String s3 = edtRegPassConf.getText().toString();
              if(s1.equals("")||s2.equals("")||s3.equals("")){
                  Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
              }
              else{
                  if(s2.equals(s3)){
                      Boolean chkmail = db.chkemail(s1);
                      if(chkmail==true){
                          Boolean insert = db.insert(s1,s2);
                          if(insert==true) {
                              Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                              Intent a = new Intent(SignUpActivity.this, Login.class);
                              startActivity(a);
                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                                  showNotifOreo();
                              else showNotifDefault();
                          }
                              finish();
                          }
                      } else{
                            Toast.makeText(getApplicationContext(),"Password Do Not Match", Toast.LENGTH_SHORT).show();
                      }
                  Toast.makeText(getApplicationContext(),"Email Already Exist", Toast.LENGTH_SHORT).show();
              }

              }

        });
    }

    public void showNotifDefault(){
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(SignUpActivity.this)
                .setSmallIcon(R.drawable.ic_mode_comment_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources()
                        , R.drawable.ic_mode_comment_black_24dp))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(getApplicationContext());
        notifManager.notify(NOTIFICATION_ID, notifBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotifOreo(){
        Notification.Builder notifBuilder = new Notification.Builder(SignUpActivity.this, ANDROID_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_mode_comment_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources()
                        , R.drawable.ic_mode_comment_black_24dp))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);

        NotificationUtils utils = new NotificationUtils(SignUpActivity.this);
        utils.getManager().notify(NOTIFICATION_ID, notifBuilder.build());
    }
}
