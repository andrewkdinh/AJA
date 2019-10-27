package org.mesibo.messenger;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mesibo.api.Mesibo;
import com.mesibo.emojiview.EmojiconTextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SpamMessages extends AppCompatActivity {
    public TextView mEmptyView;
    private ArrayList<Mesibo.UserProfile> mSearchResultList = null;
    RecyclerView mRecyclerView = null;
    private ArrayList<Mesibo.UserProfile> mUserProfiles = null;
//    RecyclerViewAdapter mAdapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spam2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mEmptyView = (TextView) findViewById(R.id.emptyview_text);
        setEmptyViewText();
        this.mUserProfiles = new ArrayList<>();


        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        this.mAdapter = new RecyclerViewAdapter(this, this, this.mUserProfiles, this.mSearchResultList);
//        this.mAdapter.setClickListener(this);
//        this.mRecyclerView.setAdapter(this.mAdapter);

    }

    public void setEmptyViewText() {
        this.mEmptyView.setText("You do not have any spam messages");
    }
}


