package com.example.suryasolanki.cardviewjson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by surya.solanki on 18-11-2016.
 */

public class MainActivity extends AppCompatActivity {

    private TextView textStatus;
    private Button btnStatus;

    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            if(bundle!=null){
                String  stringbundle=bundle.getString(MyIntentService.FILEPAH);

                int resultCode=bundle.getInt(MyIntentService.RESULT);

                if(resultCode==RESULT_OK){
                    Toast.makeText(MainActivity.this,"Download complete Download URI: "+ stringbundle,Toast.LENGTH_LONG).show();
                    textStatus.setText("Download Done");
                }
                else{
                    Toast.makeText(MainActivity.this,"DownLoad Fialed Download URI: "+stringbundle,Toast.LENGTH_LONG).show();
                    textStatus.setText("DownLoad Failed");
                }
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        textStatus=(TextView) findViewById(R.id.txtStatus);

        btnStatus=(Button) findViewById(R.id.btnService);
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MyIntentService.class);

                intent.putExtra(MyIntentService.FILENAME,"128483.1.jpg");
                intent.putExtra(MyIntentService.URL,"http://p.imgci.com/db/PICTURES/CMS/128400/128483.1.jpg");

                startService(intent);
                textStatus.setText("Service Started");
            }
        });
    }
}
