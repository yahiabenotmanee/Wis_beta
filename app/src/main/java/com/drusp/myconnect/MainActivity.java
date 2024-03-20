package com.drusp.myconnect;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adedom.library.Dru;
import com.adedom.library.ExecuteQuery;
import com.adedom.library.ExecuteUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    public String temp="0";



    private Button mBtSelect;
    private Button mBtInsert;
    private Button mBtUpdate;
    private Button mBtDelete;
    private Button mBtRvAndGlide;

    TextView txt_temp,txt_hum,txt_soil;

    private CircularSeekBar circularSeekBar,circularSeekBar2,circularSeekBar3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (ConnectDB.getConnection() == null) {
            Dru.failed(getBaseContext());
        } else {
            Dru.completed(getBaseContext());
        }

        txt_hum=findViewById(R.id.him_text);
        txt_temp=findViewById(R.id.temp_text);
        txt_soil=findViewById(R.id.soil_text);

        circularSeekBar = findViewById(R.id.circularSeekBar);
        circularSeekBar2=findViewById(R.id.circularSeekBar2);
        circularSeekBar3=findViewById(R.id.circularSeekBar3);


//        mBtSelect = (Button) findViewById(R.id.bt_select);
//        mBtInsert = (Button) findViewById(R.id.bt_insert);
//        mBtUpdate = (Button) findViewById(R.id.bt_update);
//        mBtDelete = (Button) findViewById(R.id.bt_delete);
//        mBtRvAndGlide = (Button) findViewById(R.id.bt_rv_and_glide);


        Switch SwitchON_OFF = findViewById(R.id.smsSwitch);
        SwitchON_OFF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int on =1;
                int off=0;
                    if (isChecked) {
//                        String name = mEtName.getText().toString().trim();
//                        String image = mEtImage.getText().toString().trim();
                        String sql = "INSERT INTO `power`(`power`) VALUES ('" + on + "')";
                        Dru.connection(ConnectDB.getConnection())
                                .execute(sql)
                                .commit(new ExecuteUpdate() {
                                    @Override
                                    public void onComplete() {
                                        //todo insert success

                                        //clear text
//                                        mEtName.setText("");
//                                        mEtImage.setText("");

                                        Toast.makeText(getBaseContext(), "Insert success", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        String sql = "INSERT INTO `power`(`power`) VALUES ('" + off + "')";
                        Dru.connection(ConnectDB.getConnection())
                                .execute(sql)
                                .commit(new ExecuteUpdate() {
                                    @Override
                                    public void onComplete() {
                                        //todo insert success

                                        //clear text
//                                        mEtName.setText("");
//                                        mEtImage.setText("");

                                        Toast.makeText(getBaseContext(), "Insert success", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

            }
        });





//        mBtSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //todo on click





        final Handler handler = new Handler();
        final int delay = 3000; // milliseconds

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String sql = "SELECT * FROM status";
                Dru.connection(ConnectDB.getConnection())
                        .execute(sql)
                        .commit(new ExecuteQuery() {
                            @Override
                            public void onComplete(ResultSet resultSet) {
                                try {
                                    while (resultSet.next()) {
                                        //todo date loop row

                                        String name = resultSet.getString(2);

                                        String name2 = resultSet.getString(3);
                                        String name3 = resultSet.getString(4);

                                       // Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();
                                        temp=name;

                                        mycircle_custom(temp,circularSeekBar);
                                        txt_hum.setText(name+"%");
                                        mycircle_custom(name2,circularSeekBar2);
                                        txt_temp.setText(name2+"%");
                                        mycircle_custom(name3,circularSeekBar3);
                                        txt_soil.setText(name3+"%");
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


    // circle creat
    private void mycircle_custom(String Str,CircularSeekBar seekBar){
        if (!Str.isEmpty()) {
            int value = Integer.parseInt(Str);
            if (value >= 0 && value <= 100) {
                float angle = (float) value * 3.6f; // Convert percentage to angle (360 degrees total)
                seekBar.setAngle(angle);
               // txt_him.setText(value+"%");
            } else {
               // EditTextmessage.setError("Enter a value between 0 and 100");
            }
        } else {
           // EditTextmessage.setError("Enter a value");
        }
    }
}
