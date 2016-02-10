package com.developer.artisla.lessonsecondweek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private Button buttonBt;

    @Override
    public void onClick(View view) {
        DownloadFilesTask downloadFilesTask = new DownloadFilesTask(this);
        downloadFilesTask.execute("http://gtspirit.com/wp-content/uploads/2016/02/BMW-X6-M50d-Hamann-1.jpg","http://www.2016carmodels.com/wp-content/uploads/2015/03/2016-Mercedes-ML-front.jpg");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonBt = (Button) findViewById(R.id.buttonBt);
       imageView = (ImageView) findViewById(R.id.imageView);

        buttonBt.setOnClickListener(this);

        //Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);

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
