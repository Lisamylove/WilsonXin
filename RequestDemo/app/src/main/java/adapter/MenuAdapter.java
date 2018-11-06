package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import bean.MenuBean;
import com.bumptech.glide.Glide;
import com.example.wilsonxin.requestdemo.R;

import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private List<MenuBean.DataBean.ComicsBean> comics;
    public boolean flage = false;

    public MenuAdapter(Context context, List<MenuBean.DataBean.ComicsBean> comics) {
        this.context = context;
        this.comics = comics;
    }

    @Override
    public int getCount() {
        return comics.size();
    }

    @Override
    public Object getItem(int position) {
        return comics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.adapter_menu,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_menu_imageview=convertView.findViewById(R.id.iv_menu_imageview);
            viewHolder.tv_menu_textview=convertView.findViewById(R.id.tv_menu_textview);
            viewHolder.tv_menu_des=convertView.findViewById(R.id.tv_menu_des);
            viewHolder.cb_menu_checkbox=convertView.findViewById(R.id.cb_menu_checkbox);
            convertView.setTag(viewHolder);
        }else{
             viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(comics.get(position).getCover_image_url()).into(viewHolder.iv_menu_imageview);
        viewHolder.tv_menu_textview.setText(comics.get(position).getTitle());
        viewHolder.tv_menu_des.setText(comics.get(position).getLabel_text());
        final MenuBean.DataBean.ComicsBean comicsBean = comics.get(position);
        // 根据isSelected来设置checkbox的显示状况
        if (flage) {
            viewHolder.cb_menu_checkbox.setVisibility(View.VISIBLE);
        } else {
            viewHolder.cb_menu_checkbox.setVisibility(View.GONE);
        }
        viewHolder.cb_menu_checkbox.setChecked(comicsBean.isCheck);
        //注意这里设置的不是onCheckedChangListener，还是值得思考一下的
        viewHolder.cb_menu_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comicsBean.isCheck) {
                    comicsBean.isCheck = false;
                } else {
                    comicsBean.isCheck = true;
                }
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView iv_menu_imageview;
        TextView tv_menu_textview,tv_menu_des;
        CheckBox cb_menu_checkbox;
    }
}
