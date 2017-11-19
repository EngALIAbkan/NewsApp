package com.example.android.newsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.android.newsapp.databinding.ActivityMainBinding;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    NewsArrayAdapter adapter = null;
    ActivityMainBinding binding;
    List<News> data = new ArrayList<>();
    Uri uri;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Write the code for editting on the url
        uri = Uri.parse("http://content.guardianapis.com/search");
        Uri.Builder uriBulder = uri.buildUpon();
        uriBulder.appendQueryParameter("q", "Iraq");
        uriBulder.appendQueryParameter("api-key", "test");
        uriBulder.appendQueryParameter("show-tags","contributor");
        url = uriBulder.toString();

        if (isNetworkAvailable()) {

            Bundle bundle = new Bundle();
            bundle.putString("URL", url);
            if (getSupportLoaderManager().getLoader(1) != null) {
                getSupportLoaderManager().restartLoader(1, bundle, MainActivity.this);
            } else {
                getSupportLoaderManager().initLoader(1, bundle, MainActivity.this);


            }


            binding.ListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String WebUrl = data.get(i).getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    News item = (News) adapterView.getItemAtPosition(i);
                    Uri uri = Uri.parse(item.getUrl());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        // Show a message
                    }
                }
            });
            adapter = new NewsArrayAdapter(this, R.layout.list_view, data);
            binding.ListViewItems.setAdapter(adapter);

        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void UpdateUi(List<News> news) {

        adapter.setData(news);

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, url);
    }


    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        UpdateUi(news);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        adapter.clear();
    }

}

