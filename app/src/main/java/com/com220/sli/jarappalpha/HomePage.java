package com.com220.sli.jarappalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity
{
    Button createNewMemoryButton;
    Button createNewJarButton;
    Button jarDetailButton;

    Button confirmPageButton; //DRIVER CODE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        createNewMemoryButton = (Button)findViewById(R.id.create_new_memory_button);
        createNewMemoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                Intent createNewMemoryPage = new Intent(getApplicationContext(),CreateNewMemory.class);
                startActivity(createNewMemoryPage);
            }
        });

        createNewJarButton = (Button)findViewById(R.id.create_new_jar_button);
        createNewJarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                Intent createNewMemoryPage = new Intent(getApplicationContext(),CreateNewJar.class);
                startActivity(createNewMemoryPage);
            }
        });

        jarDetailButton = (Button)findViewById(R.id.jar_detail_button);
        jarDetailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent createNewMemoryPage = new Intent(getApplicationContext(), JarDetail.class);
                startActivity(createNewMemoryPage);
            }
        });

        confirmPageButton = (Button)findViewById(R.id.confirm_page_button);
        confirmPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent confirmPage = new Intent(getApplicationContext(), ConfirmPage.class);
                startActivity(confirmPage);
            }
        });

        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
        */
    }

    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
        if(fragment != null)
        {
            fragment.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
