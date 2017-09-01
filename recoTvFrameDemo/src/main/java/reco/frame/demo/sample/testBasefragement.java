package reco.frame.demo.sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import reco.frame.demo.R;
import reco.frame.demo.entity.IndexInfoModel;

/**
 * Created by UPC on 2016/11/28.
 */
public abstract class testBasefragement extends Fragment {
    protected Activity mActivity;
    protected int resID;
    private View view;
    protected List<View> listView = new ArrayList<>();
    protected View.OnClickListener onClickListener;
    private ProgressDialog dialog;

    /**
     * 获得全局的，防止使用getActivity()为空
     * @param context
     */
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.mActivity = context;
        loading(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        if(view == null) {
            view = LayoutInflater.from(mActivity)
                    .inflate(getLayoutId(), container, false);
            view = view.findViewById(R.id.trl_root);
            buildView(view);
        }
        setView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view, savedInstanceState);
        loading(false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();


    public ViewGroup getViews(){
        if(view!=null) {
            return (ViewGroup) view;
        }
        return null;
    }
    private void setView(View c_view){
        if(c_view!=null && listView.isEmpty()){
            for(int i = 0;i<((ViewGroup) c_view).getChildCount();i++) {
                    ((ViewGroup) c_view).getChildAt(i).setOnClickListener(onClickListener);
                listView.add(((ViewGroup) c_view).getChildAt(i));
            }
        }
    }
    protected void loading(boolean load){
        if(dialog == null){
            dialog = new ProgressDialog(mActivity,ProgressDialog.STYLE_SPINNER);
        }
        if(load){
            dialog.show();
        }else{
            dialog.dismiss();
        }
    }
    //public abstract List<View> getTopView();
    public abstract void buildView(View view);
    protected Button getViewFSize(IndexInfoModel.PageinfoEntity.BlockImgEntity info){
        boolean istop = true;
        boolean isright = true;
        Button tvButton = new Button(mActivity);
        tvButton.setId(info.getBlock_id());
        int size = info.getBlock_size();
        RelativeLayout.LayoutParams params = null;
        switch (size){
            case 1:
                params = new RelativeLayout.LayoutParams(1050,500);
                break;
            case 2:
                params = new RelativeLayout.LayoutParams(500,500);
                break;
            case 3:
                params = new RelativeLayout.LayoutParams(500,300);
                break;
            case 4:
                params = new RelativeLayout.LayoutParams(R.dimen.px1000,R.dimen.px550);
                break;
        }
//        if(info.getBlock_location_right() != 0){
//            params.addRule(RelativeLayout.RIGHT_OF,info.getBlock_location_right());
//            isright = false;
//        }
//        if(info.getBlock_location_below() != 0){
//            params.addRule(RelativeLayout.BELOW,info.getBlock_location_below());
//            istop = false;
//        }
        if(istop && !isright){
            params.setMargins(50,0,0,0);
        }else if(!istop && isright){
            params.setMargins(0,50,0,0);
        }else if(!isright && !istop){
            params.setMargins(50,50,0,0);
        }

        tvButton.setLayoutParams(params);
        return tvButton;
    }
}
