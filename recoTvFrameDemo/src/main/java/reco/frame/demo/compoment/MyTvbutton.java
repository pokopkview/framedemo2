package reco.frame.demo.compoment;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by UPC on 2016/12/19.
 */
public class MyTvbutton extends ImageView{
    private Context mContext;

    public MyTvbutton(Context context) {
        this(context,null);
    }

    public MyTvbutton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTvbutton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

    }
    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if(focused){

        }else{

        }
    }
    private void coverImage(){

    }
    private void recoverImage(){

    }

}
