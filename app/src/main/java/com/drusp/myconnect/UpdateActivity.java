package com.drusp.myconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adedom.library.Dru;
import com.adedom.library.ExecuteUpdate;

public class UpdateActivity extends AppCompatActivity {

    private EditText mEtUserId;
    private EditText mEtName;
    private EditText mEtImage;
    private Button mBtUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mEtUserId = (EditText) findViewById(R.id.et_user_id);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtImage = (EditText) findViewById(R.id.et_image);
        mBtUpdate = (Button) findViewById(R.id.bt_update);

        mBtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = mEtUserId.getText().toString().trim();
                String name = mEtName.getText().toString().trim();
                String image = mEtImage.getText().toString().trim();

                String sql = "UPDATE `user` SET `name`='" + name + "',`image`='" + image
                        + "' WHERE user_id = '" + userId + "'";
                Dru.connection(ConnectDB.getConnection())
                        .execute(sql)
                        .commit(new ExecuteUpdate() {
                            @Override
                            public void onComplete() {

                                mEtUserId.setText("");
                                mEtName.setText("");
                                mEtImage.setText("");

                                Toast.makeText(getBaseContext(), "Update success", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
