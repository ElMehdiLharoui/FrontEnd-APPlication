package com.example.etudiant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.api_consumer_etu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Etudiant> EtuList = new ArrayList<>();
    String url = "http://172.17.36.185:5001/api/Etudiant";
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        new classApi().execute(url);

    }

    public class classApi extends AsyncTask<String ,Void, List<Etudiant>> {



        public String ConvertStreamIntoStr (InputStream is) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = null;
            String str;
            br = new BufferedReader (new InputStreamReader(is));
            while ((str = br.readLine()) != null)
                sb.append(str);
            return sb.toString();
        }


        @Override
        protected List<Etudiant> doInBackground(String... strings) {

            String urlChaine = strings[0];
            String jsonStr;

            try {

                URL url = new URL(urlChaine);
                HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
                jsonStr = ConvertStreamIntoStr(cnx.getInputStream());



                JSONArray EtuArray = new JSONArray(jsonStr);

                for(int index = 0; index < EtuArray.length(); index++)
                {
                    JSONObject EtuObject = EtuArray.getJSONObject(index);
                    Etudiant Etu = new Etudiant(
                            EtuObject.getString("nom"),
                            EtuObject.getString("prenom"),
                            EtuObject.getString("age")

                    );
                    EtuList.add(Etu);
                }


            }catch(MalformedURLException | JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return EtuList;
        }

        @Override
        protected void onPostExecute(List<Etudiant> result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();

            myAdapter = new Adapter(MainActivity.this,EtuList);
            recyclerView.setAdapter(myAdapter);
        }


    }

}