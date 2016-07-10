package com.helptabteam.helptab;

/**
 * Created by Nimit Agg on 10-07-2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by skyfishjy on 10/31/14.
 */
public class MyListCursorAdapter extends CursorRecyclerViewAdapter<MyListCursorAdapter.ViewHolder>{

    public MyListCursorAdapter(Context context,Cursor cursor){
        super(context,cursor);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView dosage;
        public ViewHolder(View view) {
            super(view);
            title= (TextView) view.findViewById(R.id.title);
            description= (TextView) view.findViewById(R.id.decription);
            dosage= (TextView) view.findViewById(R.id.dosage);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        //MyListItem myListItem = MyListItem.fromCursor(cursor);
        viewHolder.title.setText(cursor.getString(3));
    }
}
