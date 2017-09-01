package reco.frame.demo.compoment;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import reco.frame.demo.sample.NewIndexpage;

/**
 * Created by UPC on 2016/12/15.
 */
public class testViewpager extends ViewPager {
    public testViewpager(Context context) {
        super(context);
    }
    private int lastValue = 0;
    public testViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        System.out.println("onRequestFocusInDescendants____"+direction+"___"+previouslyFocusedRect);
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        System.out.println("onPageScrolled____"+position+":"+offset+":"+offsetPixels);
        if (lastValue > offsetPixels) {
            NewIndexpage.moveRight = 1;
        } else if (lastValue < offsetPixels) {
            NewIndexpage.moveRight = 2;
        }
        lastValue = offsetPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        System.out.println(ev.getAction());
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println(widthMeasureSpec+"__onMeasure__"+heightMeasureSpec);
    }

//    @Override
//    public boolean beginFakeDrag() {
//        System.out.println("beginFakeDrag");
//        return super.beginFakeDrag();
//    }
//
//    @Override
//    public void requestChildFocus(View child, View focused) {
//        super.requestChildFocus(child, focused);
//        System.out.println(child+"___requestChildFocus____"+focused);
//    }
//
//    @Override
//    public void computeScroll() {
//        System.out.println("computeScroll_____");
//        super.computeScroll();
//    }
}
