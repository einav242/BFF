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

import com.example.bff.entities.User;
import com.example.bff.view.animalActivityView;
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

public class edit_User_Profile extends AppCompatActivity {

    EditText edFullName;
    EditText edAnimalName;
    EditText editPhone;
    TextView edEmail;
    private Button update;
    private FirebaseUser mAuth;
    DatabaseReference reference; //for the database that save already
    private FirebaseAuth fAuth;


    private ImageView profilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        //user can change
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        fAuth =  FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        edFullName = findViewById(R.id.edit_user_fullName);
        edAnimalName = findViewById(R.id.edit_user_AnimalName);
        edEmail = findViewById(R.id.edit_user_Email);
        editPhone = findViewById(R.id.editTxtPhone);
        update = findViewById(R.id.edit_user_Update);
        profilePic = findViewById(R.id.edit_user_image);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        StorageReference profileRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
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


        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                edFullName.setText(user.getName());
                edAnimalName.setText(user.getUsername());
                edEmail.setText(user.getEmail());
                editPhone.setText(user.getPhone());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(edFullName.getText().toString(), edAnimalName.getText().toString(), editPhone.getText().toString());
                startActivity(new Intent(edit_User_Profile.this, animalActivityView.class));
            }
        });
    }

    public void update(String newFullName, String newAnimalName, String newPhone)
    {
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String fullName = user.getName();
                String animalName = user.getUsername();
                String phone = user.getPhone();
                if (!fullName.equals(newFullName)) {
                    reference.child(mAuth.getUid()).child("name").setValue(newFullName);
                }
                if (!animalName.equals(newAnimalName)) {
                    reference.child(mAuth.getUid()).child("username").setValue(newAnimalName);
                }
                if (!phone.equals(newPhone)) {
                    reference.child(mAuth.getUid()).child("phone").setValue(newPhone);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                Toast.makeText(edit_User_Profile.this, "Failed To Upload", Toast.LENGTH_SHORT).show();
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