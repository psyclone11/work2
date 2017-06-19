package com.example.psyclone.work2;

import android.app.Activity;
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

/**
 * Created by psyclone on 19/6/2560.
 */

public class ListBookAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> books;
    Activity context;
    int resource;

    static class ViewHolder{
        public TextView textTitle;
        public TextView textAuthor;
        public TextView textPrice;
    }

    public ListBookAdapter(Activity context,int resource,ArrayList<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.books = objects;
        this.resource = resource;
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_book,null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textTitle = (TextView) rowView.findViewById(R.id.textView);
            viewHolder.textAuthor = (TextView) rowView.findViewById(R.id.textView3);
            viewHolder.textPrice = (TextView) rowView.findViewById(R.id.textView5);
            rowView.setTag(viewHolder);

        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Book book = books.get(position);

        holder.textTitle.setText(book.getTitle());
        holder.textAuthor.setText(book.getAuthors());
        holder.textPrice.setText(Double.toString(book.getPrice()));

        return rowView;
    }


}
