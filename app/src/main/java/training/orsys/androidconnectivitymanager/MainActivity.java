package training.orsys.androidconnectivitymanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void clickButton(View v ) {
        StringBuffer connexion = new StringBuffer();

        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                connexion.append("WIFI");
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                connexion.append(" + ").append("DATA MOBILE ").append( networkInfo.getSubtypeName() );
            } else {
                connexion.append(" + ").append("PAS DE DATA MOBILE ");
            }
        }

        TextView tv = findViewById(R.id.champ_connectivity);
        tv.setText(connexion.toString());
    }
}
