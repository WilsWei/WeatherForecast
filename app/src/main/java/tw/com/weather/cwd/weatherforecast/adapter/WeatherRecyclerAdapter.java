package tw.com.weather.cwd.weatherforecast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tw.com.weather.cwd.weatherforecast.R;
import tw.com.weather.cwd.weatherforecast.model.WeatherData;
import tw.com.weather.cwd.weatherforecast.util.SharedUtil;


public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {
    private List<WeatherData> mList;
    private Context mContext;
    public WeatherRecyclerAdapter(List<WeatherData> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherData itemData = mList.get(position);

        setDataViewValue(holder, itemData);
    }

    private void setDataViewValue (ViewHolder viewHolder, WeatherData data) {

        viewHolder.dayOfWeekText.setText(SharedUtil.getDayOfWeek(mContext, data.getData()));

        if(SharedUtil.compareToToday(data.getData()) == 0) {
            viewHolder.dateText.setText(mContext.getString(R.string.today));
        } else if(SharedUtil.compareToToday(data.getData()) == 1) {
            viewHolder.dateText.setText(mContext.getString(R.string.tomorrow));
        } else if(SharedUtil.compareToToday(data.getData()) == 2) {
            viewHolder.dateText.setText(mContext.getString(R.string.after_tomorrow));
        } else {
            viewHolder.dateText.setText(data.getData());
        }


        if(data.getEarlyDetail() != null && !TextUtils.isEmpty(data.getEarlyDetail().getTemperature())) {
            viewHolder.textEarlyTemperature.setText(String.format(mContext.getString(R.string.temperature), data.getEarlyDetail().getTemperature()));
        } else {
            viewHolder.textEarlyTemperature.setText("-");
        }

        if(data.getNightDetail() != null && !TextUtils.isEmpty(data.getNightDetail().getTemperature())) {
            viewHolder.textNightTemperature.setText(String.format(mContext.getString(R.string.temperature), data.getNightDetail().getTemperature()));
        } else {
            viewHolder.textNightTemperature.setText("-");
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<WeatherData> getList() {
        return mList;
    }

    public void setList(List<WeatherData> cardList) {
        this.mList = cardList;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOfWeekText;
        private TextView dateText;
        private TextView textEarlyTemperature;
        private TextView textNightTemperature;

        public ViewHolder(View itemView) {
            super(itemView);

            dayOfWeekText = (TextView) itemView.findViewById(R.id.text_day_of_week);
            dateText = (TextView) itemView.findViewById(R.id.text_date);
            textEarlyTemperature = (TextView) itemView.findViewById(R.id.text_early);
            textNightTemperature = (TextView) itemView.findViewById(R.id.text_night);

        }
    }
}
