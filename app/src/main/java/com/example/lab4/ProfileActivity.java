package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private static final int CHOOSE = 101;
    EditText editText;
    ImageView imageView;
    ProgressBar progressBar;
    Uri uriprofileimg;
    String profileImageUrl;
    FirebaseAuth mAuth;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        editText = findViewById(R.id.age);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progress);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });

        findViewById(R.id.save).setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                uploadImageToFirebaseStorage(bitmap);
            }
        });
    }

    private void setDisplayNameAndProfileURL(Uri profileURL) {
        FirebaseUser user = mAuth.getCurrentUser();

        String dispName = editText.getText().toString();
        if(dispName.isEmpty()){
            editText.setError("Name Required");
            editText.requestFocus();
            return;
        }

        if(user!= null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(dispName)
                    .setPhotoUri(profileURL).build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriprofileimg = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriprofileimg);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        String path = "users/" + mAuth.getCurrentUser().getUid() + "/" + System.currentTimeMillis() + ".jpg";

        final StorageReference profileimg =
                FirebaseStorage.getInstance().getReference().child(path);
        if (uriprofileimg != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileimg.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);
                    profileimg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            setDisplayNameAndProfileURL(uri);
                        }
                    });
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

        private void showImageChooser(){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE);
        }

}
