package com.drusp.myconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adedom.library.Dru;
import com.adedom.library.ExecuteQuery;
import com.bumptech.glide.Glide;
import com.drusp.myconnect.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecyclerViewAndGlideActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_and_glide);

        mUsers = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        String sql = "SELECT * FROM user";
        Dru.connection(ConnectDB.getConnection())
                .execute(sql)
                .commit(new ExecuteQuery() {
                    @Override
                    public void onComplete(ResultSet resultSet) {
                        try {

                            while (resultSet.next()) {
                                User user = new User();
                                user.setUserId(resultSet.getString(1));
                                user.setName(resultSet.getString(2));
                                user.setImage(resultSet.getString(3));
                                mUsers.add(user);
                            }
                            mRecyclerView.setAdapter(new CustomAdapter());

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    class CustomAdapter extends RecyclerView.Adapter<CustomHolder> {
        @NonNull
        @Override
        public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new CustomHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
            Glide.with(getBaseContext())
                    .load(ConnectDB.BASE_URL + "/connect/profile/" + mUsers.get(position).getImage())
                    .into(holder.ivImage);

            holder.tvName.setText(mUsers.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
    }

    class CustomHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final TextView tvName;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
