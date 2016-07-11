package com.helptabteam.helptab;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.iconslogo);
        recyclerView= (RecyclerView) findViewById(R.id.card);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        add= (FloatingActionButton) findViewById(R.id.add_new);
        Cursor cursor=getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,null,null,null,null);
        getLoaderManager().initLoader(0, null, this);
        /*ContentValues contentValues=new ContentValues();
        contentValues.put(QuoteColumns.TITLE, "Fever Medicine For Dad");
        contentValues.put(QuoteColumns.DESCRIPTION,"Monday and Sumday 3 times a day");
        contentValues.put(QuoteColumns.START,"Daily dosage-Started from: 8th Jul,2016");
        getContentResolver().insert(QuoteProvider.Quotes.CONTENT_URI,contentValues);*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        switch (id) {

            /*case R.id.edit_detail:
                Intent intent = new Intent(HomeActivity.this, FirstScreen.class);
                startActivity(intent);
                return true;*/
            case R.id.call:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                final SharedPreferences sp=getSharedPreferences("Pref",MODE_PRIVATE);
                callIntent.setData(Uri.parse("tel:"+sp.getString("phone1","")));
                try {
                    if(sp.getString("phone1","").equalsIgnoreCase(""))
                        Toast.makeText(getApplicationContext(),"No number added ",Toast.LENGTH_SHORT).show();
                    else
                        startActivity(callIntent);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
