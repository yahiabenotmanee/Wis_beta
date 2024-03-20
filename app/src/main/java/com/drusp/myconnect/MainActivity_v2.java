package com.drusp.myconnect;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adedom.library.Dru;
import com.adedom.library.ExecuteQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity_v2 extends AppCompatActivity {

    private Button mBtSelect;
    private Button mBtInsert;
    private Button mBtUpdate;
    private Button mBtDelete;
    private Button mBtRvAndGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ConnectDB.getConnection() == null) {
            Dru.failed(getBaseContext());
        } else {
            Dru.completed(getBaseContext());
        }

//        mBtSelect = (Button) findViewById(R.id.bt_select);
//        mBtInsert = (Button) findViewById(R.id.bt_insert);
//        mBtUpdate = (Button) findViewById(R.id.bt_update);
//        mBtDelete = (Button) findViewById(R.id.bt_delete);
//        mBtRvAndGlide = (Button) findViewById(R.id.bt_rv_and_glide);

//        mBtSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //todo on click





        final Handler handler = new Handler();
        final int delay = 3000; // milliseconds

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String sql = "SELECT * FROM user";
                Dru.connection(ConnectDB.getConnection())
                        .execute(sql)
                        .commit(new ExecuteQuery() {
                            @Override
                            public void onComplete(ResultSet resultSet) {
                                try {
                                    while (resultSet.next()) {
                                        //todo date loop row

                                        String name = resultSet.getString(2);
                                        Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();

                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                handler.postDelayed(this, delay); // Repeat every 3 seconds
            }
        }, delay);














//                String sql = "SELECT * FROM user";
//                Dru.connection(ConnectDB.getConnection())
//                        .execute(sql)
//                        .commit(new ExecuteQuery() {
//                            @Override
//                            public void onComplete(ResultSet resultSet) {
//                                try {
//                                    while (resultSet.next()) {
//                                        //todo date loop row
//
//                                        String name = resultSet.getString(2);
//                                        Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();
//
//                                    }
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//            }
//        });

//        mBtInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), InsertActivity.class));
//            }
//        });
//
//        mBtUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), UpdateActivity.class));
//            }
//        });
//
//        mBtDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), DeleteActivity.class));
//            }
//        });
//
//        mBtRvAndGlide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), RecyclerViewAndGlideActivity.class));
//            }
//        });
    }
}
