package com.example.chatapp;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chatapp.databinding.FragmentInputBinding;

public class InputFragment extends Fragment {

    private FragmentInputBinding binding;
    private OnMessageSendListener messageSendListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnMessageSendListener) {
            messageSendListener = (OnMessageSendListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnMessageSendListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSend.setOnClickListener(v -> {
            String message = binding.editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                messageSendListener.onMessageSend(message);
                binding.editTextMessage.setText("");
            }
        });
    }

    public interface OnMessageSendListener {
        void onMessageSend(String message);
    }
}
