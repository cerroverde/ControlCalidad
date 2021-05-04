package com.cupagroup.controlcalidad.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.cupagroup.controlcalidad.R;
import com.cupagroup.controlcalidad.db.AppDatabase;
import com.cupagroup.controlcalidad.sync.SyncManager;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;

import java.security.PrivateKey;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.cupagroup.controlcalidad.sync.SyncManager.sendDataSsesion;
import static com.cupagroup.controlcalidad.sync.SyncManager.sessionCycle;

public class FragmentoMiscelaneo extends Fragment {
    private AppDatabase mAppDatabase;
    private SharedPreferences mPreferences;
    private SharedPreferences mDataShared;
    private SharedPreferences mDataUser;
    private String mShareFile = "pref_data";
    private String mShareCampos = "campos_data";
    private String mShareUser = "user_data";
    private Editable mComments;
    private Integer tFallos;

    // Campos respectivos de un item
    public TextView iControl;
    public TextView iCanteras;
    public TextView iPiezas;
    public TextView iFallos;
    public ImageView iComment;
    public ImageView iCommentIcon;
    public ImageView iFallosIcon;
    public TextView iCommentText;
    public Button iEndSession;
    public Button iNewEnsayo;


    public Handler mHandler= new Handler();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = requireContext()
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);
        mDataShared = requireContext()
                .getSharedPreferences(mShareCampos, Context.MODE_PRIVATE);
        mDataUser = requireContext()
                .getSharedPreferences(mShareUser, Context.MODE_PRIVATE);
        tFallos = 0;

    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragmento_comentario, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAppDatabase = AppDatabase.getInstance(this.getContext());

        iControl = (TextView) requireView().findViewById(R.id.info_numero_control);
        iCanteras = (TextView) requireView().findViewById(R.id.info_cantera);
        iPiezas = (TextView) requireView().findViewById(R.id.info_piezas);
        iFallos = (TextView) requireView().findViewById(R.id.info_fallos);
        iComment = (ImageView) requireView().findViewById(R.id.icono_indicador_derecho);
        iCommentIcon = (ImageView) requireView().findViewById(R.id.icono_password);
        iCommentText = (TextView) requireView().findViewById(R.id.info_comment_text);
        iEndSession = (Button) requireView().findViewById(R.id.btn_end_session);
        iNewEnsayo = (Button) requireView().findViewById(R.id.btn_end_ensayo);
        iFallosIcon = (ImageView) requireView().findViewById(R.id.info_fallos_icon);

        Map<String, ?> entries = mDataShared.getAll();
        Set<String> keys = entries.keySet();

        String width = mPreferences.getString("LARGO DE LA PIEZA", "None");
        String height = mPreferences.getString("ANCHO DE LA PIEZA", "None");
        String tComment = mPreferences.getString("comentario", "None");
        Long tControl = mPreferences.getLong("sessionId", 99);
        if (tControl.equals(99L)){
            sessionCycle(tControl);
            tControl = mPreferences.getLong("sessionId", 99);
        }

        String tCantera = mDataUser.getString("cantera_name", "Sin definir");
        String tNave = mDataUser.getString("nave", "Sin definir");

        tCantera = tCantera + " / " + tNave;
        Integer tPiezas = mAppDatabase.getQualityControl().getCountControls(tControl);


        // Stablish color depending of state of the comment
        if (tComment.equals("None")) {
            iCommentIcon.setColorFilter(
                    ContextCompat.getColor(getContext(), R.color.red),
                    PorterDuff.Mode.SRC_IN);
        }else {
            iCommentIcon.setColorFilter(
                    ContextCompat.getColor(getContext(), R.color.green),
                    PorterDuff.Mode.SRC_IN);
        }

        if (entries.size() < 1){
            tFallos = 0;
            Log.i("Shared Data", "No se ha modificado ningun valor");
        }else {
            for (String key : keys){
                if (key.equals("FORMA")
                        || key.equals("ESPESOR")
                        || key.equals("CALIDAD")
                        || key.equals("LARGO DE LA PIEZA")
                        || key.equals("ANCHO DE LA PIEZA")
                ){
                    String f = mPreferences.getString(key,"Sin Valor");

                }else{
                    String f = mDataShared.getString(key,"Sin Valor");
                    int m = Integer.parseInt(f);
                    // Se restan los tres elementos Forma, Espesor y Calidad
                    // ya que estos se declararon en ShareData por necesidad del
                    // ID position para recolocar el item declarado anteriormente
                    tFallos = tFallos + m ;
                }
            }
        }

        // Define the fallos Icon color depending of the sum of all
        if (tFallos >= 0 && tFallos <= 8){
            iFallosIcon.setColorFilter(
                    ContextCompat.getColor(getContext(), R.color.green),
                    PorterDuff.Mode.SRC_IN);
        }else if( tFallos >= 9 && tFallos <= 12){
            iFallosIcon.setColorFilter(
                    ContextCompat.getColor(getContext(), R.color.yellow),
                    PorterDuff.Mode.SRC_IN);
        }else {
            iFallosIcon.setColorFilter(
                    ContextCompat.getColor(getContext(), R.color.red),
                    PorterDuff.Mode.SRC_IN);
        }

        iControl.setText(String.format(Locale.getDefault(),"Nº de control: %d", tControl));
        iCanteras.setText(String.format("Cantera: %s", tCantera));
        iPiezas.setText(String.format("Nº de piezas evaluadas: %s", tPiezas));
        iFallos.setText(String.format(Locale.getDefault(),"Total de fallos %d", tFallos));


        // Press Comment Button
        iComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( dialogComment() ){
                    iCommentIcon.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.green),
                            PorterDuff.Mode.SRC_IN);
                }else {
                    iCommentIcon.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.red),
                            PorterDuff.Mode.SRC_IN);
                }
            }
        });

        // Press End Session Button
        iEndSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(width.equals("None") || height.equals("None")){
                    Snackbar snackbar = Snackbar.make(
                            v,
                            "Falta declarar un comentario o la pieza carece de medidas\n"+ tComment,
                            Snackbar.LENGTH_LONG
                    );
                    snackbar.show();

                }else{
                    LoadingDialog loadingDialog = new LoadingDialog(v.getContext(), v);
                    loadingDialog.setCancelable(false);
                    loadingDialog.setCanceledOnTouchOutside(false);
                    loadingDialog.show();
                    loadingDialog.setFeatureDrawableResource(
                            Window.FEATURE_LEFT_ICON,
                            R.drawable.sync_black_36dp
                    );
                }
            }
        });

        iNewEnsayo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SyncManager.ensayoCycle(v).syncLocalToServer();
            }
        });
    }




    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager imm = (InputMethodManager)
                getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0 );

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i("RESUME", "AQUI SE RESUMIO OnPause");
    }

    public class LoadingDialog extends Dialog{
        private Context mContext;
        private View mView;
        private int pStatus = 0;
        private TextView tv;

        public LoadingDialog(Context context, View v){
            super(context);
            mContext = context;
            mView = v;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_LEFT_ICON);
            setTitle("Procesando");


            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View inflateView = inflater.inflate(R.layout.loading_dialog, (ViewGroup) findViewById(R.id.loading_cont));
            setContentView(inflateView);


            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.circular_progress);
            final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
            mProgress.setProgress(0);
            mProgress.setSecondaryProgress(100);
            mProgress.setMax(100);
            mProgress.setProgressDrawable(drawable);
            tv = (TextView) findViewById(R.id.tv);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (pStatus < 100){
                        pStatus += 1;
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mProgress.setProgress(pStatus);
                                tv.setText(pStatus + "%");
                            }
                        });
                        try {
                            Thread.sleep(26);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAppDatabase.getQualityControl()
                                    .insert(new SyncManager.ensayoCycle(mView).getDataQuality());
                            new sendDataSsesion(mView).execute();
                            dismiss();
                        }
                    });
                }
            }).start();
        }
    }

    public boolean dialogComment(){
        String savedComment = mPreferences.getString("comentario", "");
        Boolean mResult;
        final AlertDialog dialogBuilder =
                new AlertDialog.Builder(
                        requireContext(),
                        R.style.MyAlertDialogStyle).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
        editText.setText(savedComment);
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getContext(),
                        "No se realizan cambios en el comentario",
                        Toast.LENGTH_LONG
                ).show();
                mPreferences.edit().putString("comentario","None").apply();

                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mComments = editText.getText();
                if ( String.valueOf(mComments).equals("") || String.valueOf(mComments).isEmpty()){
                    Toast.makeText(
                            getContext(),
                            "Es mejor decir algo que no decir nada!!!",
                            Toast.LENGTH_LONG
                    ).show();
                }else {
                    mPreferences.edit().putString("comentario", String.valueOf(mComments)).apply();
                    iCommentIcon.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.green),
                            PorterDuff.Mode.SRC_IN);

                    dialogBuilder.dismiss();
                }
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();


        mResult = !mPreferences.getString("comentario", "None").equals("None");
        return mResult;
    }
}
