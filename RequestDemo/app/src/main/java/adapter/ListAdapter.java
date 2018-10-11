package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import bean.TitleBean;
import com.example.wilsonxin.requestdemo.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private Context context;
    List<TitleBean.DataBean.InfosBean> infos;
    public ListAdapter(Context context, List<TitleBean.DataBean.InfosBean> infos) {
        this.context = context;
        this.infos = infos;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.adapter_list,null);
            viewHodler=new ViewHodler();
            viewHodler.tv_title=convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) convertView.getTag();
        }
        viewHodler.tv_title.setText(infos.get(position).getTitle());
        viewHodler.tv_title.setTextColor(Color.parseColor(infos.get(position).getTopics().get(0).getLabel_color()));
        return convertView;
    }
    class ViewHodler{
        TextView tv_title;
    }
}
