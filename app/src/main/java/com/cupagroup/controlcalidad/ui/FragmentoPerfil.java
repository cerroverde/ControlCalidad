package com.cupagroup.controlcalidad.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cupagroup.controlcalidad.R;
import com.cupagroup.controlcalidad.db.AppDatabase;

import java.util.List;
import java.util.Objects;

/**
 * Fragmento para la pestaña "PERFIL" De la sección "Mi Cuenta"
 */
public class FragmentoPerfil extends Fragment {
    private AppDatabase mAppDatabase;
    private SharedPreferences mPreferences;
    private String mShareFile = "user_data";

    public ImageView icUserSettings;
    public ImageView icPassSettings;
    public TextView tvUserName;
    public TextView tvUserMail;

    public FragmentoPerfil() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppDatabase = AppDatabase.getInstance(requireContext());
        mPreferences = requireContext()
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragmento_perfil, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        icUserSettings = (ImageView) requireView().findViewById(R.id.ic_user_settings);
        icPassSettings = (ImageView) requireView().findViewById(R.id.ic_password_settings);
        tvUserName = (TextView) requireView().findViewById(R.id.texto_nombre);
        tvUserMail = (TextView) requireView().findViewById(R.id.texto_email);

        tvUserMail.setText(mPreferences.getString("user_mail", "Sin correo registrado"));
        tvUserName.setText(mPreferences.getString("user_name", "Usuario no definido"));

        icUserSettings.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                showUserSettings();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showUserSettings(){
        final Dialog dialog = new Dialog(getContext());
        List<String> userEntries = mAppDatabase.getPreferenceUser().getAllEntries();


        dialog.setTitle(R.string.label_user_pref);
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setContentView(R.layout.radiobutton_dialog);

        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radio_group);
        for (int i = 0; i < userEntries.size(); i++){
            RadioButton radioButton = new RadioButton(requireContext());
            long mUserID = mAppDatabase.getPreferenceUser().getIdByName(userEntries.get(i));
            radioButton.setTextSize(18);
            radioButton.setPadding(15,15,15,15);
            radioButton.setId(Math.toIntExact(mUserID));
            radioButton.setText(userEntries.get(i));

            radioGroup.addView(radioButton);


        }
        dialog.show();
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.usuario);
        radioGroup.check((int) mPreferences.getLong("user_id", 1));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int x=0; x < childCount; x++){
                    RadioButton btn = (RadioButton) group.getChildAt(x);
                    if (btn.getId() == checkedId){
                        Long userID = mAppDatabase
                                .getPreferenceUser().getIdByName(btn.getText().toString());
                        String userMail = mAppDatabase.getPreferenceUser().getEmailById(userID);

                        // Save data into preference file "user_data"
                        mPreferences.edit().putString("user_name", btn.getText().toString()).apply();
                        mPreferences.edit().putString("user_mail", userMail).apply();
                        mPreferences.edit().putLong("user_id", userID).apply();

                        // We change name an mail in UI
                        tvUserMail.setText(userMail);
                        tvUserName.setText(btn.getText().toString());

                        /* For development use only
                        Toast.makeText(
                                requireContext(),
                                "Usuario modificado!!!"+mPreferences.getLong("user_id", 1),
                                Toast.LENGTH_LONG
                        ).show();

                         */
                    }
                }
            }
        });
    }
}
