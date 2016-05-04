package com.com220.sli.jarappalpha;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
DEVELOPER.ANDROID.com/training/sharing/send.html
 */

public class CreateNewMemory extends AppCompatActivity
{
    Button back;
    Button date;
    Button uploadPhotos;
    Button uploadVideos;
    Button backToDetails;
    EditText description;

    TextView dateText;

    int year, month, day;
    static final int DIALOG_ID = 0;

    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_memory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showDialogOnClick();

        dateText = (TextView)findViewById(R.id.date_edittext);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateText.setText(dateFormat.format(new Date())); // format: "04/30/2016"
        //dateText.setText(dateFormat.getDateInstance().format(new Date())); //Alternative format shows the date as "Apr 30, 2016"

        backToDetails = (Button)findViewById(R.id.submit_to_jar_button);
        backToDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToDetails = new Intent(getApplicationContext(), JarDetail.class);
                startActivity(backToDetails);
            }
        });

        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        uploadPhotos = (Button)findViewById(R.id.upload_photo_button);
        uploadVideos = (Button)findViewById(R.id.upload_video_button);

        uploadPhotos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM, "");
                sendIntent.setType("image/jpeg");
                startActivity(sendIntent);
                */

                /* Alternative method (https://www.youtube.com/watch?v=8nDKwtTcOUg)
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                */

                Intent openConfirmPage = new Intent(getApplicationContext(), ConfirmPage.class);
                openConfirmPage.putExtra("date", dateText.getText().toString());
                description = (EditText)findViewById(R.id.editText);
                openConfirmPage.putExtra("description", description.getText().toString());
                startActivity(openConfirmPage);
            }
        });

        uploadVideos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("audio/mpg");
                startActivity(sendIntent);
            }
        });
    }

    public void showDialogOnClick()
    {
        date = (Button)findViewById(R.id.date_button);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showDialog(DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id)
    {
        if(id == DIALOG_ID)
        {
            return new DatePickerDialog(this, datePickerListener, year, month, day);
        }
        else
        {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int yearOf, int monthOfYear, int dayOfMonth)
        {
            year = yearOf;
            month = monthOfYear + 1;
            day = dayOfMonth;
            //Toast.makeText(CreateNewMemory.this, month + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();
            dateText = (TextView)findViewById(R.id.date_edittext);
            dateText.setText(month + "/" + day + "/" + year);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            //image
        }
    }
}
