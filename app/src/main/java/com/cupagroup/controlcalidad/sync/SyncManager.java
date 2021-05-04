package com.cupagroup.controlcalidad.sync;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cupagroup.controlcalidad.R;
import com.cupagroup.controlcalidad.db.AppDatabase;
import com.cupagroup.controlcalidad.db.entity.Calidad;
import com.cupagroup.controlcalidad.db.entity.Canteras;
import com.cupagroup.controlcalidad.db.entity.Espesor;
import com.cupagroup.controlcalidad.db.entity.Formas;
import com.cupagroup.controlcalidad.db.entity.Naves;
import com.cupagroup.controlcalidad.db.entity.QualityControl;
import com.cupagroup.controlcalidad.db.entity.Session;
import com.cupagroup.controlcalidad.db.entity.User;
import com.cupagroup.controlcalidad.ui.FragmentoCategorias;
import com.cupagroup.controlcalidad.utils.NetworkController;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.cupagroup.controlcalidad.utils.Constants.SERVER_URL;

public class SyncManager extends Application {
    private static AppDatabase mAppDatabase;
    private static SharedPreferences mPreferences;
    private static SharedPreferences mDataShared;
    private static SharedPreferences mDataUser;
    private static String mShareFile = "pref_data";
    private static String mShareCampos = "campos_data";
    private static String mShareUser = "user_data";

    private static String url_get_usuarios;
    private static String url_get_formas;
    private static String url_get_espesor;
    private static String url_get_quality;
    private static String url_post_ensayo;
    private static String url_get_naves;
    private static String url_get_canteras;

    private static RequestQueue mQueue;
    private static Context mContext;
    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    private static void setURLs() {
        url_get_usuarios = SERVER_URL + "calidad/usuarios";
        url_get_formas = SERVER_URL + "calidad/forma";
        url_get_espesor = SERVER_URL + "calidad/espesor";
        url_get_quality = SERVER_URL + "calidad/quality";
        url_post_ensayo = SERVER_URL + "calidad/ensayo";
        url_get_naves = SERVER_URL + "calidad/naves";
        url_get_canteras = SERVER_URL + "calidad/canteras";

    }

    /**
     * Check if there any connection to internet
     * @param context
     * @return
     */
    //TODO hay que cambiar esta funcion por NetworkCallback {investigar}
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void savedParamShared(String key, String value){
        mPreferences = SyncManager.mContext
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getHeightUnit(String key){
        mPreferences = SyncManager.mContext
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);

        return mPreferences.getString(key, "Null");
    }

    public static Boolean syncAll(Context context, Long sessionActive){
        Boolean mReturn = false;
        SyncManager.mContext = context;
        mAppDatabase = AppDatabase.getInstance(context);
        mPreferences = SyncManager.mContext
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);

        if (sessionActive.equals(Long.valueOf(99))){
            Long sessionID = mAppDatabase.getSession().getIdFromActiveSession();
            if (sessionID == null || sessionID == 99 ){
                Session session = new Session( true);
                mAppDatabase.getSession().insert(session);
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putLong(
                        "sessionId",
                        mAppDatabase.getSession().getIdFromActiveSession());
                editor.apply();

                mReturn = true;
            }else{
                Log.e("SyncAll",
                        "Session is already active with NUMBER: " + sessionID);
                mReturn = false;
            }
        }else{
            Log.e("SyncAll",
                    "Session is already active with number: " + sessionActive);
            mReturn = false;
        }

        if (isNetworkAvailable(context)){
            setURLs();
            syncUsuarios();
            syncFormas();
            syncEspesor();
            syncQuality();
            syncNaves();
            syncCanteras();
        }else {
            mReturn = false;
        }
        return mReturn;
    }

    private static void syncNaves(){
        mQueue = NetworkController.getInstance(SyncManager.mContext).getRequestQueue();
        JsonArrayRequest newReq = new JsonArrayRequest(
                url_get_naves,
                response -> {
                    mAppDatabase.getNaves().deleteAll();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = response.getJSONObject(i);
                            Naves naves = new Naves(
                                    obj.getLong("id"),
                                    obj.getLong("id_cantera"),
                                    obj.getString("name"),
                                    obj.getString("address"),
                                    obj.getString("phone")
                            );
                            mAppDatabase.getNaves().insert(naves);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(
                                "SyncNaves",
                                "Error encoutered on Sync Naves with error "
                                        + error.getMessage()
                        );
                    }
                }
        );
        mQueue.add(newReq);
    }

    private static void syncCanteras(){
        mQueue = NetworkController.getInstance(SyncManager.mContext).getRequestQueue();
        JsonArrayRequest newReq = new JsonArrayRequest(
                url_get_canteras,
                response -> {
                    mAppDatabase.getCanteras().deleteAll();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = response.getJSONObject(i);
                            Canteras canteras = new Canteras(
                                    obj.getLong("id"),
                                    obj.getString("name")
                            );
                            mAppDatabase.getCanteras().insert(canteras);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(
                                "SyncCanteras",
                                "Error encoutered on Sync Canteras with error "
                                        + error.getMessage()
                        );
                    }
                }
        );
        mQueue.add(newReq);
    }

    private static void syncUsuarios(){
        mQueue = NetworkController.getInstance(SyncManager.mContext).getRequestQueue();
        JsonArrayRequest newReq = new JsonArrayRequest(
                url_get_usuarios,
                response -> {
                    mAppDatabase.getPreferenceUser().deleteAll();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = response.getJSONObject(i);
                            User user = new User(
                                    obj.getLong("user_id"),
                                    obj.getString("username"),
                                    obj.getString("email"),
                                    obj.getString("password")
                            );
                            mAppDatabase.getPreferenceUser().insert(user);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(
                                "SyncUsers",
                                "Error encoutered on Sync Users with error "
                                        + error.getMessage()
                        );

                    }
                }
        );
        mQueue.add(newReq);
    }

    private static void syncFormas(){
        mQueue = NetworkController.getInstance(SyncManager.mContext).getRequestQueue();
        JsonArrayRequest newReq = new JsonArrayRequest(
                url_get_formas,
                response -> {
                    mAppDatabase.getFormas().deleteAll();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = response.getJSONObject(i);
                            Formas formas = new Formas(
                                    obj.getLong("forma_id"),
                                    obj.getString("forma_name")
                            );
                            mAppDatabase.getFormas().insert(formas);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(
                                "SyncFormas",
                                "Error encoutered on Sync Formas with error "
                                        + error.getMessage()
                        );

                    }
                }
        );
        mQueue.add(newReq);
    }

    private static void syncEspesor(){
        mQueue = NetworkController.getInstance(SyncManager.mContext).getRequestQueue();
        JsonArrayRequest newReq = new JsonArrayRequest(
                url_get_espesor,
                response -> {
                    mAppDatabase.getEspesor().deleteAll();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = response.getJSONObject(i);
                            Espesor espesor = new Espesor(
                                    obj.getLong("espesor_id"),
                                    obj.getString("espesor_name")
                            );
                            mAppDatabase.getEspesor().insert(espesor);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(
                                "SyncEspesor",
                                "Error encoutered on Sync Espesor with error "
                                        + error.getMessage()
                        );

                    }
                }
        );
        mQueue.add(newReq);
    }

    private static void syncQuality(){
        mQueue = NetworkController.getInstance(SyncManager.mContext).getRequestQueue();
        JsonArrayRequest newReq = new JsonArrayRequest(
                url_get_quality,
                response -> {

                    mAppDatabase.getCalidad().deleteAll();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = response.getJSONObject(i);
                            Calidad calidad = new Calidad(
                                    obj.getLong("quality_id"),
                                    obj.getString("quality_name")
                            );
                            mAppDatabase.getCalidad().insert(calidad);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(
                                "SyncQuality",
                                "Error encoutered on Sync Quality with error "
                                        + error.getMessage()
                        );
                    }
                }
        );
        mQueue.add(newReq);
    }

    public static class sendDataSsesion extends AsyncTask<String, String, String>  {
        private final FragmentManager fragmentManager;
        private JSONObject json;

        public sendDataSsesion(View view) {
            this.fragmentManager =
                    ((AppCompatActivity)view.getRootView().getContext())
                            .getSupportFragmentManager();
        }

        @Override
        protected String doInBackground(String... strings) {
            setURLs();
            mPreferences = SyncManager.mContext
                    .getSharedPreferences(mShareFile, MODE_PRIVATE);
            mDataShared = SyncManager.mContext
                    .getSharedPreferences(mShareCampos, MODE_PRIVATE);
            mDataUser = SyncManager.mContext
                    .getSharedPreferences(mShareUser, MODE_PRIVATE);

            JSONParser jsonParser = new JSONParser();
            List<QualityControl> mData = mAppDatabase.getQualityControl()
                    .getAllSessionById(mPreferences.getLong("sessionId", 99));
            String mCanteraName = mDataUser.getString("cantera_name",
                    "None");
            Long mUserID = mDataUser.getLong("user_id", 0);


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //Map<String, ?> entries = mDataShared.getAll();
            //Set<String> keys = entries.keySet();

            for (int i = 0; mData.size() > i; i++){
                Log.i("DATA", "Show data SIZE: "+ mData.size());
                params.add(
                        new BasicNameValuePair(
                                "id_session",
                                String.valueOf(mData.get(i).getSession_id())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "id_ensayo",
                                String.valueOf(mData.get(i).getEnsayo_id())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "id_user",
                                String.valueOf(mUserID)
                        )
                );
                params.add( new BasicNameValuePair("cantera_name", mCanteraName));
                params.add(
                        new BasicNameValuePair(
                                "nave_name",
                                mDataUser.getString("nave", "Sin definir en Preferences")
                        )
                );

                params.add(new BasicNameValuePair("witdh", mData.get(i).getSize_width()));
                params.add(new BasicNameValuePair("heigth", mData.get(i).getSize_height()));
                params.add(new BasicNameValuePair("forma", mData.get(i).getForma()));
                params.add(
                        new BasicNameValuePair(
                                "espesor",
                                String.valueOf(mData.get(i).getEspesor())
                        )
                );
                params.add(new BasicNameValuePair("calidad", mData.get(i).getCalidad()));
                params.add(new BasicNameValuePair("comentario", mData.get(i).getComment()));
                params.add(
                        new BasicNameValuePair(
                                "chaflan",
                                String.valueOf(mData.get(i).getChaflan())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "roturas",
                                String.valueOf(mData.get(i).getRoturas())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "malcortada",
                                String.valueOf(mData.get(i).getCorte())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "fina",
                                String.valueOf(mData.get(i).getFina())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "gruesa",
                                String.valueOf(mData.get(i).getGruesa())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "refollo",
                                String.valueOf(mData.get(i).getRefolio())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "fallaescuadra",
                                String.valueOf(mData.get(i).getEscuadra())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "torcida",
                                String.valueOf(mData.get(i).getTorcida())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "nudos",
                                String.valueOf(mData.get(i).getNudos())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "piritasoxidables",
                                String.valueOf(mData.get(i).getPiritas())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "diferenciasdecolor",
                                String.valueOf(mData.get(i).getColor())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "vetas",
                                String.valueOf(mData.get(i).getVetas())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "flor",
                                String.valueOf(mData.get(i).getFlor())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "malsonido",
                                String.valueOf(mData.get(i).getSonido())
                        )
                );
                params.add(
                        new BasicNameValuePair(
                                "cortescuarzo",
                                String.valueOf(mData.get(i).getCortescuarzo())
                        )
                );

                // getting JSON Object
                // Note that create product url accepts POST method
                //Log.i("PARAMS", "Size of PARAMS: "+ params.size());

                if (mCanteraName.equals("none")){
                    Toast.makeText(SyncManager.mContext,
                            "Para enviar la data, es necesario definir una Cantera y Nave",
                            Toast.LENGTH_LONG).show();
                }else {
                    json = jsonParser.makeHttpRequest(url_post_ensayo, "POST", params);
                }

            }


            String retorno = "false";
            // check log cat for response
            //Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1){
                    retorno = "true";
                    Log.i("Http Response", "Sending data Successfully");

                }else {
                    retorno = "false";
                    Log.i("Http Response", "Sending data UnSuccessfully");
                }
            }catch (JSONException e){
                e.printStackTrace();
                retorno = "false";
            }

            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == "true"){
                if ( sessionCycle(mPreferences.getLong("sessionId", 99))){
                    mPreferences.edit().clear().apply();
                    mDataShared.edit().clear().apply();

                    // Actualizamos la variable en Preferencias
                    mPreferences.edit().putLong(
                            "sessionId",
                            mAppDatabase.getSession().getIdFromActiveSession()
                    ).apply();

                    //Una vez listo, nos vamos a nuevo ensayo
                    Fragment fragmentoGenerico = new FragmentoCategorias();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.contenedor_principal, fragmentoGenerico)
                            .commit();
                }else {
                    Toast.makeText(SyncManager.mContext,
                            "Unexpected error recycling session //onPostExecute - SyncManager",
                            Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(SyncManager.mContext,
                        "Unexpected error sending data //onPostExecute - SyncManager",
                        Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }

    // Gestion de la session
    public static Boolean sessionCycle(Long mIdSession){
        boolean active = false;

        if (mIdSession == 99) {
            Log.i("SESSION STATUS ERROR: ",
                    "Deberia existir una sesion con valor 1 en su status y tiene valor = " + mIdSession);
            active = false;

        }else{
            int isSessionActive = mAppDatabase.getSession().disableActiveSession(mIdSession);
            Log.i("SessionActive", "Value: "+ isSessionActive);

            //Insert new Session
            Session session = new Session( true);
            mAppDatabase.getSession().insert(session);

            //Declare in Preferences the new session
            mIdSession = mAppDatabase.getSession().getIdFromActiveSession();
            mPreferences.edit().putLong("sessionId", mIdSession).apply();

            // Log just for review
            Log.i("Session ID", "Value: "+ mIdSession);

            active = true;

        }
        return active;
    }

    public static class ensayoCycle {
        private final FragmentManager fragmentManager;
        private QualityControl dataQuality = new QualityControl();

        public ensayoCycle(View view){
            this.fragmentManager = ((AppCompatActivity) view.getRootView().getContext())
                    .getSupportFragmentManager();
            mDataShared = SyncManager.mContext
                    .getSharedPreferences(mShareCampos, MODE_PRIVATE);
            mPreferences = SyncManager.mContext
                    .getSharedPreferences(mShareFile, MODE_PRIVATE);
        }

        public QualityControl getDataQuality() {
            Long session = mPreferences.getLong("sessionId", 99);
            Long ensayo = mPreferences.getLong("ensayoId", 0);
            String width = mPreferences.getString("LARGO DE LA PIEZA", "None");
            String height = mPreferences.getString("ANCHO DE LA PIEZA", "None");
            String forma = mPreferences.getString("FORMA", "None");
            String espesor = mPreferences.getString("ESPESOR", "None");
            String calidad = mPreferences.getString("CALIDAD", "None");
            String comment = mPreferences.getString("comentario","Ninguno");

            dataQuality.setSession_id(session);
            dataQuality.setEnsayo_id(ensayo);
            dataQuality.setSize_width(width);
            dataQuality.setSize_height(height);
            dataQuality.setForma(forma);
            dataQuality.setEspesor(Double.valueOf(espesor));
            dataQuality.setCalidad(calidad);
            dataQuality.setComment(comment);

            Map<String, ?> entries = mDataShared.getAll();
            Set<String> keys = entries.keySet();
            for (String key : keys) {
                String f = mDataShared.getString(key, "Sin Valor");
                int m = Integer.parseInt(f);
                Log.i("Shared Data", key + " VALORES " + f);

                switch (key){
                    case "CHAFLAN":
                        dataQuality.setChaflan(Integer.parseInt(f));
                        break;
                    case "ROTURAS":
                        dataQuality.setRoturas(Integer.parseInt(f));
                        break;
                    case "MAL CORTADA":
                        dataQuality.setCorte(Integer.parseInt(f));
                        break;
                    case "FINA":
                        dataQuality.setFina(Integer.parseInt(f));
                        break;
                    case "GRUESA":
                        dataQuality.setGruesa(Integer.parseInt(f));
                        break;
                    case "REFOLLO":
                        dataQuality.setRefolio(Integer.parseInt(f));
                        break;
                    case "FALSA ESCUADRA":
                        dataQuality.setEscuadra(Integer.parseInt(f));
                        break;
                    case "TORCIDA":
                        dataQuality.setTorcida(Integer.parseInt(f));
                        break;
                    case "NUDOS":
                        dataQuality.setNudos(Integer.parseInt(f));
                        break;
                    case "PIRITAS OXIDABLES":
                        dataQuality.setPiritas(Integer.parseInt(f));
                        break;
                    case "DIFERENCIAS DE COLOR":
                        dataQuality.setColor(Integer.parseInt(f));
                        break;
                    case "VETAS":
                        dataQuality.setVetas(Integer.parseInt(f));
                        break;
                    case "FLOR":
                        dataQuality.setFlor(Integer.parseInt(f));
                        break;
                    case "MAL SONIDO":
                        dataQuality.setSonido(Integer.parseInt(f));
                        break;
                    case "CORTES Y CUARZOS":
                        dataQuality.setCortescuarzo(Integer.parseInt(f));
                }
            }

            return dataQuality;
        }

        public void syncLocalToServer(){
            mAppDatabase.getQualityControl().insert(getDataQuality());
            mDataShared.edit().clear().apply();
            mPreferences.edit().clear().apply();

            //Nuevas variables
            Long nSession = mAppDatabase.getSession().getIdFromActiveSession();
            int nEnsayo = mAppDatabase.getQualityControl().getLastEnsayoIdOfSession(nSession)+1;

            // Actualizamos la variable en Preferencias
            mPreferences.edit().putLong("sessionId", nSession).apply();
            mPreferences.edit().putLong("ensayoId",nEnsayo).apply();



            //Una vez listo, nos vamos a nuevo ensayo
            Fragment fragmentoGenerico = new FragmentoCategorias();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }
    }
}