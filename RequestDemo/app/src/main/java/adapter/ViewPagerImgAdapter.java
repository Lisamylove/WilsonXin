package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import bean.TitleBean;
import com.bumptech.glide.Glide;
import com.example.wilsonxin.requestdemo.R;

import java.util.List;

public class ViewPagerImgAdapter extends PagerAdapter {
    private Context context;
    private ImageView iv_img;
    private TextView tv_img_title;
    private List<TitleBean.DataBean.InfosBean.TopicsBean> topics;
    public ViewPagerImgAdapter(Context context, List<TitleBean.DataBean.InfosBean.TopicsBean> topics) {
        this.context = context;
        this.topics = topics;
    }

    @Override
    public int getCount() {
        return topics.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=View.inflate(context, R.layout.viewpageradapter,null);
        iv_img = view.findViewById(R.id.iv_msg);
        tv_img_title = view.findViewById(R.id.tv_img_title);
        tv_img_title.setText(topics.get(position).getTitle());
        Glide.with(context).load(topics.get(position).getPic()).into(iv_img);
        container.addView(view);
        return view;
    }
}
