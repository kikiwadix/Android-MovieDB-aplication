package com.example.juanpabloss.practica2trimestremoviedb.Resto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by kiki on 19/01/2017.
 */

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    Bitmap mIcon = null;

    public ImageDownloader(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        try {
            InputStream in = new URL(url).openStream();
            this.mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return mIcon;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }


}