package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Patterns;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import com.example.bff.R;

public class forget_password extends AppCompatActivity {

    EditText _txtEmail;
    Button _send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        _txtEmail=findViewById(R.id.et_email);
        _send=findViewById(R.id.bt_forget);
        _send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username="bffdogandcat@gmail.com";
                final String password="bffnivbar";
                String messageToSend="hi";
                Properties props=new Properties();
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port",587);
                Session session=Session.getInstance(props,new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username,password);
                    }
                });
                try {
                    Message msg=new MimeMessage(session);
                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(_txtEmail.getText().toString()));
                    msg.setSubject("sending email without opening gmail app");
                    msg.setText(messageToSend);
                    Transport.send(msg);
                    System.out.println("hiiiiii");
                    Toast.makeText(getApplicationContext(),"email send successfully",Toast.LENGTH_LONG).show();

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

}