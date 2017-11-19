package com.example.android.newsapp;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsArrayAdapter extends ArrayAdapter<News> {


    List<News> Data = new ArrayList<>();

    public NewsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        Data = objects;
    }

    public void setData(List<News> data) {
        Data.clear();
        Data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_view, parent, false);
            viewHolder.TitleNames = (TextView) convertView.findViewById(R.id.title_view);
            viewHolder.StoryNames = (TextView) convertView.findViewById(R.id.story_view);
            viewHolder.AuthorNames = (TextView) convertView.findViewById(R.id.names_view);
            viewHolder.Dates = (TextView) convertView.findViewById(R.id.date_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        News item = Data.get(position);
        viewHolder.TitleNames.setText(item.getTitle());
        viewHolder.StoryNames.setText(item.getStory());
        viewHolder.AuthorNames.setText(item.getNameFirst()+" "+item.getNameLast());
        viewHolder.Dates.setText(item.getDate());

        return convertView;
    }

    //    define this static class to prevent using findviewbyId for every getView call
    private static class ViewHolder {
        TextView TitleNames;
        TextView StoryNames;
        TextView AuthorNames;
        TextView Dates;

    }
}

