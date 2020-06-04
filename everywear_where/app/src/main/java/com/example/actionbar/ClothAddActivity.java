package com.example.actionbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ClothAddActivity extends AppCompatActivity {
    ImageButton btnCamera;
     Button  btnUpload;
     Button btnInception;
    ImageView img;

    //request code
    final int CAMERA_REQUEST_CODE = 1;


    // 찬종
    final String TAG = getClass().getSimpleName();
    final static int TAKE_PICTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    private String id;
    String hanger;

    String mCurrentPhotoPath;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    //이미지 base64
    String profileImageBase64="";

    //통신
    private Retrofit retrofit;
    ImageService imageService;

    ArrayList<PredictionItem> alItem = new ArrayList<>();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloth_new);

        // 6.0 마쉬멜로우 이상일 경우에는 권한 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(ClothAddActivity.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent intent = getIntent();
        hanger = intent.getStringExtra("hanger");

        //홈버튼
        Button home = (Button)findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        HomeActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        //뒤로가기
        Button back = (Button) findViewById(R.id.reversed_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        HomeActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });


        btnCamera = (ImageButton)findViewById(R.id.btnCamera);
        btnUpload = (Button)findViewById(R.id.btnUpload);
        img = (ImageView)findViewById(R.id.img);
        btnInception = (Button) findViewById(R.id.btnInception);

        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();


            }
        });

        //분석하기 버튼을 누르면
        btnInception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagetoBase();
                postText(ClothAddActivity.this,profileImageBase64);
            }
        });

        //파이어베이스에 업로드
        btnUpload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                id ="cloth";

                String filename = id + "_" +hanger;
                FirebaseStorage storage = FirebaseStorage.getInstance("gs://everywear-794cd.appspot.com");
                StorageReference storageRef = storage.getReferenceFromUrl("gs://everywear-794cd.appspot.com").child("WearPhotos/" + filename);
                UploadTask uploadTask;

                Bitmap bitmap = null;
                if (img.getDrawable() != null) {
                    bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 65, outStream);
                    byte[] image = outStream.toByteArray();
                    Resources res= getResources(); //추가
                    profileImageBase64 = Base64.encodeToString(image, 0);

                    uploadTask = storageRef.putBytes(image);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Log.v("알림", "사진 업로드 실패");
                            exception.printStackTrace();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //카테고리 선택으로 이동
                            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                            Toast.makeText(getApplication(),"등록 완료",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();

                        }
                    });
                }

            }
        });

    }
    //image to base
    public void imagetoBase() {


        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Resources res = getResources();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        byte[] image = outStream.toByteArray();
        profileImageBase64 = Base64.encodeToString(image, 0);
    }
    //통신
    public void postText(final Context context, String base64){
        String url = "http://116.37.22.219:8501";

        retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(url).build();

        imageService = retrofit.create(ImageService.class);


        JsonObject jsonObject =new JsonObject();
        jsonObject.addProperty("signature_name","predict_images");
        final JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject1 = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("b64",base64);
        jsonObject1.add("images",jsonObject2);
        jsonArray.add(jsonObject1);


        jsonObject.add("instances",jsonArray);
        Log.e("json",jsonObject.toString());


        //Call<ResponseBody> call = blackboxService.upload("predict_images","[{\"images\": {\"b64\":\""+base64+"\"}}]");

        Call<JsonObject> call = imageService.upload(jsonObject);
        Log.e("uploadtest",base64);
        //    Call<ResponseBody> call = blackboxService.upload(map);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {

                try {
                    Log.d("Upload", "success" +  " " +response.body().toString());
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
                    JsonArray jsonArray1 = response.body().getAsJsonArray("predictions");
                    double item1 = jsonArray1.get(0).getAsJsonArray().get(0).getAsDouble();
                    double item2 = jsonArray1.get(0).getAsJsonArray().get(1).getAsDouble();
                    double item3 = jsonArray1.get(0).getAsJsonArray().get(2).getAsDouble();
                    alItem.add(new PredictionItem(item1,item2,item3));
                    Log.e("data",item1+" "+item2+" "+item3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                Toast.makeText(context, "다시 시도해주세요", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        try {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO: {
                    if (resultCode == RESULT_OK) {
                        File file = new File(mCurrentPhotoPath);
                        Bitmap bitmap = MediaStore.Images.Media
                                .getBitmap(getContentResolver(), Uri.fromFile(file));
                        if (bitmap != null) {
                            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                    ExifInterface.ORIENTATION_UNDEFINED);

                            Bitmap rotatedBitmap = null;
                            switch(orientation) {

                                case ExifInterface.ORIENTATION_ROTATE_90:
                                    rotatedBitmap = rotateImage(bitmap, 90);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_180:
                                    rotatedBitmap = rotateImage(bitmap, 180);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_270:
                                    rotatedBitmap = rotateImage(bitmap, 270);
                                    break;

                                case ExifInterface.ORIENTATION_NORMAL:
                                default:
                                    rotatedBitmap = bitmap;
                            }

                            img.setImageBitmap(rotatedBitmap);
                        }
                    }
                    break;
                }
            }

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    // 2번쨰
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.actionbar.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }


//뒤로 ㄴㄴ
public void onBackPressed() {
    //super.onBackPressed();
}
}



