//package com.example.bff;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.snackbar.Snackbar;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;
//
//import java.net.URI;
//import java.util.UUID;
//
//public class addImage extends AppCompatActivity {
//
//    private ImageView profilePic;
//    public Uri imageUri;
//
//    private FirebaseStorage storage;
//    private StorageReference storageReference;
//
//    private DatabaseReference mRootRef;
//    private FirebaseAuth mAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_image);
//        profilePic = findViewById(R.id.edit_user_image);
//
//        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference();
//
//        StorageReference profileRef = storageReference.child("user/"+ mAuth.getCurrentUser().getUid() +"profile.jpg");
//        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).into(profilePic);
//            }
//        });
//
//        profilePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //open gallery
//                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) ;
//                startActivityForResult(openGalleryIntent,1000);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==1000 && resultCode==RESULT_OK ){
//            imageUri = data.getData();
//            //profilePic.setImageURI(imageUri);
//            uploadPicture(imageUri);
//        }
//    }
//
//    private void uploadPicture(Uri imageUri) {
//        //uplaod image to firebase storage
//        final ProgressDialog pd = new ProgressDialog(this);
//        pd.setTitle("Uploading Image...");
//        pd.show();
//
//
//        final  String randomKey = UUID.randomUUID().toString();
//
//
//        StorageReference riversRef = storageReference.child("user/"+ mAuth.getCurrentUser().getUid() +"profile.jpg");
//        // Register observers to listen for when the download is done or if it fails
//        riversRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                pd.dismiss();
//                Toast.makeText(addImage.this, "Failed To Upload", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                pd.dismiss();
//                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        Picasso.get().load(uri).into(profilePic);
//                    }
//                });
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                pd.setMessage("Percentage: " + (int)progressPercent + "%");
//            }
//        });
//
//
//    }
//}