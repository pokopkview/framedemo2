package reco.frame.demo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import reco.frame.tv.TvBitmap;

/**
 * Created by UPC on 2016/6/17.
 */
public class TVviewpager extends ViewPager {
    private ImageView cursor;
    private final String cursorTag = "TvSubButton";
    /**
     * 光标资源
     */
    private int cursorRes;
    /**
     * 可否缩放
     */
    private boolean scalable;
    /**
     * 放大比率
     */
    private float scale;

    public int getCursorRes() {
        return cursorRes;
    }

    public void setCursorRes(int cursorRes) {
        this.cursorRes = cursorRes;
    }

    public boolean isScalable() {
        return scalable;
    }

    public void setScalable(boolean scalable) {
        this.scalable = scalable;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getDurationLarge() {
        return durationLarge;
    }

    public void setDurationLarge(int durationLarge) {
        this.durationLarge = durationLarge;
    }

    public int getDurationSmall() {
        return durationSmall;
    }

    public void setDurationSmall(int durationSmall) {
        this.durationSmall = durationSmall;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getBoarder() {
        return boarder;
    }

    public void setBoarder(int boarder) {
        this.boarder = boarder;
    }

    public int getBoarderLeft() {
        return boarderLeft;
    }

    public void setBoarderLeft(int boarderLeft) {
        this.boarderLeft = boarderLeft;
    }

    public int getBoarderTop() {
        return boarderTop;
    }

    public void setBoarderTop(int boarderTop) {
        this.boarderTop = boarderTop;
    }

    public int getBoarderRight() {
        return boarderRight;
    }

    public void setBoarderRight(int boarderRight) {
        this.boarderRight = boarderRight;
    }

    public int getBoarderBottom() {
        return boarderBottom;
    }

    public void setBoarderBottom(int boarderBottom) {
        this.boarderBottom = boarderBottom;
    }

    /**
     * 放大用时
     */
    private int durationLarge = 100;
    /**
     * 缩小用时
     */
    private int durationSmall = 100;
    /**
     * 触发延迟
     */
    private int delay = 110;
    /**
     * 光标边框宽度 包括阴影
     */
    private int boarder;
    /**
     * 光标左边框宽度 含阴影
     */
    private int boarderLeft;
    /**
     * 光标顶边框宽度 含阴影
     */
    private int boarderTop;
    /**
     * 光标右边框宽度 含阴影
     */
    private int boarderRight;
    /**
     * 光标底边框宽度 含阴影
     */
    private int boarderBottom;



    public TVviewpager(Context context) {
        this(context,null);
    }

    public TVviewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray custom = getContext().obtainStyledAttributes(attrs,
                reco.frame.tv.R.styleable.Tvviewpager);
        this.cursorRes = custom
                .getResourceId(reco.frame.tv.R.styleable.Tvviewpager_cursorRes, 0);
        this.scalable = custom.getBoolean(reco.frame.tv.R.styleable.Tvviewpager_scalable, true);
        this.scale = custom.getFloat(reco.frame.tv.R.styleable.Tvviewpager_scale, 1.1f);
        this.delay = custom.getInteger(reco.frame.tv.R.styleable.Tvviewpager_delay, 110);
        this.durationLarge = custom.getInteger(
                reco.frame.tv.R.styleable.Tvviewpager_durationLarge, 100);
        this.durationSmall = custom.getInteger(
                reco.frame.tv.R.styleable.Tvviewpager_durationSmall, 100);
        this.boarder = (int) custom.getDimension(reco.frame.tv.R.styleable.Tvviewpager_boarder, 0)
                + custom.getInteger(reco.frame.tv.R.styleable.Tvviewpager_boarderInt, 0);

        if (boarder == 0) {
            this.boarderLeft = (int) custom.getDimension(
                    reco.frame.tv.R.styleable.Tvviewpager_boarderLeft, 0)
                    + custom.getInteger(reco.frame.tv.R.styleable.Tvviewpager_boarderLeftInt, 0);
            this.boarderTop = (int) custom.getDimension(
                    reco.frame.tv.R.styleable.Tvviewpager_boarderTop, 0)
                    + custom.getInteger(reco.frame.tv.R.styleable.Tvviewpager_boarderTopInt, 0);
            this.boarderRight = (int) custom.getDimension(
                    reco.frame.tv.R.styleable.Tvviewpager_boarderRight, 0)
                    + custom.getInteger(reco.frame.tv.R.styleable.Tvviewpager_boarderRightInt, 0);
            this.boarderBottom = (int) custom.getDimension(
                    reco.frame.tv.R.styleable.Tvviewpager_boarderBottom, 0)
                    + custom.getInteger(reco.frame.tv.R.styleable.Tvviewpager_boarderBottomInt,
                    0);

        } else {
            this.boarderLeft = boarder;
            this.boarderTop = boarder;
            this.boarderRight = boarder;
            this.boarderBottom = boarder;
        }

        custom.recycle();

        setFocusable(true);
        setClickable(true);
        // 文字居中
        //setGravity(Gravity.CENTER);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    moveCover();
                }
            }, delay);

        } else {
            removeCover();
        }
    }
    private void moveCover() {
        if (getParent() == null) {
            return;
        }
        cursor = (ImageView) ((RelativeLayout) getParent())
                .findViewWithTag(cursorTag);
        if (cursor == null) {
            cursor = new ImageView(getContext());
            cursor.setTag(cursorTag);
            cursor.setBackgroundResource(cursorRes);
            ((RelativeLayout) getParent()).addView(cursor);
        }
        setBorderParams();
        this.bringToFront();
        cursor.bringToFront();
        if (scalable) {
            scaleToLarge();
        }

    }
    public void removeCover() {
        if (cursor != null) {
            cursor.setVisibility(View.INVISIBLE);
        }

        if (scalable) {
            scaleToNormal();
        }
    }

    private AnimatorSet animatorSet;
    private ObjectAnimator largeX;

    private void scaleToLarge() {

        if (!this.isFocused()) {
            return;
        }

        animatorSet = new AnimatorSet();
        largeX = ObjectAnimator.ofFloat(this, "ScaleX", 1f, scale);
        ObjectAnimator largeY = ObjectAnimator.ofFloat(this, "ScaleY", 1f,
                scale);
        ObjectAnimator cursorX = ObjectAnimator.ofFloat(cursor, "ScaleX", 1f,
                scale);
        ObjectAnimator cursorY = ObjectAnimator.ofFloat(cursor, "ScaleY", 1f,
                scale);

        animatorSet.setDuration(durationLarge);
        animatorSet.play(largeX).with(largeY).with(cursorX).with(cursorY);

        animatorSet.start();
    }

    public void scaleToNormal() {
        if (animatorSet == null) {
            return;
        }
        // float scaleNow=(Float) largeX.getAnimatedValue("ScaleX");
        // if (scaleNow<=0) {
        // scaleNow=1;
        // }
        if (animatorSet.isRunning()) {
            animatorSet.cancel();
        }
        // Log.e(VIEW_LOG_TAG, "scaleNow="+scaleNow);

        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "ScaleX", 1f);
        oa.setDuration(durationSmall);
        oa.start();
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(this, "ScaleY", 1f);
        oa2.setDuration(durationSmall);
        oa2.start();
    }

    /**
     * 配置网络图片地址
     *
     * @param url
     */
    public void configImageUrl(String url) {

        TvBitmap.create(getContext()).display(this, url);

    }

    /**
     * 可配置网络图片
     *
     * @param url
     * @param loadingRes
     */
    public void configImageUrl(String url, int loadingRes) {

        TvBitmap.create(getContext()).display(this, url,loadingRes);


    }

    /**
     * 指定光标相对位置
     */
    private void setBorderParams() {
        cursor.clearAnimation();
        cursor.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cursor
                .getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_LEFT, this.getId());
        params.addRule(RelativeLayout.ALIGN_TOP, this.getId());

        int coverLeft = 0 - boarderLeft;
        int coverTop = 0 - boarderTop;

        params.leftMargin = coverLeft;
        params.topMargin = coverTop;
        params.width = boarderLeft + getWidth() + boarderRight;
        params.height = boarderBottom + getHeight() + boarderTop;

        cursor.setLayoutParams(params);
        //
        // int left = getLeft();
        // int top = getTop();
        //
        // int coverLeft = left - boarderLeft;
        // int coverTop = top - boarderTop;// offset;额
        //
        // cursor.layout(coverLeft, coverTop, getRight() + boarderRight,
        // getBottom() + boarderBottom);
        //
        // params.leftMargin = coverLeft;
        // params.topMargin = coverTop;
        // params.width = boarderLeft + getWidth() + boarderRight;
        // params.height = boarderTop + getHeight() + boarderBottom;
        // border.setLayoutParams(params);

    }
}
