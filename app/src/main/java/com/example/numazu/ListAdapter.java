package com.example.numazu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final ListData[] listData;
    private final Context context;

    public ListAdapter(Context context, ListData[] listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListData lst = listData[position];
        holder.textView.setText(lst.getName());
        final String name = lst.getName();
        final String type = lst.getType();
        final String menu = lst.getMenu();
        final String address_jp = lst.getAddress_jp();
        final String address = lst.getAddress();
        final String day = lst.getDay();
        final int oHour = lst.getOhour();
        final int oMinute = lst.getOminute();
        final int cHour = lst.getChour();
        final int cMinute = lst.getCminute();
        final int rsHour = lst.getRshour();
        final int rsMinute = lst.getRsminute();
        final int reHour = lst.getRehour();
        final String longitude = lst.getLongitude();
        final String latitude = lst.getLatitude();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListActivity) context).showDescription(name, type, menu, address_jp, address, day, oHour, oMinute, cHour, cMinute, rsHour, rsMinute, reHour, rsMinute, longitude, latitude);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public final RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
