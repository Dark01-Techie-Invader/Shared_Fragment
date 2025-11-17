package com.example.assignment_no_02;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class User_Setting_Fragment extends Fragment {

    EditText username, email, password;
    Switch notificationSwitch;
    Spinner themeSpinner;
    Button savebt, resetbt;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user__setting_, container, false);

        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        notificationSwitch = view.findViewById(R.id.notificationswitch);
        themeSpinner = view.findViewById(R.id.themeSpinner);
        savebt = view.findViewById(R.id.Savebtn);
        resetbt = view.findViewById(R.id.Resetbtn);


        prefs = requireActivity().getSharedPreferences("UserPrefs", getActivity().MODE_PRIVATE);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.themes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(adapter);


        loadPreferences();


        savebt.setOnClickListener(v -> savePreferences());


        resetbt.setOnClickListener(v -> resetPreferences());

        return view;
    }

    private void loadPreferences() {
        username.setText(prefs.getString("username", ""));
        email.setText(prefs.getString("email", ""));
        password.setText(prefs.getString("password", ""));
        notificationSwitch.setChecked(prefs.getBoolean("notifications", false));

        String savedTheme = prefs.getString("theme", "Light");
        int spinnerPosition = ((ArrayAdapter) themeSpinner.getAdapter()).getPosition(savedTheme);
        themeSpinner.setSelection(spinnerPosition);
    }

    private void savePreferences() {
        String uname = username.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String selectedTheme = themeSpinner.getSelectedItem().toString();

        if (uname.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        prefs.edit()
                .putString("username", uname)
                .putString("email", mail)
                .putString("password", pass)
                .putBoolean("notifications", notificationSwitch.isChecked())
                .putString("theme", selectedTheme)
                .apply();

        Toast.makeText(getActivity(), "Preferences Saved!", Toast.LENGTH_SHORT).show();


        requireActivity().recreate();
    }

    private void resetPreferences() {
        prefs.edit().clear().apply();
        Toast.makeText(getActivity(), "Preferences Cleared", Toast.LENGTH_SHORT).show();

        username.setText("");
        email.setText("");
        password.setText("");
        notificationSwitch.setChecked(false);
        themeSpinner.setSelection(0);

        requireActivity().recreate();
    }
}
