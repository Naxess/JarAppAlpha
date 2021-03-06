package com.com220.sli.jarappalpha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmPage extends AppCompatActivity implements View.OnClickListener
{
    private static final int RESULT_LOAD_IMAGE = 1;
    public static final int CAMERA_REQUEST = 10;
    ImageView uploadImage;
    //ImageView downloadedImage;
    Button uploadButton;
    //Button downloadButton;
    EditText nameImage;
    //EditText findImage;
    Uri selectedImage = null;
    TextView dateText;
    Button btnTakePhoto;

    EditText description;

    Memory mem = new Memory(1, "name1", "address1");
    MemoryManager mb = new MemoryManager(this);
    //mb.addMemory(mem);    //ISSUES GETTING THE DATABASE TO WORK

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        uploadImage = (ImageView)findViewById(R.id.image_to_upload);
        //downloadedImage = (ImageView)findViewById(R.id.downloaded_image);
        uploadButton = (Button)findViewById(R.id.upload_image_button);
        //downloadButton = (Button)findViewById(R.id.download_image_button);
        nameImage = (EditText)findViewById(R.id.image_name);
        //findImage = (EditText)findViewById(R.id.image_name2);

        uploadImage.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
        //downloadButton.setOnClickListener(this);

        btnTakePhoto = (Button)findViewById(R.id.take_photo);

        description = (EditText)findViewById(R.id.editText2);
        dateText = (TextView)findViewById(R.id.date_selected);

        String dateString = getIntent().getStringExtra("date");
        String desc = getIntent().getStringExtra("description");

        try
        {
            dateText.setText(dateString);
            description.setText(desc);
        }
        catch(NullPointerException e)
        {
            description.setText("NullPointerException has occurred.");
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.image_to_upload:
            {
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, RESULT_LOAD_IMAGE);
                break;
            }
            case R.id.upload_image_button:
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                if(selectedImage == null)
                {
                    uploadButton.setText("No image selected.");
                    break;
                }
                else
                {
                    uploadButton.setText("Upload Memory");
                }
                sendIntent.putExtra(Intent.EXTRA_STREAM, selectedImage);
                sendIntent.setType("image/jpeg");
                startActivity(sendIntent);
                break;
            }
            /*
            case R.id.take_photo:
            {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 10);
            }
            */
            /*
            case R.id.download_image_button:
            {
                break;
            }
            */
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            selectedImage = data.getData();
            uploadImage.setImageURI(selectedImage);
        }
        else if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null)
        {
            selectedImage = data.getData();
            uploadImage.setImageURI(selectedImage);
        }
    }

    public void btnTakePhotoClicked(View v)
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
}
