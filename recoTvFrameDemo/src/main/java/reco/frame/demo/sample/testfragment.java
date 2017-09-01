package reco.frame.demo.sample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import reco.frame.demo.R;
import reco.frame.demo.compoment.MyAdapter;
import reco.frame.demo.entity.IndexInfoModel.PageinfoEntity;

/**
 * Created by UPC on 2016/11/28.
 */
public class testfragment extends Fragment {
    View view;
    View tempView,lefttemp,righttemp;
    RecyclerView recyclerView;
    StaggeredGridLayoutManager gridLayoutManager;
    ImageView mView;
    View lastFocus,oldLastFocus;
    List<Animator> animationList = new ArrayList<>();
    AnimatorSet animatorSet;
    int tempposition = 0;
    protected int mMargin = 0;
    int scrollTemp = -1;
    protected List<View> attacheViews = new ArrayList<>();
    private boolean isScrolling = false;
    View stateView,state2View;
    protected boolean mScalable = true;
    IScroll iScroll;
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model = getArguments().getParcelable("info");
        view = inflater.inflate(R.layout.test_hori_layout,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setFocusable(false);
        mView = new ImageView(getContext());
        mView.setBackgroundResource(R.drawable.border_highlight);
        //BorderView borderView = new BorderView(mView);
        recyclerView.setLayoutManager(gridLayoutManager);
        //borderView.attachTo(recyclerView);
        //recyclerView.addItemDecoration(new SpaceItemDecoration(getContext()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    isScrolling = false;
                    View oldFocus = oldLastFocus;
                    View newFocus = lastFocus;
                    VisibleScope scope = checkVisibleScope(oldFocus, newFocus);
                    if (!scope.isVisible) {
                        return;
                    } else {
                        oldFocus = scope.oldFocus;
                        newFocus = scope.newFocus;
                    }
                    AnimatorSet animatorSet = new AnimatorSet();
                    List<Animator> list = new ArrayList<>();
//                            list.addAll(getScaleAnimator(oldLastFocus, false));
                    list.addAll(getScaleAnimator(newFocus, true));
                    list.addAll(getMoveAnimator(newFocus, 0, 0));
                    animatorSet.setDuration(1000);
                    animatorSet.playTogether(list);
                    animatorSet.start();
                }else if(newState == RecyclerView.SCROLL_STATE_SETTLING){
                    isScrolling = true;
                    if (lastFocus != null) {
                        List<Animator> list = getScaleAnimator(lastFocus, false);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.setDuration(150);
                        animatorSet.playTogether(list);
                        animatorSet.start();
                    }
                }


            }
        });
        createData(recyclerView);
        return view;
    }
    protected List<Animator> getMoveAnimator(View newFocus, int factorX, int factorY) {

        List<Animator> animatorList = new ArrayList<Animator>();
        int newXY[];
        int oldXY[];

        try {

            newXY = getLocation(newFocus);
            oldXY = getLocation(mView);

            int newWidth;
            int newHeight;
            int oldWidth = mView.getMeasuredWidth();
            int oldHeight = mView.getMeasuredHeight();

            if (mScalable) {
                float scaleWidth = newFocus.getMeasuredWidth() * 1.07f;
                float scaleHeight = newFocus.getMeasuredHeight() * 1.07f;
                newWidth = (int) (scaleWidth + mMargin * 2 + 0.5);
                newHeight = (int) (scaleHeight + mMargin * 2 + 0.5);
                newXY[0] = (int) (newXY[0] - (newWidth - newFocus.getMeasuredWidth()) / 2.0f) + factorX;
                newXY[1] = (int) (newXY[1] - (newHeight - newFocus.getMeasuredHeight()) / 2.0f + 0.5 + factorY);

            } else {
                newWidth = newFocus.getWidth();
                newHeight = newFocus.getHeight();
            }
            if (oldHeight == 0 && oldWidth == 0) {
                oldHeight = newHeight;
                oldWidth = newWidth;
            }

            PropertyValuesHolder valuesWithdHolder = PropertyValuesHolder.ofInt("width", oldWidth, newWidth);
            PropertyValuesHolder valuesHeightHolder = PropertyValuesHolder.ofInt("height", oldHeight, newHeight);
            PropertyValuesHolder valuesXHolder = PropertyValuesHolder.ofFloat("translationX", oldXY[0], newXY[0]);
            PropertyValuesHolder valuesYHolder = PropertyValuesHolder.ofFloat("translationY", oldXY[1], newXY[1]);
            final ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(mView, valuesWithdHolder, valuesHeightHolder, valuesYHolder, valuesXHolder);

            scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public synchronized void onAnimationUpdate(ValueAnimator animation) {
                    int width = (int) animation.getAnimatedValue("width");
                    int height = (int) animation.getAnimatedValue("height");
                    float translationX = (float) animation.getAnimatedValue("translationX");
                    float translationY = (float) animation.getAnimatedValue("translationY");
                    //Log.d(TAG,"width:"+width+" height:"+height+" translationX:"+translationX+" translationY:"+translationY);
                    View view = (View) scaleAnimator.getTarget();
                    assert view != null;
                    int w = view.getLayoutParams().width;
                    view.getLayoutParams().width = width;
                    view.getLayoutParams().height = height;
                    if (width > 0) {
                        view.requestLayout();
                        view.postInvalidate();

                    }
                }
            });
            animatorList.add(scaleAnimator);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return animatorList;
    }

    private FocusListener  scalelistener = new FocusListener() {
        @Override
        public void onFocusChanged(View oldFocus, View newFocus) {
            if(oldFocus instanceof ImageView || newFocus instanceof ImageView){
                return;
            }else if(oldFocus.getClass().equals(newFocus.getClass())){

            }
        }
    };
    private FocusListener playlistener = new FocusListener() {
        @Override
        public void onFocusChanged(View oldFocus, View newFocus) {
            System.out.println(oldFocus+"____"+newFocus);
        }
    };
    private FocusListener truelistener = new FocusListener() {
        @Override
        public void onFocusChanged(View oldFocus, View newFocus) {

        }
    };



    @Override
    public void onStart() {
        doEffectWork();
        mFocusListener.add(truelistener);
        mFocusListener.add(scalelistener);
        mFocusListener.add(playlistener);
        super.onStart();
    }
    private interface IScroll{
        void doScroll(View oldview,View newView);
    }
    private class VisibleScope {
        public boolean isVisible;
        public View oldFocus;
        public View newFocus;
    }

    private void doEffectWork(){
        if(mView == null){
            mView = new ImageView(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(500,500);
            mView.setLayoutParams(layoutParams);
            mView.setBackgroundResource(R.drawable.border_highlight);
            ViewGroup viewGroup = (ViewGroup) recyclerView.getRootView();
            viewGroup.addView(mView);
            mView.setVisibility(View.INVISIBLE);
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int oldx = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //scrollTemp = dx;
            }
        });
        final ViewTreeObserver observer = recyclerView.getViewTreeObserver();
        observer.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {

                if (newFocus == null ) {
                    return;
                }
                if (oldFocus == newFocus)
                    return;

                if (animatorSet != null && animatorSet.isRunning()) {
                    animatorSet.end();
                }

                lastFocus = newFocus;
                oldLastFocus = oldFocus;
                Log.d("testfragment", "onFocusChanged:111111111" + oldFocus + "=" + newFocus);

                VisibleScope scope = checkVisibleScope(oldFocus, newFocus);
                if (!scope.isVisible) {
                    return;
                } else {
                    oldFocus = scope.oldFocus;
                    newFocus = scope.newFocus;
                    oldLastFocus = scope.oldFocus;
                }

                if (isScrolling || newFocus == null || newFocus.getWidth() <= 0 || newFocus.getHeight() <= 0)
                    return;
                Log.d("testfragment", "onFocusChanged:2222222222" + oldFocus + "=" + newFocus);

                animationList.clear();

                for (FocusListener f : mFocusListener) {
                    f.onFocusChanged(oldFocus, newFocus);
                }



            }
        });

    }
    protected List<Animator> getScaleAnimator(View view, boolean isScale) {

        List<Animator> animatorList = new ArrayList<Animator>(2);
        if(!mScalable) return animatorList;
        try {
            float scaleBefore = 1.0f;
            float scaleAfter = 1.07f;
            if (!isScale) {
                scaleBefore = 1.07f;
                scaleAfter = 1.0f;
            }
            ObjectAnimator scaleX = new ObjectAnimator().ofFloat(view, "scaleX", scaleBefore, scaleAfter);
            ObjectAnimator scaleY = new ObjectAnimator().ofFloat(view, "scaleY", scaleBefore, scaleAfter);
            animatorList.add(scaleX);
            animatorList.add(scaleY);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return animatorList;
    }

    protected VisibleScope checkVisibleScope(View oldFocus, View newFocus) {
        VisibleScope scope = new VisibleScope();
        try {
            scope.oldFocus = oldFocus;
            scope.newFocus = newFocus;
            scope.isVisible = true;
            if (attacheViews.indexOf(oldFocus) >= 0 && attacheViews.indexOf(newFocus) >= 0) {
                return scope;
            }

            if (oldFocus != null && newFocus != null) {
                if (oldFocus.getParent() != newFocus.getParent()) {
                    Log.d("testfragment", "=====>" + attacheViews.indexOf(newFocus.getParent()) + "=" + attacheViews.indexOf(oldFocus.getParent()));

                    if ((attacheViews.indexOf(newFocus.getParent()) < 0) || (attacheViews.indexOf(oldFocus.getParent()) < 0 && attacheViews.indexOf(newFocus.getParent()) > 0)) {
                        mView.setVisibility(View.INVISIBLE);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(getScaleAnimator(oldFocus, false));
                        animatorSet.setDuration(0).start();
                        Log.d("testfragment", "=====>1");
                        scope.isVisible = false;
                        return scope;
                    } else {
                        Log.d("testfragment", "=====>2");

                        mView.setVisibility(View.VISIBLE);
                    }
                    if (attacheViews.indexOf(oldFocus.getParent()) < 0) {
                        scope.oldFocus = null;
                    }

                } else {
                    if (attacheViews.indexOf(newFocus.getParent()) < 0) {
                        mView.setVisibility(View.INVISIBLE);
                        Log.d("testfragment", "=====>3");
                        scope.isVisible = false;
                        return scope;
                    }
                    Log.d("testfragment", "=====>4");

                }
                Log.d("testfragment", "=====>5");
            }
            mView.setVisibility(View.VISIBLE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return scope;
    }
    private void setScale(int type,View viewss){
        if(type == 1) {
            ObjectAnimator scaleX = new ObjectAnimator().ofFloat(viewss, "scaleX", 1f, 1.07f);
            ObjectAnimator scaleY = new ObjectAnimator().ofFloat(viewss, "scaleY", 1f, 1.07f);
            animationList.add(scaleX);
            animationList.add(scaleY);
        }else{
            ObjectAnimator scaleX = new ObjectAnimator().ofFloat(viewss, "scaleX", 1.07f, 1f);
            ObjectAnimator scaleY = new ObjectAnimator().ofFloat(viewss, "scaleY", 1.07f, 1f);
            animationList.add(scaleX);
            animationList.add(scaleY);
        }

    }

    private int [] getLocation(View view){
        int[]location = new int[2];
        try {
            view.getLocationOnScreen(location);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return location;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = model.getBlock_img().get((Integer) v.getTag()).getBlock_id();
            Intent intent = new Intent(getActivity(),VideoPlayActivity.class);
            intent.putExtra("resource_id",id);
            startActivity(intent);
        }
    };

    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(v.getY()<50) {
                tempView = v;
            }else{
                tempView = null;
            }
            if(v.getX()<60){
                lefttemp = v;
            }else{
                lefttemp = null;
            }
            if(v.getX()>1200){
                righttemp = v;
            }else{
                righttemp = null;
            }
            System.out.println("getX:"+v.getX()+":getY:"+v.getY());
            if(hasFocus) {
                if(v.getRight()>tempposition){//right_key
                    if (v.getLeft() < 1200) {
                        v.findViewById(R.id.textView).requestFocus();
                    } else {
                        v.findViewById(R.id.textView).requestFocus();
                    }
                }else{//left_key
                    if(v.getRight()>400){
                        v.findViewById(R.id.textView).requestFocus();
                    }else{
                        v.findViewById(R.id.textView).requestFocus();
                    }
                }
            }else{
                if(v.getRight()>tempposition){//right_key
                    if (v.getLeft() < 1200) {
                    } else {
                        recyclerView.scrollBy(200,0);
                    }
                }else{//left_key
                    if(v.getRight()>400){
                    }else{
                        recyclerView.scrollBy(-200,0);
                    }
                }
            }
            tempposition = v.getRight();
        }
    };

    private void createData(final RecyclerView recyclerView) {
        //创建数据集
        MyAdapter adapter = new MyAdapter(getContext(), model,0, onFocusChangeListener,onClickListener);
        // 设置Adapter
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }
    public View getViews(){
        if(null == tempView){
            return null;
        }else {
            return tempView;
        }
    }
    public View getLefttemp(){
        if(null == lefttemp){
            return null;
        }else {
            return lefttemp;
        }
    }
    public View getRighttemp(){
        if(null == righttemp){
            return null;
        }else {
            return righttemp;
        }
    }
    private PageinfoEntity model;


    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        //系统theme.xml中,可以自定义
        private final int[] ATTRS = new int[] { android.R.attr.listDivider };
        private Drawable mDivider;

        public SpaceItemDecoration(Context context)
        {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state)
        {

            drawHorizontal(c, parent);
            drawVertical(c, parent);

        }

        private int getSpanCount(RecyclerView parent)
        {
            // 列数
            int spanCount = -1;
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager)
            {

                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            } else if (layoutManager instanceof StaggeredGridLayoutManager)
            {
                spanCount = ((StaggeredGridLayoutManager) layoutManager)
                        .getSpanCount();
            }
            return spanCount;
        }

        public void drawHorizontal(Canvas c, RecyclerView parent)
        {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++)
            {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;
                final int right = child.getRight() + params.rightMargin
                        + mDivider.getIntrinsicWidth();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent)
        {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++)
            {
                final View child = parent.getChildAt(i);

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop() - params.topMargin;
                final int bottom = child.getBottom() + params.bottomMargin;
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                    int childCount)
        {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager)
            {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL)
                {
                    if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                    {
                        return true;
                    }
                } else
                {
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                        return true;
                }
            }
            return false;
        }

        private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                                  int childCount)
        {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager)
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                    return true;
            } else if (layoutManager instanceof StaggeredGridLayoutManager)
            {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                // StaggeredGridLayoutManager 且纵向滚动
                if (orientation == StaggeredGridLayoutManager.VERTICAL)
                {
                    childCount = childCount - childCount % spanCount;
                    // 如果是最后一行，则不需要绘制底部
                    if (pos >= childCount)
                        return true;
                } else
                // StaggeredGridLayoutManager 且横向滚动
                {
                    // 如果是最后一行，则不需要绘制底部
                    if ((pos + 1) % spanCount == 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition,
                                   RecyclerView parent)
        {
            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();
            if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
            {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
            {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else
            {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                        mDivider.getIntrinsicHeight());
            }
        }

    }
    protected List<FocusListener> mFocusListener = new ArrayList<FocusListener>(1);
    public interface FocusListener {
        public void onFocusChanged(View oldFocus, View newFocus);
    }



    /**
     *  System.out.println(oldFocus+"_______"+newFocus);
     animationList.clear();
     mView.setVisibility(View.VISIBLE);
     System.out.println(getLocation(mView)+"_________"+getLocation(oldFocus));
     int [] oldXY = getLocation(mView);
     int [] newXY = getLocation(newFocus);
     int oldWidth,oldHeight,newWidth,newHeight;
     oldWidth = mView.getMeasuredWidth();
     oldHeight = mView.getMeasuredHeight();
     newWidth = newFocus.getMeasuredWidth();
     newHeight = newFocus.getMeasuredHeight();
     PropertyValuesHolder valuesWithdHolder = PropertyValuesHolder.ofInt("width", oldWidth, newWidth);
     PropertyValuesHolder valuesHeightHolder = PropertyValuesHolder.ofInt("height", oldHeight, newHeight);
     PropertyValuesHolder valuesXHolder = PropertyValuesHolder.ofFloat("translationX", oldXY[0], newXY[0]);
     PropertyValuesHolder valuesYHolder = PropertyValuesHolder.ofFloat("translationY", oldXY[1], newXY[1]);
     System.out.println("translationX"+oldXY[0]+":"+newXY[0]+"translationY"+oldXY[1]+":"+newXY[1]+"________onScrolled");
     System.out.println("width"+oldWidth+":"+newWidth+"height"+oldHeight+":"+newHeight+"translationX"+oldXY[0]+":"+newXY[0]+"translationY"+oldXY[1]+":"+newXY[1]);
     final ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(mView, valuesWithdHolder, valuesHeightHolder, valuesYHolder, valuesXHolder);
     scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public synchronized void onAnimationUpdate(ValueAnimator animation) {
    int width = (int) animation.getAnimatedValue("width");
    int height = (int) animation.getAnimatedValue("height");
    System.out.println("width:"+width+":height:"+height);
    float translationX = (float) animation.getAnimatedValue("translationX");
    float translationY = (float) animation.getAnimatedValue("translationY");
    //Log.d(TAG,"width:"+width+" height:"+height+" translationX:"+translationX+" translationY:"+translationY);
    View view = (View) scaleAnimator.getTarget();
    assert view != null;
    //PropertyValuesHolder[]holders = scaleAnimator.getValues();
    int w = view.getLayoutParams().width;
    view.getLayoutParams().width = width;
    view.getLayoutParams().height = height;
    System.out.println(translationX+"__1__"+translationY+"____onAnimationUpdate__"+scrollTemp);
    System.out.println(view.getX()+"__2__"+view.getY()+"____onAnimationUpdate");
    view.setX(translationX-scrollTemp);
    view.setY(translationY);
    //view.setScaleX(translationX-scrollTemp);
    if (width > 0) {
    view.requestLayout();
    view.postInvalidate();
    }
    }
    });
     animationList.add(scaleAnimator);
     setScale(1,newFocus);
     setScale(0,oldFocus);
     AnimatorSet animatorSet = new AnimatorSet();
     animatorSet.setDuration(500);
     animatorSet.playTogether(animationList);
     animatorSet.start();

     int[] last = new int[2];
     int [] lasttemp = gridLayoutManager.findLastCompletelyVisibleItemPositions(last);
     View view = gridLayoutManager.getChildAt(last[0]);
     System.out.println(view.getMeasuredHeight()+"___"+view.getMeasuredWidth()+"___"+view.getX());

     //                animationList.clear();
     System.out.println("oldFocus"+oldFocus+":newFocus"+newFocus);
     */




//    View views;
//    private List<View> allView;
//
//
//    @Override
//    protected int getLayoutId() {
//        onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (View views : listView) {
//                    if (views == v) {
//                        if(model!=null){
//                            int id = model.getBlock_img().get(listView.indexOf(views)).getBlock_id();
//                            Intent intent = new Intent(getActivity(),VideoPlayActivity.class);
//                            System.out.println(id+"________id");
//                            intent.putExtra("resource_id",id);
//                            startActivity(intent);
//                        }
//                    }
//                }
//            }
//        };
//        model = getArguments().getParcelable("info");
//        return R.layout.test_hori_layout;
//    }
//
//
//    @Override
//    protected void initView(View view, Bundle savedInstanceState) {
//        views = view;
//        for(final View view1 : listView){
//            System.out.println(model.getBlock_img().get(listView.indexOf(view1)).getBlock_img_url());
//            Glide.with(getActivity()).load(model.getBlock_img().get(listView.indexOf(view1)).getBlock_img_url()).into(new SimpleTarget<GlideDrawable>() {
//                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                @Override
//                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                    view1.setBackground(resource);
//                }
//            });
//        }
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    public List<View> getTopView() {
//        if(allView == null){
//            allView = new ArrayList<>();
//            for(int i = 0;i<((ViewGroup)views).getChildCount();i++){
//                if(((ViewGroup)views).getChildAt(i).getTop()<150){
//                    allView.add(((ViewGroup)views).getChildAt(i));
//                }
//            }
//        }
//        return allView;
//    }
//
//    @Override
//    public void buildView(View b_view) {
//        if(model!=null){
//            for(BlockImgEntity entity : model.getBlock_img()){
//                Button button = getViewFSize(entity);
//                ((ViewGroup)b_view).addView(button);
//            }
//        }
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
//    private void testRecyclerViewGridLayout() {
//        //test grid
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        GridLayoutManager gridlayoutManager = new TvGridLayoutManagerScrolling(this, 4);
//        gridlayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(gridlayoutManager);
//        recyclerView.setFocusable(false);
//
//        border.attachTo(recyclerView);
//        createData(recyclerView);
//
//    }

}
