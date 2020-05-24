package com.example.weatherapp.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.presentation.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Weather> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Weather item);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtTitle;
        TextView txtWeather;
        TextView txtTemp;
        View layout;
        ImageView imageView;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtTitle = v.findViewById(R.id.titre);
            txtWeather = v.findViewById(R.id.weather);
            txtTemp = v.findViewById(R.id.temp);
            imageView = v.findViewById(R.id.icon);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Weather> myDataset, OnItemClickListener listener) {
        values = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Weather currentPlaceWeather  = values.get(position);
        holder.txtTitle.setText(currentPlaceWeather.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentPlaceWeather);
            }
        });
        holder.txtWeather.setText(currentPlaceWeather.getWeather_state_name());

        holder.txtTemp.setText(currentPlaceWeather.getThe_temp() + "°C");

        Picasso.get().load("https://www.metaweather.com/static/img/weather/png/" + currentPlaceWeather.getWeather_state_abbr() + ".png").into(holder.imageView);

        /*if(currentPlaceWeather.equals("Snow")){Picasso.get().load("https://www.metaweather.com/static/img/weather/sn.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Sleet")){Picasso.get().load("https://www.metaweather.com/static/img/weather/sl.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Hail")){Picasso.get().load("https://www.metaweather.com/static/img/weather/h.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Thunderstorm")){Picasso.get().load("https://www.metaweather.com/static/img/weather/t.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Heavy Rain")){Picasso.get().load("https://www.metaweather.com/static/img/weather/hr.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Light Rain")){Picasso.get().load("https://www.metaweather.com/static/img/weather/lr.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Showers")){Picasso.get().load("https://www.metaweather.com/static/img/weather/sShowers.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Heavy Cloud")){Picasso.get().load("https://www.metaweather.com/static/img/weather/hc.png").into(holder.imageView);}
        if(currentPlaceWeather.equals("Light Cloud")){Picasso.get().load("https://www.metaweather.com/static/img/weather/lc.svg").into(holder.imageView);}
        if(currentPlaceWeather.equals("Clear")){Picasso.get().load("https://www.metaweather.com/static/img/weather/c.svg").into(holder.imageView);}*/


        /*holder.txtFooter.setText(currentPlaceWeather.getMin_temp());
        holder.txtFooter.setText(currentPlaceWeather.getThe_temp());*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}