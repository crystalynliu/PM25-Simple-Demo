package com.thoughtworks.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thoughtworks.myapplication.domain.PM25;
import com.thoughtworks.myapplication.service.AirServiceClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE ="com.thoughtworks.myapplication.message";
    private EditText cityEditText;
    private TextView pm25TextView;
    private TextView errorTextView;
    private ListView pm25ListView;
    private ProgressDialog loadingDialog;
    private ArrayAdapter<String> adapter;
    private List<PM25> cityPM25Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = (EditText) findViewById(R.id.edit_view_input);
        pm25TextView = (TextView) findViewById(R.id.text_city_pm25);
        pm25ListView = (ListView) findViewById(R.id.list_position_pm25);
        errorTextView = (TextView)findViewById(R.id.error_message);

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage(getString(R.string.loading_message));

        findViewById(R.id.button_query_pm25).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQueryPM25Click();
            }
        });

        pm25ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String positionName = (String)pm25ListView.getItemAtPosition(position);
                PM25 matchPM25 = matchPositionName(positionName);
                SerializeMethod(matchPM25);
            }
        });
    }

    private void SerializeMethod(PM25 pm25){
        Intent intent = new Intent(this,DisplayDetailsActivity.class);
        Bundle pmBundle = new Bundle();
        pmBundle.putSerializable(EXTRA_MESSAGE,pm25);
        intent.putExtras(pmBundle);
        startActivity(intent);
    }

    private PM25 matchPositionName(String positionName){
        PM25 pm25 = new PM25();
        for(int i =0;i<cityPM25Data.size();i++){
            if(positionName == cityPM25Data.get(i).getPositionName()){
                pm25 = cityPM25Data.get(i);
            }
        }
        return pm25;
    }

    private void onQueryPM25Click() {
        final String city = cityEditText.getText().toString();
        if (!TextUtils.isEmpty(city)) {
            showLoading();
            AirServiceClient.getInstance().requestPM25(city, new Callback<List<PM25>>() {
                @Override
                public void onResponse(Response<List<PM25>> response, Retrofit retrofit) {
                    showSuccessScreen(response);
                }

                @Override
                public void onFailure(Throwable t) {
                    showErrorScreen();
                }
            });
        }
    }

    private void onQueryPostionPM25Click(){

    }
    private void showSuccessScreen(Response<List<PM25>> response) {
        hideLoading();
        if (response != null) {
            cityPM25Data = response.body();
            populate(response.body());
        }
    }

    private void showErrorScreen() {
        hideLoading();

        errorTextView.setText(R.string.error_message_query_pm25);
    }

    private void showLoading() {
        loadingDialog.show();
    }

    private void hideLoading() {
        loadingDialog.dismiss();
    }

    private void populate(List<PM25> data) {
        errorTextView.setText("");
        List<String> positionList = new ArrayList<String>();
        if (data != null && !data.isEmpty()) {
            pm25TextView.setText(data.get(0).getArea().toString());
            for(int i =0;i<data.size();i++){
                if(data.get(i).getPositionName() !=null&&!data.get(i).getPositionName().isEmpty()){
                    positionList.add(data.get(i).getPositionName());
                }
            }
            adapter = new ArrayAdapter<String>(this,R.layout.list_item,positionList);
            pm25ListView.setAdapter(adapter);

        }
    }
}


