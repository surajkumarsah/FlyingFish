package com.example.flyingfish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.flyingfish.Model.UserView;
import com.example.flyingfish.ViewHolder.UserViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeaderBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference Userref;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        Userref = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<UserView> options = new FirebaseRecyclerOptions.Builder<UserView>().setQuery(Userref, UserView.class).build();
        FirebaseRecyclerAdapter<UserView, UserViewHolder> adapter = new FirebaseRecyclerAdapter<UserView, UserViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i, @NonNull UserView userView) {

                userViewHolder.inputName.setText(userView.getName());
                userViewHolder.inputScore.setText(userView.getScore());
            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leadershow_layout, parent, false);
                UserViewHolder holder = new UserViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
