package com.f_s_news.practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

       ProgressBar progressBar;
GridView grid;
ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
HashMap<String,String>hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

progressBar=findViewById(R.id.progressBar);
        grid=findViewById(R.id.grid);









String urii="https://fahad71.000webhostapp.com/apps/song.json";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, urii, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
progressBar.setVisibility(View.GONE);


for (int x=0;x<response.length();x++){
    try {

        JSONObject jsonObject=response.getJSONObject(x);
        String tittle=jsonObject.getString("tittle");
        String video_id=jsonObject.getString("video_id");


        hashMap=new HashMap<>();
hashMap.put("tittle",tittle);
hashMap.put("video_id",video_id);
arrayList.add(hashMap);


} catch (JSONException e) {
                        e.printStackTrace();
                    }
}

my_adapter adapter=new my_adapter();
grid.setAdapter(adapter);











            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);

            }
        });


RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
requestQueue.add(jsonArrayRequest);


    }



 private class my_adapter extends BaseAdapter{

     @Override
     public int getCount() {
         return arrayList.size();
     }

     @Override
     public Object getItem(int position) {
         return null;
     }

     @Override
     public long getItemId(int position) {
         return 0;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {

         LayoutInflater layoutInflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View my_view=layoutInflater.inflate(R.layout.last_layout,parent,false);



TextView textView=my_view.findViewById(R.id.textView);
         ImageView imageView=my_view.findViewById(R.id.imageView);

         HashMap<String,String>hashMap=arrayList.get(position);
         String tittle=hashMap.get("tittle");
         String video_id=hashMap.get("video_id");





String img_url="https://img.youtube.com/vi/"+video_id+"/0.jpg";
         textView.setText(tittle);
         Picasso.get().load(img_url)
                 .placeholder(R.drawable.ic_launcher_background)
                 .into(imageView);

         return my_view;




     }
 }











}