package com.example.bff.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.R;
import com.example.bff.controller.editBusinessController;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class editBusinessView extends AppCompatActivity {

    private EditText name;
    private EditText businessName;
    private TextView email;
    private EditText id;
    private EditText phone;
    private EditText city;
    private EditText street;
    private EditText house_number;
    private EditText type;
    private EditText time;
    private Button update;
    private Button edImagep;
    editBusinessController controller;
    ProgressDialog pd;
    CircleImageView profilePic;
    public Uri imageUri;

    String[] language ={"veterinary medicine","hairdressing salon","dog walker"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_business);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        AutoCompleteTextView actv = findViewById(R.id.register_BO_TypeOfBusiness);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);

        //user can change
        name = findViewById(R.id.register_BO_fullName);
        businessName = findViewById(R.id.register_BO_BusinessName);
        email = findViewById(R.id.register_BO_Email);
        id = findViewById(R.id.register_BO_ID);
        phone = findViewById(R.id.register_BO_PhoneNumber);
        city = findViewById(R.id.register_BO_City);
        street = findViewById(R.id.register_BO_Street);
        house_number = findViewById(R.id.register_BO_StreetNum);
        type = findViewById(R.id.register_BO_TypeOfBusiness);
        time = findViewById(R.id.editTextTextPersonName2);
        update = findViewById(R.id.register_BO_Register);
        profilePic = findViewById(R.id.register_BO_image);
        edImagep = findViewById(R.id.edit_userB_EditPhoto);


        pd = new ProgressDialog(this);
        controller = new editBusinessController(this);
        controller.getImageController();
        controller.getDataController();


        //for add Images
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });

        edImagep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.updateImageController(imageUri);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.updateController(name.getText().toString(),businessName.getText().toString(),id.getText().toString(),phone.getText().toString(),city.getText().toString(),street.getText().toString(),
                        house_number.getText().toString(),type.getText().toString(), time.getText().toString());

            }
        });
    }

    public void passPage(){
        startActivity(new Intent(editBusinessView.this, businessActivityView.class));
    }

    //for add Image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK  && data != null){
            if(data.getData() != null){
                imageUri = data.getData();
                controller.uploadPictureController(imageUri);
            }
        }
    }




    public void setDataView(String email ,String name, String businessName, String id ,String phone, String city,String street,
                            String house_number, String type , String time) {
        this.name.setText(name);
        this.businessName.setText(businessName);
        this.email.setText(email);
        this.id.setText(id);
        this.phone.setText(phone);
        this.city.setText(city);
        this.street.setText(street);
        this.house_number.setText(house_number);
        this.type.setText(type);
        this.time.setText(time);
    }

    public void setImegeView(Uri uri) {
        profilePic.setImageURI(uri);
        imageUri = uri;
        Picasso.get().load(uri).into(profilePic);
    }
    public void setToastView(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void setPdView(String msg){
        pd.setTitle(msg);
        pd.show();
    }
    public void pdDismissView(){
        pd.dismiss();
    }
}