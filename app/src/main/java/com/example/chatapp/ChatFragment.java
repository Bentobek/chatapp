package com.example.chatapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chatapp.databinding.FragmentChatBinding;
import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatAdapter chatAdapter;
    private ArrayList<Message> messageList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(chatAdapter);
    }

    public void addMessage(String message, boolean isUser) {
        messageList.add(new Message(message, isUser));
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        binding.recyclerView.smoothScrollToPosition(messageList.size() - 1);

        if (isUser) {
            messageList.add(new Message( message, false));
            chatAdapter.notifyItemInserted(messageList.size() - 1);
        }
    }
}
