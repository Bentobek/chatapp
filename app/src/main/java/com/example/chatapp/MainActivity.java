package com.example.chatapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chatapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements InputFragment.OnMessageSendListener {

    private ActivityMainBinding binding;
    private ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatFragment = new ChatFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(binding.chatContainer.getId(), chatFragment)
                .replace(binding.inputContainer.getId(), new InputFragment())
                .commit();
    }
    @Override
    public void onMessageSend(String message) {
        if (chatFragment != null) {
            chatFragment.addMessage(message, true); // Передача сообщения в ChatFragment
        }
    }
}
