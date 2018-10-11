package adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.wilsonxin.study.R;
import com.squareup.picasso.Picasso;
import dao.User;

import java.util.List;

public class OneListViewAdapter extends BaseAdapter {
    private Context context;
    List<User.DateBean.ComicBean> comic;

    public OneListViewAdapter(Context context, List<User.DateBean.ComicBean> comic) {
        this.context = context;
        this.comic = comic;
    }

    @Override
    public int getCount() {
        return comic.size();
    }

    @Override
    public Object getItem(int position) {
        return comic.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.onelistviewfragment, null);
            viewHodler = new ViewHodler();
            viewHodler.tv_fenlei=convertView.findViewById(R.id.tv_fenlei);
            viewHodler.tv_title=convertView.findViewById(R.id.tv_title);
            viewHodler.tv_zuozhe=convertView.findViewById(R.id.tv_zuozhe);
            viewHodler.tv_hua=convertView.findViewById(R.id.tv_hua);
            viewHodler.iv_imageview=convertView.findViewById(R.id.iv_imageview);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) convertView.getTag();
        }
        viewHodler.tv_fenlei.setText(comic.get(position).getLabel_text());
        viewHodler.tv_fenlei.setBackgroundColor(Color.parseColor(comic.get(position).getLabel_color()));
        viewHodler.tv_title.setText(comic.get(position).getTopic().getTitle());
        viewHodler.tv_zuozhe.setText("作者"+"\t"+":"+"\t"+comic.get(position).getTopic().getUser().getNickname());
        viewHodler.tv_hua.setText(comic.get(position).getTitle());
//        Picasso.with(context).load(comic.get(position).getCover_image_url()).into(viewHodler.iv_imageview);
        Glide.with(context).load(comic.get(position).getCover_image_url()).into(viewHodler.iv_imageview);
        return convertView;
    }

    class ViewHodler {
        TextView tv_fenlei, tv_title, tv_zuozhe, tv_hua;
        ImageView iv_imageview;
    }
}
