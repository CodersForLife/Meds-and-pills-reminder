package com.helptabteam.helptab;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    FloatingActionButton add;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Cursor mCursor;
    private MyListCursorAdapter myListCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView= (RecyclerView) findViewById(R.id.card);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        add= (FloatingActionButton) findViewById(R.id.add_new);
        Cursor cursor=getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,null,null,null,null);
        getLoaderManager().initLoader(0, null, this);
        //if(cursor.getCount()>0) {
            myListCursorAdapter = new MyListCursorAdapter(this, cursor);
            recyclerView.setAdapter(myListCursorAdapter);
        //}

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddNewDescription.class));
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader=new CursorLoader(this,QuoteProvider.Quotes.CONTENT_URI,null,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        myListCursorAdapter.swapCursor(data);
        mCursor=data;
        if(myListCursorAdapter.getItemCount()==0){
            //TODO as wanted
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myListCursorAdapter.swapCursor(null);
    }
}
