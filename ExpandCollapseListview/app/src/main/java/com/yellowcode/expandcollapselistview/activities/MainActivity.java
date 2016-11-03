package com.yellowcode.expandcollapselistview.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.yellowcode.expandcollapselistview.R;
import com.yellowcode.expandcollapselistview.adapters.PhoneListAdapter;
import com.yellowcode.expandcollapselistview.models.MainContentModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView lvPhones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPhones = (ExpandableListView) findViewById(R.id.phone_list);

        new LoadContentAsync().execute();
    }

    class LoadContentAsync extends AsyncTask<Void, Void, MainContentModel> {

        @Override
        protected MainContentModel doInBackground(Void... voids) {
            Gson gson = new Gson();
            MainContentModel mainContentModel = null;

            try {
                InputStream is = getAssets().open("phones.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                synchronized (this) {
                    mainContentModel = gson.fromJson(reader, MainContentModel.class);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return mainContentModel;
        }

        @Override
        protected void onPostExecute(MainContentModel mainContentModel) {
            super.onPostExecute(mainContentModel);

            PhoneListAdapter phoneListAdapter = new PhoneListAdapter(MainActivity.this, mainContentModel.getCompanies());
            lvPhones.setAdapter(phoneListAdapter);
        }
    }
}
