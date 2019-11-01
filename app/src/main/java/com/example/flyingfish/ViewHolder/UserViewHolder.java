package com.example.flyingfish.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.flyingfish.Interface.UserClickListner;
import com.example.flyingfish.R;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView inputName,inputScore;
    public UserClickListner listner;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        inputName = (TextView) itemView.findViewById(R.id.input_name);
        inputScore = (TextView) itemView.findViewById(R.id.input_score);
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v,getAdapterPosition(),false);
    }
}
