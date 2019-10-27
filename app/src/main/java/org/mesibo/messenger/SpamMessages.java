package org.mesibo.messenger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mesibo.api.Mesibo;

import java.util.ArrayList;

public class SpamMessages extends AppCompatActivity {
    public TextView mEmptyView;
    private ArrayList<Mesibo.UserProfile> mSearchResultList = null;
    RecyclerView mRecyclerView = null;
    private ArrayList<UserProfile> mUserProfiles = null;
    C1257a mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spam2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mEmptyView = (TextView) findViewById(R.id.emptyview_text);
        setEmptyViewText();
        this.mUserProfiles = new ArrayList<>();
        this.mRecyclerView = (RecyclerView) inflate.findViewById(C1196R.C1198id.message_contact_frag_rv);
        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        C1257a aVar = new C1257a(getActivity(), this, this.mUserProfiles, this.mSearchResultList);
        this.mAdapter = aVar;
        this.mRecyclerView.setAdapter(this.mAdapter);

    }

    public void setEmptyViewText() {
        this.mEmptyView.setText("You do not have any spam messages");
    }



}
