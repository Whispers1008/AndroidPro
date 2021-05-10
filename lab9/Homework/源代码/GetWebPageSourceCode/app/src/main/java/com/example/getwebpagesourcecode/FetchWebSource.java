package com.example.getwebpagesourcecode;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.lang.ref.WeakReference;

public class FetchWebSource extends AsyncTask<String,Void,String> {
    private WeakReference<TextView> mWebSource;
    public FetchWebSource(TextView webSourceText){
        this.mWebSource = new WeakReference<>(webSourceText);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            if(s!=null){
                mWebSource.get().setText(s);
            }else{
                mWebSource.get().setText("Wrong URL");
            }
        }catch (Exception e){
            mWebSource.get().setText("Wrong URL");
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getWebSource(strings[0]);
    }
}