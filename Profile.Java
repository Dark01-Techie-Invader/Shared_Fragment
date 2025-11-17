package com.example.assignment_no_02;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Profile_Fragment extends Fragment {

    TextView tvUsername, tvEmail, tvPassword, tvNotifications, tvTheme;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile_, container, false);


        tvUsername = view.findViewById(R.id.username2);
        tvEmail = view.findViewById(R.id.email2);
        tvPassword = view.findViewById(R.id.password2);
        tvNotifications = view.findViewById(R.id.notification2);
        tvTheme = view.findViewById(R.id.theme2); // Add this TextView in XML


        prefs = requireActivity().getSharedPreferences("UserPrefs", MODE_PRIVATE);


        displayPreferences();

        return view;
    }

    private void displayPreferences() {
        String uname = prefs.getString("username", "Not set");
        String mail = prefs.getString("email", "Not set");
        String pass = prefs.getString("password", "Not set");
        boolean notifications = prefs.getBoolean("notifications", false);
        String theme = prefs.getString("theme", "Default"); // Read saved theme


        tvUsername.setText("Username: " + uname);
        tvEmail.setText("Email: " + mail);
        tvPassword.setText("Password: " + pass);
        tvNotifications.setText("Notifications: " + (notifications ? "Enabled" : "Disabled"));
        tvTheme.setText("Theme: " + theme);
    }
}
