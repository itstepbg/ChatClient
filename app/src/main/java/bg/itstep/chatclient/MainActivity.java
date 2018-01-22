package bg.itstep.chatclient;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<String> chatMessages = new ArrayList<>();
    private ArrayAdapter chatHistoryAdapter;
    private ListView chatHistory;
    private EditText chatMessage;
    private Button chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the user interface of this activity to the specified XML file.
        setContentView(R.layout.activity_main);

        chatHistory = findViewById(R.id.chat_history);
        chatMessage = findViewById(R.id.chat_message);
        chatButton = findViewById(R.id.chat_button);

        setupChatGUI();
    }

    private void setupChatGUI() {
        chatButton.setOnClickListener(new ChatButtonClickListener());

        chatHistoryAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, chatMessages);
        chatHistory.setAdapter(chatHistoryAdapter);

        chatMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    if (charSequence.charAt(charSequence.length() - 1) == '\n') {
                        sendMessage();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private class ChatButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            sendMessage();
        }
    }

    private void sendMessage() {
        chatMessages.add(chatMessage.getText().toString());
        chatHistoryAdapter.notifyDataSetChanged();
        chatMessage.setText("");

        chatHistory.smoothScrollToPosition(chatMessages.size() - 1);
    }
}