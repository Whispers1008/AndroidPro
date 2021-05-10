package com.example.getwebpagesourcecode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    private EditText mWebSite;
    private TextView mWebSource;
    private Spinner spinner;
    private String URL;
    private String[] choiceArray = {"http://","https://"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebSite = (EditText) findViewById(R.id.web_site);
        mWebSource = (TextView) findViewById(R.id.web_source);
        initSpinner();
    }

    private void initSpinner(){
        spinner = (Spinner) findViewById(R.id.choose_http_or_https);
        // 建立数据源

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choiceArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                Toast.makeText(MainActivity.this,"您选择的是："+choiceArray[pos]+"id: "+id,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO
            }
        });
    }

    public void searchWebSoruce(View view) {
        int selected_pos = spinner.getSelectedItemPosition();
        URL = choiceArray[selected_pos] + mWebSite.getText().toString();
//        Toast.makeText(MainActivity.this,"当前要访问的URL是： "+URL,Toast.LENGTH_SHORT).show();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && URL.length() != 0) {
            new FetchWebSource(mWebSource).execute(URL);
            mWebSource.setText("Loading...");
        } else {
            if (URL.length() == 0) {
                mWebSource.setText("Please enter a search term");
            } else {
                mWebSource.setText("Please check your network connection and try again.");
            }
        }
    }
}