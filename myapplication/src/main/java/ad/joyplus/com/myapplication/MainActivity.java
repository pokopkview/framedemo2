package ad.joyplus.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        getinfo();
        return super.onKeyDown(keyCode, event);
    }

    private void getinfo() {
        System.out.println("getinfo");
        new Thread() {
            @Override
            public void run() {
                String urls = "http://advapikj.ijoyplus.com/advapi/v1/mdrequest?rt=android_app&v=4.1.6&u=Mozilla%2F5.0%20(Linux%3B%20U%3B%20Android%204.3%3B%20zh-cn%3B%20Konka%20Android%20TV%20918%20Build%2FJLS36G)%20AppleWebKit%2F533.1%20(KHTML%2C%20like%20Gecko)%20Version%2F4.0%20Mobile%20Safari%2F533.1&u2=Mozilla%2F5.0%20(Linux%3B%20U%3B%20Android%204.3%3B%20zh-cn%3B%20Konka%20Android%20TV%20918%20Build%2FJLS36G)%20AppleWebKit%2F533.1%20(KHTML%2C%20like%20Gecko)%20Version%2F4.0%20Mobile%20Safari%2F533.1&s=7d19a0a1c4f0405b7e9bccf5ad420443&o=&o2=b605763ae0b6ee46&t=1482204340417&connection_type=UNKNOWN&listads=&c.mraid=0&sdk=vad&u_wv=Mozilla%2F5.0%20(Linux%3B%20U%3B%20Android%204.3%3B%20zh-cn%3B%20Konka%20Android%20TV%20918%20Build%2FJLS36G)%20AppleWebKit%2F533.1%20(KHTML%2C%20like%20Gecko)%20Version%2F4.0%20Mobile%20Safari%2F533.1&ds=KONKA_6a918&sn=LWK1422Y1003137379L1&dt=1&up=&lp=&dm=6a918&b=JOYPLUS&ot=0&screen=002&mt=&os=&osv=&dss=0&dsr=&i=863a9dd1cda1284186310762d2ccf89e";
                URL url = null;
                try {
                    url = new URL(urls);
                    //String ua = new WebView(MainActivity.this).getSettings().getUserAgentString();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//使用HttpURLConnection进行访问
                    httpURLConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    System.out.println(sb.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
