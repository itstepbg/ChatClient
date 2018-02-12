package bg.itstep.chatclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ITstep on 2/12/2018.
 */

public class UserListActivity extends Activity {

    private ArrayList<String> users = new ArrayList<>();
    private ArrayAdapter usersAdapter;

    private ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the user interface of this activity to the specified XML file.
        setContentView(R.layout.activity_user_list);

        usersList = findViewById(R.id.users_list);
        usersAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);

        usersList.setAdapter(usersAdapter);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(UserListActivity.this, ChatActivity.class);
                UserListActivity.this.startActivity(intent);
            }
        });

        updateUsersList();
    }

    private void updateUsersList() {
        users.add("USER1");
        users.add("USER2");
        users.add("USER3");
        users.add("USER4");

        usersAdapter.notifyDataSetChanged();
    }
}
