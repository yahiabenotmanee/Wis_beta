package com.drusp.myconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adedom.library.Dru;
import com.adedom.library.ExecuteUpdate;

public class DeleteActivity extends AppCompatActivity {

    private EditText mEtUserId;
    private Button mBtDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mEtUserId = (EditText) findViewById(R.id.et_user_id);
        mBtDelete = (Button) findViewById(R.id.bt_delete);

        mBtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = mEtUserId.getText().toString().trim();

                String sql = "DELETE FROM `user` WHERE user_id = '" + userId + "'";
                Dru.connection(ConnectDB.getConnection())
                        .execute(sql)
                        .commit(new ExecuteUpdate() {
                            @Override
                            public void onComplete() {
                                mEtUserId.setText("");
                                Toast.makeText(getBaseContext(), "Delete success", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
