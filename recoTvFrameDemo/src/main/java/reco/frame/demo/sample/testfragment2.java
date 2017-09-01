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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import reco.frame.demo.R;
import reco.frame.demo.compoment.ISetDefoultItem;
import reco.frame.demo.compoment.MyAdapter;
import reco.frame.demo.compoment.SpaceItemDecoration;
import reco.frame.demo.entity.IndexInfoModel;
import reco.frame.tv.view.TvButton;

/**
 * Created by UPC on 2016/11/28.
 */
public class testfragment2 extends Fragment implements ISetDefoultItem{

    TvButton imageView;
    View newFocuss,oldFocuss;
    List<OnfocuseChanges> listfocus = new ArrayList<>();
    OnfocuseChanges onfocuseChanges,secendfocus,thridfocus,fourfocus;
    View mTarget;
    MyAdapter adapter;
    TvButton ivMian;
    protected List<Animator> mAnimatorList = new ArrayList<Animator>();
    RecyclerView recyclerView;
    protected boolean isScroling = false;
    private IndexInfoModel.PageinfoEntity model;

    @Override
    public void doDefoultitem(View view) {
            AnimatorSet animatorSet = new AnimatorSet();
            List<Animator> list = new ArrayList<>();
//                            list.addAll(getScaleAnimator(oldLastFocus, false));
            Log.i("getScaleAnimator","doDefoultitem");
            list.addAll(getScaleAnimator(view, true));
            list.addAll(getMoveAnimator(view,0,0));
            mTarget.setVisibility(View.VISIBLE);
            animatorSet.setDuration(200);
            animatorSet.playTogether(list);
            animatorSet.start();
    }


    private interface OnfocuseChanges{
        void firstChangeListener(View oldFocus,View newFocus);
    }
    public List<View> viewList = new ArrayList<>();

    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                if(getLoaction(v)[1]<350){
                    System.out.println(viewList.size());
                    viewList.add(v);
                }else{
                    viewList.clear();
                }
            }
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = model.getBlock_img().get((Integer) v.getTag()).getBlock_id();
            Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
            intent.putExtra("resource_id", id);
            startActivity(intent);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        final View rootView = inflater.inflate(R.layout.test_hori_layout, null);
        model = getArguments().getParcelable("info");
        mTarget = new ImageView(getContext());
        mTarget.setBackgroundResource(R.drawable.border_highlights);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        ((ViewGroup) recyclerView.getRootView()).addView(mTarget);
        mTarget.setVisibility(View.GONE);
        onfocuseChanges = new OnfocuseChanges() {
            @Override
            public void firstChangeListener(View oldFocus, View newFocus) {
                if(!(newFocus instanceof RelativeLayout) && oldFocus != null){
                    AnimatorSet animatorSet = new AnimatorSet();
                    List<Animator> list = new ArrayList<>();
//                            list.addAll(getScaleAnimator(oldLastFocus, false));
                    Log.i("getScaleAnimator","firstChangeListener");
                    list.addAll(getScaleAnimator(oldFocus, false));
                    list.addAll(getMoveAnimator(newFocus,0,0));
                    animatorSet.setDuration(200);
                    animatorSet.playTogether(list);
                    animatorSet.start();
                    mTarget.setVisibility(View.GONE);
                }

            }
        };
        secendfocus = new OnfocuseChanges() {
            @Override
            public void firstChangeListener(View oldFocus, View newFocus) {
                if (newFocus == null || !(newFocus instanceof RelativeLayout)) return;
                try {
                    mAnimatorList.addAll(getMoveAnimator(newFocus, 0, 0));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        thridfocus = new OnfocuseChanges() {
            @Override
            public void firstChangeListener(View oldFocus, View newFocus) {
                    Log.i("getScaleAnimator","firstChangeListener");
                    mAnimatorList.addAll(getScaleAnimator(newFocus, true));
                    if (oldFocus != null) {
                        Log.i("getScaleAnimator","firstChangeListener2");
                        mAnimatorList.addAll(getScaleAnimator(oldFocus, false));
                    }
            }
        };
        fourfocus = new OnfocuseChanges() {
            @Override
            public void firstChangeListener(View oldFocus, View newFocus) {
                try {
                    if (!(newFocus instanceof RelativeLayout)) {
                        NewIndexpage.isTabView = true;
                        return;
                    }
                    NewIndexpage.isTabView = false;
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setInterpolator(new DecelerateInterpolator(1));
                    animatorSet.setDuration(200);
                    mTarget.setVisibility(View.VISIBLE);
                    animatorSet.playTogether(mAnimatorList);
//                    for (Animator.AnimatorListener listener : mAnimatorListener) {
//                        animatorSet.addListener(listener);
//                    }
//                    mAnimatorSet = animatorSet;
                    if (oldFocus == null) {
                        animatorSet.setDuration(0);
                        mTarget.setVisibility(View.VISIBLE);
                    }
                    System.out.println("play__________2");
                    animatorSet.start();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        listfocus.add(secendfocus);
        listfocus.add(thridfocus);
        listfocus.add(fourfocus);
        recyclerView.setFocusable(false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                        NewIndexpage.isScrolling = false;
                        System.out.println("isScrolling was false");
                        AnimatorSet animatorSet = new AnimatorSet();
                        List<Animator> list = new ArrayList<>();
//                            list.addAll(getScaleAnimator(oldLastFocus, false));
                        Log.i("getScaleAnimator","onScrollStateChanged");
                        list.addAll(getScaleAnimator(newFocuss, true));
                        list.addAll(getMoveAnimator(newFocuss, 0, 0));
                        animatorSet.setDuration(200);
                        animatorSet.playTogether(list);
                        System.out.println("play__________1");
                        animatorSet.start();
                }else if(newState == RecyclerView.SCROLL_STATE_SETTLING){
                    NewIndexpage.isScrolling = true;
                    System.out.println("isScrolling was true");
                }

            }
        });
        ViewTreeObserver observer = rootView.getViewTreeObserver();
        observer.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                newFocuss = newFocus;
                oldFocuss = oldFocus;
                if(NewIndexpage.isScrolling){
                    return;
                }
                mAnimatorList.clear();
                if(oldFocus!=null && (oldFocus instanceof RelativeLayout) && !(newFocus instanceof RelativeLayout)){
                    onfocuseChanges.firstChangeListener(oldFocus, newFocus);
                    return;
                }

                for(OnfocuseChanges listener : listfocus){
                    listener.firstChangeListener(oldFocus,newFocus);
                }
            }
        });
//        ButterKnife.bind(this, rootView);
        adapter = new MyAdapter(getContext(), model, 0, onFocusChangeListener, onClickListener);
        adapter.setInterface(this);
        SpaceItemDecoration decoration = new SpaceItemDecoration(30);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("scrolls","onResume");

    }

    public void setFocus(){
        if(adapter.hasload) {
            Log.i("scrolls","adapter has load");
            if (NewIndexpage.moveRight == 2) {
                Log.i("scrolls","right to left move to frist");
                View view1 = adapter.getFocusToFrist();
                AnimatorSet animatorSet = new AnimatorSet();
                List<Animator> list = new ArrayList<>();
                Log.i("getScaleAnimator","right to left move to frist");
                list.addAll(getScaleAnimator(view1, true));
                list.addAll(getMoveAnimator2(view1, 0, 0));
                animatorSet.setDuration(200);
                animatorSet.playTogether(list);
                animatorSet.start();
            } else if(NewIndexpage.moveRight == 1) {
                Log.i("scrolls","left to right move to end");
                View view2 = adapter.getFocusToEnd();
                newFocuss = view2;
                newFocuss.requestFocus();
                AnimatorSet animatorSet = new AnimatorSet();
                List<Animator> list = new ArrayList<>();
                Log.i("getScaleAnimator","left to right move to end");
                list.addAll(getScaleAnimator(view2, true));
                list.addAll(getMoveAnimator2(view2, 0, 0));
                animatorSet.setDuration(200);
                animatorSet.playTogether(list);
                animatorSet.start();
            }
        }
    }

    private int [] getLocationOnwindow(View view){
        int[] location = new int[2];
        try {
            view.getLocationInWindow(location);
        }catch (Exception e){

        }
        return location;
    }

    private int [] getLoaction(View view){
        int[] location = new int[2];
        try {
            view.getLocationOnScreen(location);
        }catch (Exception e){

        }
        return location;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }
    protected List<Animator> getMoveAnimator2(View newFocus, int factorX, int factorY) {

        List<Animator> animatorList = new ArrayList<Animator>();
        int newXY[];
        int oldXY[];

        try {

            newXY = getLoaction(newFocus);
            oldXY = getLoaction(mTarget);
            int newWidth;
            int newHeight;
            int oldWidth = mTarget.getMeasuredWidth();
            int oldHeight = mTarget.getMeasuredHeight();
            if (true) {
                float scaleWidth = newFocus.getMeasuredWidth()*1.07f;
                float scaleHeight = newFocus.getMeasuredHeight()*1.07f;
                newWidth = (int) (scaleWidth + 0.5);
                newHeight = (int) (scaleHeight + 0.5);
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
            //System.out.println("width"+oldWidth+newWidth+"___"+"height"+oldHeight+newHeight+"___"+"translationX"+oldXY[0]+newXY[0]+"____"+"translationY"+oldXY[1]+newXY[1]);
            PropertyValuesHolder valuesWithdHolder = PropertyValuesHolder.ofInt("width", oldWidth, newWidth);
            PropertyValuesHolder valuesHeightHolder = PropertyValuesHolder.ofInt("height", oldHeight, newHeight);
            PropertyValuesHolder valuesXHolder = PropertyValuesHolder.ofFloat("translationX", newXY[0], newXY[0]);
            PropertyValuesHolder valuesYHolder = PropertyValuesHolder.ofFloat("translationY", newXY[1]-143, newXY[1]-143);
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



    protected List<Animator> getMoveAnimator(View newFocus, int factorX, int factorY) {

        List<Animator> animatorList = new ArrayList<Animator>();
        int newXY[];
        int oldXY[];

        try {

            newXY = getLoaction(newFocus);
            oldXY = getLoaction(mTarget);
            int newWidth;
            int newHeight;
            int oldWidth = mTarget.getMeasuredWidth();
            int oldHeight = mTarget.getMeasuredHeight();
            if (true) {
                float scaleWidth = newFocus.getMeasuredWidth()*1.07f;
                float scaleHeight = newFocus.getMeasuredHeight()*1.07f;
                newWidth = (int) (scaleWidth + 0.5);
                newHeight = (int) (scaleHeight + 0.5);
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
            //System.out.println("width"+oldWidth+newWidth+"___"+"height"+oldHeight+newHeight+"___"+"translationX"+oldXY[0]+newXY[0]+"____"+"translationY"+oldXY[1]+newXY[1]);
            PropertyValuesHolder valuesWithdHolder = PropertyValuesHolder.ofInt("width", oldWidth, newWidth);
            PropertyValuesHolder valuesHeightHolder = PropertyValuesHolder.ofInt("height", oldHeight, newHeight);
            PropertyValuesHolder valuesXHolder = PropertyValuesHolder.ofFloat("translationX", oldXY[0], newXY[0]);
            PropertyValuesHolder valuesYHolder = PropertyValuesHolder.ofFloat("translationY", oldXY[1]-143, newXY[1]-143);
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

    protected List<Animator> getScaleAnimator(View view, boolean isScale) {
        Log.i("getScaleAnimator","getScaleAnimator");
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
}
