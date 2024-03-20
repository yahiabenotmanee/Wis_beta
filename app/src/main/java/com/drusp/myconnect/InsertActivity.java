package com.drusp.myconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adedom.library.Dru;
import com.adedom.library.ExecuteUpdate;

public class InsertActivity extends AppCompatActivity {

    private EditText mEtName;
    private EditText mEtImage;
    private Button mBtInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mEtName = (EditText) findViewById(R.id.et_name);
        mEtImage = (EditText) findViewById(R.id.et_image);
        mBtInsert = (Button) findViewById(R.id.bt_insert);

        mBtInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo on click

                String name = mEtName.getText().toString().trim();
                String image = mEtImage.getText().toString().trim();

                String sql = "INSERT INTO `user`(`name`, `image`) VALUES ('" + name + "','" + image + "')";
                Dru.connection(ConnectDB.getConnection())
                        .execute(sql)
                        .commit(new ExecuteUpdate() {
                            @Override
                            public void onComplete() {
                                //todo insert success

                                //clear text
                                mEtName.setText("");
                                mEtImage.setText("");

                                Toast.makeText(getBaseContext(), "Insert success", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}
