package com.helptabteam.helptab;

/**
 * Created by Nimit Agg on 10-07-2016.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    public Context context;
    public MyListCursorAdapter(Context context,Cursor cursor){
        super(context,cursor);
        this.context=context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public ImageView delete;
        public TextView dosage;

        public ViewHolder(View view) {
            super(view);
            title= (TextView) view.findViewById(R.id.title);
            description= (TextView) view.findViewById(R.id.decription);
            dosage= (TextView) view.findViewById(R.id.dosage);
            delete= (ImageView) view.findViewById(R.id.delete);
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
    public void onBindViewHolder(ViewHolder viewHolder, final Cursor cursor) {
        //MyListItem myListItem = MyListItem.fromCursor(cursor);
        viewHolder.title.setText(cursor.getString(cursor.getColumnIndex("title")));
        viewHolder.description.setText(cursor.getString(cursor.getColumnIndex("description")));
        viewHolder.dosage.setText("Daily dosage-Started from: "+cursor.getString(cursor.getColumnIndex("start")));
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this Healthtab ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                context.getContentResolver().delete(QuoteProvider.Quotes.CONTENT_URI,QuoteColumns._ID + " = ?",new String[]{cursor.getString(cursor.getColumnIndex("_id"))});

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.drawable.logo454)
                        .show();
            }
        });
    }
}
