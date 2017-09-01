package reco.frame.demo.sample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import reco.frame.demo.R;
import reco.frame.demo.compoment.MyAdapter;
import reco.frame.demo.entity.IndexInfoModel;
import reco.frame.tv.view.TvButton;

/**
 * Created by UPC on 2016/11/28.
 */
public class testfragment1 extends Fragment {

    TvButton imageView;
    View views;
    TvButton ivMian;
    View mTarget;
    StaggeredGridLayoutManager gridLayoutManager;
    protected AnimatorSet mAnimatorSet;
    View tempView,lefttemp,righttemp;
    private static final String TAG = "testfragment1";
    RecyclerView recyclerView;
    private IndexInfoModel.PageinfoEntity model;
    private List<OnMyFocusListener> myFocusListenerList = new ArrayList<>();
    protected List<Animator> mAnimatorList = new ArrayList<Animator>();
    private OnMyFocusListener oneFocusListener = new OnMyFocusListener() {
        @Override
        public void Onfocus(View newView, View oldView) {
            System.out.println(oldView+"______"+newView);
            mAnimatorList.addAll(getScaleAnimator(newView, true));
            if (oldView != null) {
                mAnimatorList.addAll(getScaleAnimator(oldView, false));
            }
        }
    };

    private OnMyFocusListener towFocusListener = new OnMyFocusListener() {
        @Override
        public void Onfocus(View newView, View oldView) {
            if (newView == null) return;
            try {

                mAnimatorList.addAll(getMoveAnimator(newView, 0, 0));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    };
    protected List<Animator.AnimatorListener> mAnimatorListener = new ArrayList<Animator.AnimatorListener>(1);

    private OnMyFocusListener threeFocusListener = new OnMyFocusListener() {
        @Override
        public void Onfocus(View newView, View oldView) {
            try {
                if (newView instanceof AbsListView) {
                    return;
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new DecelerateInterpolator(1));
                animatorSet.setDuration(200);
                animatorSet.playTogether(mAnimatorList);
                for (Animator.AnimatorListener listener : mAnimatorListener) {
                    animatorSet.addListener(listener);
                }
                mAnimatorSet = animatorSet;
                if (oldView == null) {
                    animatorSet.setDuration(0);
                    mTarget.setVisibility(View.VISIBLE);
                }
                animatorSet.start();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    };
    private OnMyFocusListener fourFocusListener = new OnMyFocusListener() {
        @Override
        public void Onfocus(View newView, View oldView) {
            System.out.println("进入了absListViewFocusListener");
            try {
                {
                Log.d(TAG, "onFocusChanged==>" + oldView + "__" + newView);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };
//    @Override
//    protected int getLayoutId() {
//
//        return R.layout.testfragmentlayout1;
//    }
//
//    @Override
//    protected void initView(View view, Bundle savedInstanceState) {
 //       views = view;
        //ivMian = (TvButton) view.findViewById(R.id.iv_mian);
        //ivMian.setBackgroundResource(R.drawable.f07f179a91d10ad31fb687dfcee71485);
//        onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.iv_below_end:
//                        System.out.println("点击了第五个1");
//                        break;
//                    case R.id.iv_below_right:
//                        System.out.println("点击了第四个1");
//                        break;
//                    case R.id.iv_below_left:
//                        System.out.println("点击了第三个1");
//                        break;
//                    case R.id.iv_right:
//                        System.out.println("点击了第二个1");
//                        break;
//                    case R.id.iv_mian:
//                        System.out.println("点击了第一个1");
//                        break;
//                }
//            }
//        };
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//
//    @Override
//    public void buildView(View view) {
//
//    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = model.getBlock_img().get((Integer) v.getTag()).getBlock_id();
            Intent intent = new Intent(getActivity(),VideoPlayActivity.class);
            intent.putExtra("resource_id",id);
            startActivity(intent);
        }
    };

    private interface OnMyFocusListener{
        void Onfocus(View newView,View oldView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        mTarget = new ImageView(getContext());
        mTarget.setBackgroundResource(R.drawable.border_highlight);
        model = getArguments().getParcelable("info");
        myFocusListenerList.add(oneFocusListener);
        myFocusListenerList.add(towFocusListener);
        myFocusListenerList.add(threeFocusListener);
        myFocusListenerList.add(fourFocusListener);
        //View rootView = super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.test_hori_layout,null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        ViewGroup viewGroups = (ViewGroup) recyclerView.getRootView();
        viewGroups.addView(mTarget);
        mTarget.setVisibility(View.INVISIBLE);
        recyclerView.setFocusable(false);
        gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        ViewTreeObserver observer = rootView.getViewTreeObserver();
        observer.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if(newFocus == oldFocus){
                    return;
                }
                if(newFocus == null){
                    return;
                }


                for (OnMyFocusListener listener : myFocusListenerList){
                    listener.Onfocus(oldFocus,newFocus);
                }

            }
        });
       // recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        createData(recyclerView);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private class VisibleScope {
        public boolean isVisible;
        public View oldFocus;
        public View newFocus;
    }
    private boolean isScrolling = true;
    private View oldLastFocus,lastFocus;
    private void createData(final RecyclerView recyclerView) {
        //创建数据集
        MyAdapter adapter = new MyAdapter(getContext(), model,0, onFocusChangeListener,onClickListener);
        // 设置Adapter
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                try {
                    super.onScrollStateChanged(recyclerView, newState);
                    //Log.d(TAG, "========>onScrollStateChanged");

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //Log.d(TAG, "========>SCROLL_STATE_IDLE");
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
                        animatorSet.setDuration(200);
                        animatorSet.playTogether(list);
                        animatorSet.start();
                    } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                        //Log.d(TAG, "========>SCROLL_STATE_SETTLING=" + mAnimatorSet.isRunning());
                        isScrolling = true;
                        if (lastFocus != null) {
                            List<Animator> list = getScaleAnimator(lastFocus, false);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(150);
                            animatorSet.playTogether(list);
                            animatorSet.start();
                        }
                    }
                } catch (Exception ex) {

                }


            }
        });
    }

    protected int[] getLocation(View view) {
        int[] location = new int[2];
        try {
            view.getLocationOnScreen(location);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return location;
    }

    protected List<Animator> getMoveAnimator(View newFocus, int factorX, int factorY) {

        List<Animator> animatorList = new ArrayList<Animator>();
        int newXY[];
        int oldXY[];

        try {

            newXY = getLocation(newFocus);
            oldXY = getLocation(mTarget);

            int newWidth;
            int newHeight;
            int oldWidth = mTarget.getMeasuredWidth();
            int oldHeight = mTarget.getMeasuredHeight();

            if (true) {
                float scaleWidth = newFocus.getMeasuredWidth() * 1.07f;
                float scaleHeight = newFocus.getMeasuredHeight() * 1.07f;
                newWidth = (int) (scaleWidth + 0 * 2 + 0.5);
                newHeight = (int) (scaleHeight + 0 * 2 + 0.5);
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
            final ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(mTarget, valuesWithdHolder, valuesHeightHolder, valuesYHolder, valuesXHolder);

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


    protected VisibleScope checkVisibleScope(View oldFocus, View newFocus) {
        VisibleScope scope = new VisibleScope();
        try {
            scope.oldFocus = oldFocus;
            scope.newFocus = newFocus;
            scope.isVisible = true;
//            if (attacheViews.indexOf(oldFocus) >= 0 && attacheViews.indexOf(newFocus) >= 0) {
//                return scope;
//            }

            if(!(newFocus instanceof ImageView) ||!(oldFocus instanceof ImageView)){
                scope.isVisible = false;
                mTarget.setVisibility(View.INVISIBLE);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(getScaleAnimator(oldFocus, false));
                animatorSet.setDuration(0).start();
                Log.d(TAG, "=====>1");
                return scope;
            }


            if (oldFocus != null && newFocus != null) {
                if (oldFocus.getParent() != newFocus.getParent()) {
                   // Log.d(TAG, "=====>" + attacheViews.indexOf(newFocus.getParent()) + "=" + attacheViews.indexOf(oldFocus.getParent()));

                   // if ((attacheViews.indexOf(newFocus.getParent()) < 0) || (attacheViews.indexOf(oldFocus.getParent()) < 0 && attacheViews.indexOf(newFocus.getParent()) > 0)) {
                        mTarget.setVisibility(View.INVISIBLE);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(getScaleAnimator(oldFocus, false));
                        animatorSet.setDuration(0).start();
                        Log.d(TAG, "=====>1");
                        scope.isVisible = false;
                        return scope;
//                    } else {
//                        Log.d(TAG, "=====>2");
//
//                        mTarget.setVisibility(View.VISIBLE);
//                    }
//                    if (attacheViews.indexOf(oldFocus.getParent()) < 0) {
//                        scope.oldFocus = null;
//                    }

                } else {
//                    if (attacheViews.indexOf(newFocus.getParent()) < 0) {
//                        mTarget.setVisibility(View.INVISIBLE);
//                        Log.d(TAG, "=====>3");
//                        scope.isVisible = false;
//                        return scope;
//                    }
                    Log.d(TAG, "=====>4");

                }
                Log.d(TAG, "=====>5");
            }
            mTarget.setVisibility(View.VISIBLE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return scope;
    }

    protected List<Animator> getScaleAnimator(View view, boolean isScale) {

        List<Animator> animatorList = new ArrayList<Animator>(2);
        //if(!mScalable) return animatorList;
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









    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

        }
    };
//    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
//        @Override
//        public void onFocusChange(View v, boolean hasFocus) {
//            if(v.getY()<50) {
//                tempView = v;
//            }else{
//                tempView = null;
//            }
//            if(v.getX()<60){
//                lefttemp = v;
//            }else{
//                lefttemp = null;
//            }
//            if(v.getX()>1200){
//                righttemp = v;
//            }else{
//                righttemp = null;
//            }
//            System.out.println("getX:"+v.getX()+":getY:"+v.getY());
//            if(hasFocus) {
//                if(v.getRight()>tempposition){//right_key
//                    if (v.getLeft() < 1200) {
//                        v.findViewById(R.id.textView).requestFocus();
//                    } else {
//                        v.findViewById(R.id.textView).requestFocus();
//                    }
//                }else{//left_key
//                    if(v.getRight()>400){
//                        v.findViewById(R.id.textView).requestFocus();
//                    }else{
//                        v.findViewById(R.id.textView).requestFocus();
//                    }
//                }
//            }else{
//                if(v.getRight()>tempposition){//right_key
//                    if (v.getLeft() < 1200) {
//                    } else {
//                        recyclerView.scrollBy(200,0);
//                    }
//                }else{//left_key
//                    if(v.getRight()>400){
//                    }else{
//                        recyclerView.scrollBy(-200,0);
//                    }
//                }
//            }
//            tempposition = v.getRight();
//        }
//    };
}
