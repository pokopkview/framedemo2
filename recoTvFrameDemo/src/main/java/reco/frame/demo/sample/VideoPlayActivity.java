package reco.frame.demo.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import reco.frame.demo.R;

public class VideoPlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        int id = getIntent().getIntExtra("resource_id",0);
        System.out.println(id+"___________resourceID");
        TextView tv_show_id = (TextView) findViewById(R.id.tv_show_id);
        tv_show_id.setText(id+"");
    }
}
