package com.example.bff;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.entities.Business;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.UUID;

public class editBusiness extends AppCompatActivity {

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
    private FirebaseUser mAuth;


    private FirebaseAuth fAuth;
    private ImageView profilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    DatabaseReference reference; //for the database that save already


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_business_activity);

        //user can change
        reference = FirebaseDatabase.getInstance().getReference("Business");
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        fAuth =  FirebaseAuth.getInstance();
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

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        fAuth =  FirebaseAuth.getInstance();

        StorageReference profileRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
            }
        });

        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                name.setText(user.getName());
                businessName.setText(user.getUsername());
                email.setText(user.getEmail());
                id.setText(user.getBusinessID());
                phone.setText(user.getPhone());
                city.setText(user.getCity());
                street.setText(user.getStreet());
                house_number.setText(user.getHouseNumber());
                type.setText(user.getType());
                time.setText(user.getTime());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(name.getText().toString(),businessName.getText().toString(),id.getText().toString(),phone.getText().toString(),city.getText().toString(),street.getText().toString(),
                        house_number.getText().toString(),type.getText().toString(), time.getText().toString());
                startActivity(new Intent(editBusiness.this, businessActivity.class));
            }
        });

        //for add Images
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) ;
                startActivityForResult(openGalleryIntent,1000);
            }
        });


    }


    private boolean update(String newName,String newBusinessName, String newId, String newPhone, String newCity, String newStreet, String newHouseNumber, String newType, String newTime) {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                String name = user.getName();
                String businessName = user.getUsername();
                String ID = user.getId();
                String phone = user.getPhone();
                String city = user.getCity();
                String street = user.getStreet();
                String houseNumber = user.getHouseNumber();
                String type = user.getType();
                String time = user.getTime();
                if (!name.equals(newName)) {
                    reference.child(mAuth.getUid()).child("name").setValue(newName);
                }
                if (!businessName.equals(newBusinessName)) {
                    reference.child(mAuth.getUid()).child("username").setValue(newBusinessName);
                }
                if (!ID.equals(newId)) {
                    reference.child(mAuth.getUid()).child("id").setValue(newId);
                }
                if (!phone.equals(newPhone)) {
                    reference.child(mAuth.getUid()).child("phone").setValue(newPhone);
                }
                if (!city.equals(newCity)) {
                    reference.child(mAuth.getUid()).child("city").setValue(newCity);
                }
                if (!street.equals(newStreet)) {
                    reference.child(mAuth.getUid()).child("street").setValue(newStreet);
                }
                if (!houseNumber.equals(newHouseNumber)) {
                    reference.child(mAuth.getUid()).child("houseNumber").setValue(newHouseNumber);
                }
                if (!type.equals(newType)) {
                    reference.child(mAuth.getUid()).child("type").setValue(newType);
                }
                if (!time.equals(newTime)) {
                    reference.child(mAuth.getUid()).child("time").setValue(newTime);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return false;
    }


    //for add Image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000 && resultCode==RESULT_OK ){
            imageUri = data.getData();
            //profilePic.setImageURI(imageUri);
            uploadPicture(imageUri);
        }
    }

    private void uploadPicture(Uri imageUri) {
        //uplaod image to firebase storage
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();


        final  String randomKey = UUID.randomUUID().toString();


        StorageReference riversRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
        // Register observers to listen for when the download is done or if it fails
        riversRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                pd.dismiss();
                Toast.makeText(editBusiness.this, "Failed To Upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilePic);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                pd.setMessage("Percentage: " + (int)progressPercent + "%");
            }
        });


    }


}
