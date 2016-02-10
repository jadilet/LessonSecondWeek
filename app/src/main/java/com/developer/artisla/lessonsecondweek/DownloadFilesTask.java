package com.developer.artisla.lessonsecondweek;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by artisla on 2/8/16.
 */
public class DownloadFilesTask extends AsyncTask<String, Integer, Long> {

    private ProgressDialog mProgressDialog;
    private Context context;
    private int totalFile, currentFile;

    public DownloadFilesTask(Context context) {
        this.context = context;
        mProgressDialog = new ProgressDialog(context);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        currentFile = 0;
        mProgressDialog.setMessage("Downloading!!!");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();
    }

    @Override
    protected Long doInBackground(String... args) {
        totalFile = args.length;

        try {
            for (String arg : args) {
                download(arg, currentFile);
                currentFile++;
                Log.d("Argument: ", arg);
            }
        } catch (Exception e) {

        }
        return null;

    }

    public void download(String urlPath, int currentFile) throws Exception {
        int count;

        URL url = new URL(urlPath);
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        String fileName = "image000" + String.valueOf(currentFile) + ".jpg";
        int lengthOfFile = urlConnection.getContentLength();
        String PATH = Environment.getExternalStorageDirectory() + "/Artisla/Images/";
        File file = new File(PATH);

        if (!file.exists()) {
            file.mkdirs();
        }

        InputStream inputStream = new BufferedInputStream(url.openStream());
        OutputStream outputStream = new FileOutputStream(PATH + fileName);
        byte data[] = new byte[1024];

        long total = 0;

        while ((count = inputStream.read(data)) != -1) {
            total += count;
            publishProgress((int) (total * 100 / lengthOfFile));
            outputStream.write(data, 0, count);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    protected void onProgressUpdate(Integer... progress) {
        mProgressDialog.setProgress(progress[0]);
        mProgressDialog.setMessage("Loading " + (currentFile + 1) + "/" + totalFile);

        if (currentFile == totalFile) {
            mProgressDialog.dismiss();
        }
        if (mProgressDialog.getProgress() == mProgressDialog.getMax()) {
            mProgressDialog.setProgress(0);
            //Toast.makeText(context, "File Downloaded", Toast.LENGTH_SHORT).show();
        }


    }

    protected void onPostExecute(Long result) {

    }
}
