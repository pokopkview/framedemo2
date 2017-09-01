package reco.frame.demo.compoment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import reco.frame.demo.R;
import reco.frame.demo.entity.IndexInfoModel.PageinfoEntity;

/**
 * 作者:evilbinary on 2/20/16.
 * 邮箱:rootdebug@163.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // 数据集
    public boolean hasload = false;
    private View firstTemp,endTemp;
    private PageinfoEntity mDataset;
    private Context mContex;
    private int id;
    private int counttemp = 0;
    private View.OnFocusChangeListener mOnFocusChangeListener;
    private View.OnClickListener mOnclickListener;
    private OnBindListener onBindListener;
    private ISetDefoultItem setdefoult;

    public void setInterface(ISetDefoultItem i) {
        setdefoult = i;
    }

    public interface  OnBindListener{
        public void onBind(View view, int i);
    };

    public MyAdapter(Context context, PageinfoEntity dataset) {
        super();
        mContex = context;
        mDataset = dataset;
    }
    public MyAdapter(Context context, PageinfoEntity dataset, int id) {
        super();
        mContex = context;
        mDataset = dataset;
        this.id=id;
    }

    public MyAdapter(Context context, PageinfoEntity dataset, int id, View.OnFocusChangeListener onFocusChangeListener, View.OnClickListener onClickListener) {
        super();
        mContex = context;
        mDataset = dataset;
        this.id=id;
        this.mOnFocusChangeListener=onFocusChangeListener;
        this.mOnclickListener = onClickListener;
    }

    public void setOnBindListener(OnBindListener onBindListener) {
        this.onBindListener = onBindListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        System.out.println(i+"___onCreateViewHolder");
        int width = mDataset.getBlock_img().get(counttemp).getBlock_width();
        int height = mDataset.getBlock_img().get(counttemp).getBlock_hight();
        View view = LayoutInflater.from(mContex).inflate(R.layout.item, viewGroup, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);
        System.out.println("width:"+width+":hight:"+height);
        view.setLayoutParams(params);
        ViewHolder holder = new ViewHolder(view);
        if(counttemp==0){
            firstTemp = holder.ItemView;
        }

        if(counttemp == mDataset.getBlock_img().size()-1){
            endTemp = holder.ItemView;
            hasload = true;
            Log.i("scrolls",counttemp+"_"+mDataset.getBlock_img().size()+"");
        }
        counttemp+=1;
        return holder;
    }
    public View getFocusToFrist(){
        return firstTemp;
    }
    public View getFocusToEnd(){
        return endTemp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        if(i == 0){

        }
        Glide.with(mContex).load(mDataset.getBlock_img().get(i).getBlock_img_url()).into(viewHolder.mTextView);
        viewHolder.mTextView.setTag(i);
        viewHolder.mTextView.setOnClickListener(mOnclickListener);
        viewHolder.itemView.setOnFocusChangeListener(mOnFocusChangeListener);
        if(onBindListener!=null){
            onBindListener.onBind(viewHolder.itemView,i);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.getBlock_img().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View ItemView;
        public ImageView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ItemView = itemView;
            mTextView = (ImageView) itemView.findViewById(R.id.textView);
        }
    }

//    public void setData(String[] data){
//        this.mDataset=data;
//    }

}
