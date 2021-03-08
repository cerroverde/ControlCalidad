package com.cupagroup.controlcalidad.sync;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cupagroup.controlcalidad.db.AppDatabase;
import com.cupagroup.controlcalidad.db.entity.Session;
import com.cupagroup.controlcalidad.db.entity.User;
import com.cupagroup.controlcalidad.utils.NetworkController;

import org.json.JSONException;
import org.json.JSONObject;

import static com.cupagroup.controlcalidad.utils.Constants.SERVER_URL;

public class SyncManager extends Application {
    private static AppDatabase mAppDatabase;
    private static SharedPreferences mPreferences;
    private static String mShareFile = "pref_data";

    private static String url_get_usuarios;
    private static String url_get_centros;
    private static String url_put_serialesqr;

    private static RequestQueue mQueue;
    private static Context mContext;

    private static void setURLs() {
        url_get_usuarios = SERVER_URL + "calidad/usuarios";
        url_get_centros = SERVER_URL + "centros";
        url_put_serialesqr = SERVER_URL + "create";
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
        }else {
            mReturn = false;
        }
        return mReturn;
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

}