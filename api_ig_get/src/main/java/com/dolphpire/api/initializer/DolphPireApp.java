package com.dolphpire.api.initializer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import androidx.core.os.UserManagerCompat;
import androidx.core.util.Preconditions;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dolphpire.api.instance.DolphPireInstance;
import com.dolphpire.api.models.IGAccountModel;
import com.dolphpire.api.models.SyncIGAccount;
import com.dolphpire.api.models.SyncUserModel;
import com.dolphpire.api.models.UserModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static android.content.Context.MODE_PRIVATE;
import static com.dolphpire.api.utils.DolphPireUtils.DPIRE_SP_APP_DATA;
import static com.dolphpire.api.utils.DolphPireUtils.DPIRE_SP_IG_ACCOUNT;
import static com.dolphpire.api.utils.DolphPireUtils.DPIRE_SP_USER_ACCOUNT;

public class DolphPireApp
{

    public static final String TAG = DolphPireApp.class
            .getSimpleName();
    @GuardedBy("LOCK")
    static final Map<String, DolphPireApp> INSTANCES = new ArrayMap<>();
    private static final String LOG_TAG = "DolphPireApp";
    private static final @NonNull
    String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final Object LOCK = new Object();
    private final static AtomicBoolean deleted = new AtomicBoolean();
    private static DolphPireInstance mDolphPireInstance;
    private final Context applicationContext;
    private final String name;
    private final DolphPireOptions options;
    private final AtomicBoolean automaticResourceManagementEnabled = new AtomicBoolean(false);
    private SyncUserModel mSyncUserModel;
    private SyncIGAccount mSyncIGAccount;
    private RequestQueue mRequestQueue;

    @SuppressLint("RestrictedApi")
    protected DolphPireApp(Context applicationContext, String name, DolphPireOptions options)
    {
        this.applicationContext = Preconditions.checkNotNull(applicationContext);
        this.options = Preconditions.checkNotNull(options);
        this.name = null;
    }

    @SuppressLint("RestrictedApi")
    protected DolphPireApp(Context applicationContext, String name)
    {
        this.applicationContext = Preconditions.checkNotNull(applicationContext);
        this.options = null;
        this.name = null;
    }

    @NonNull
    public static List<DolphPireApp> getApps(@NonNull Context context)
    {
        synchronized (LOCK)
        {
            return new ArrayList<>(INSTANCES.values());
        }
    }

    @NonNull
    public static DolphPireApp getInstance()
    {
        synchronized (LOCK)
        {
            DolphPireApp defaultApp = INSTANCES.get(DEFAULT_APP_NAME);
            if (defaultApp == null)
            {
                throw new IllegalStateException(
                        "Default DolphPireApp is not initialized in this "
                                + "process "
                                + ". Make sure to call "
                                + "DolphPireApp.initializeApp(Context) first.");
            }
            return defaultApp;
        }
    }

    public static DolphPireAPI initializeApi()
    {
        synchronized (LOCK)
        {
            if (getInstance().getApplicationContext().getPackageName().equals(getInstance().getApplicationContext().getPackageName()))
            {
                return new DolphPireAPI();
            }
            throw new IllegalStateException("The package is different than the one from the dolphpire-services.json");
        }
    }

    @NonNull
    public static DolphPireApp getInstance(@NonNull String name)
    {
        synchronized (LOCK)
        {
            DolphPireApp dolphpireApp = INSTANCES.get(normalize(name));
            if (dolphpireApp != null)
            {
                return dolphpireApp;
            }

            List<String> availableAppNames = getAllAppNames();
            String availableAppNamesMessage;
            if (availableAppNames.isEmpty())
            {
                availableAppNamesMessage = "";
            } else
            {
                availableAppNamesMessage =
                        "Available app names: " + TextUtils.join(", ", availableAppNames);
            }
            String errorMessage =
                    String.format(
                            "DolphPireApp with name %s doesn't exist. %s", name, availableAppNamesMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    @SuppressLint("RestrictedApi")
    public static DolphPireApp initializeAppB(@NonNull Context context)
    {
        String normalizedName = normalize(DEFAULT_APP_NAME);
        initializeApiData(context);
        Context applicationContext;
        if (context.getApplicationContext() == null)
        {
            applicationContext = context;
        } else
        {
            applicationContext = context.getApplicationContext();
        }

        DolphPireApp dolphpireApp;
        synchronized (LOCK)
        {
            Preconditions.checkState(!INSTANCES.containsKey(normalizedName), "DolphPireApp name " + normalizedName + " already exists!");
            Preconditions.checkNotNull(applicationContext, "Application context cannot be null.");
            dolphpireApp = new DolphPireApp(applicationContext, normalizedName);
            INSTANCES.put(normalizedName, dolphpireApp);
        }
        INSTANCES.put(normalizedName, dolphpireApp);
        getInstance().mSyncUserModel = new SyncUserModel();
        getInstance().mSyncIGAccount = new SyncIGAccount();
        return dolphpireApp;
    }

    @Nullable
    public static DolphPireApp initializeApp(@NonNull Context context)
    {
        synchronized (LOCK)
        {
            if (INSTANCES.containsKey(DEFAULT_APP_NAME))
            {
                return getInstance();
            }
            DolphPireOptions zeoflowOptions = DolphPireOptions.fromResource(context);
            if (zeoflowOptions == null)
            {
                Log.w(
                        LOG_TAG,
                        "Default DolphPireApp failed to initialize because no default "
                                + "options were found. This usually means that com.dolphpire.sdk:dolphpire-services was "
                                + "not applied to your gradle project.");
                return null;
            }
            initializeApiData(context);
            return initializeApp(context, zeoflowOptions);
        }
    }

    @KeepForApi
    private static void initializeApiData(Context mContext)
    {

        mDolphPireInstance = new DolphPireInstance();

        try
        {
            InputStream is = mContext.getAssets().open("dolphpire-services.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            try
            {
                JSONObject obj = new JSONObject(json);
                JSONArray mObj = obj.getJSONArray("client");
                String packageAppApi = mObj.getJSONObject(0).getJSONObject("client_info").getJSONObject("android_client_info").getString("package_name");
                String packageApp = mContext.getApplicationContext().getPackageName();
                String apiKey = mObj.getJSONObject(0).getString("api_key");
                String secret_key = mObj.getJSONObject(0).getString("secret_key");
                mDolphPireInstance.initialize(packageAppApi, packageApp, apiKey, secret_key);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @NonNull
    public static DolphPireApp initializeApp(@NonNull Context context, @NonNull DolphPireOptions options)
    {
        return initializeApp(context, options, "[DEFAULT]");
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    public static DolphPireApp initializeApp(@NonNull Context context, @NonNull DolphPireOptions options, @NonNull String name)
    {
        String normalizedName = normalize(name);
        Context applicationContext;
        if (context.getApplicationContext() == null)
        {
            applicationContext = context;
        } else
        {
            applicationContext = context.getApplicationContext();
        }

        DolphPireApp dolphpireApp;
        synchronized (LOCK)
        {
            Preconditions.checkState(!INSTANCES.containsKey(normalizedName), "DolphPireApp name " + normalizedName + " already exists!");
            Preconditions.checkNotNull(applicationContext, "Application context cannot be null.");
            dolphpireApp = new DolphPireApp(applicationContext, normalizedName, options);
            INSTANCES.put(normalizedName, dolphpireApp);
        }

        dolphpireApp.initializeAllApis();
        return dolphpireApp;
    }

    @SuppressLint("RestrictedApi")
    private static void checkNotDeleted()
    {
        Preconditions.checkState(!deleted.get(), "DolphPireApp was deleted");
    }

    @VisibleForTesting
    public static void clearInstancesForTest()
    {
        // TODO: also delete, once functionality is implemented.
        synchronized (LOCK)
        {
            INSTANCES.clear();
        }
    }

    private static List<String> getAllAppNames()
    {
        List<String> allAppNames = new ArrayList<>();
        synchronized (LOCK)
        {
            for (DolphPireApp app : INSTANCES.values())
            {
                allAppNames.add(app.getName());
            }
        }
        Collections.sort(allAppNames);
        return allAppNames;
    }

    private static String normalize(@NonNull String name)
    {
        return name.trim();
    }

    public void deleteUser()
    {
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(DPIRE_SP_USER_ACCOUNT, null);
        prefsEditor.apply();
    }

    public void setCurrentAccount(IGAccountModel ig_account)
    {

        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(DPIRE_SP_IG_ACCOUNT, new Gson().toJson(ig_account));
        prefsEditor.apply();
        this.syncIGAccount().setIGAccount(ig_account);
    }

    public IGAccountModel getIGAccount()
    {
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE);
        return new Gson().fromJson(mPrefs.getString(DPIRE_SP_IG_ACCOUNT, null), IGAccountModel.class);
    }

    public SyncUserModel syncUser()
    {
        return this.mSyncUserModel;
    }

    public SyncIGAccount syncIGAccount()
    {
        return this.mSyncIGAccount;
    }

    public void clearUserInstance()
    {
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(DPIRE_SP_USER_ACCOUNT, null);
        prefsEditor.putString(DPIRE_SP_IG_ACCOUNT, null);
        prefsEditor.apply();
    }

    public UserModel getUser()
    {
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE);
        return new Gson().fromJson(mPrefs.getString(DPIRE_SP_USER_ACCOUNT, null), UserModel.class);
    }

    public void setUser(UserModel user)
    {
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(DPIRE_SP_USER_ACCOUNT, new Gson().toJson(user));
        prefsEditor.apply();
        this.mSyncUserModel.setUser(user);
    }

    public int getUUID()
    {
        return this.getUser().getUUID();
    }

    public String getApiKey()
    {
        return mDolphPireInstance.getKey();
    }

    public String getSecretKey()
    {
        return mDolphPireInstance.getSecretKey();
    }

    private String getJsonPackage()
    {
        return mDolphPireInstance.getJsonPackage();
    }

    public String getPackage()
    {
        return getApplicationContext().getPackageName();
    }

    @SuppressLint("HardwareIds")
    public String getDeviceID()
    {
        return Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @NonNull
    public Context getApplicationContext()
    {
        checkNotDeleted();
        return applicationContext;
    }

    @NonNull
    public String getName()
    {
        checkNotDeleted();
        return name;
    }

    @NonNull
    public DolphPireOptions getOptions()
    {
        this.checkNotDeleted();
        return this.options;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag)
    {
        if (mRequestQueue != null)
        {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DolphPireApp))
        {
            return false;
        }
        return name.equals(((DolphPireApp) o).getName());
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    public void delete()
    {
        boolean valueChanged = deleted.compareAndSet(false /* expected */, true);
        if (!valueChanged)
        {
            return;
        }

        synchronized (LOCK)
        {
            INSTANCES.remove(this.name);
        }
    }

    @KeepForApi
    @VisibleForTesting
    public boolean isDefaultApp()
    {
        return DEFAULT_APP_NAME.equals(getName());
    }

    private void initializeAllApis()
    {
        boolean inDirectBoot = !UserManagerCompat.isUserUnlocked(applicationContext);
        if (inDirectBoot)
        {
            // Ensure that all APIs are initialized once the user unlocks the phone.
            UserUnlockReceiver.ensureReceiverRegistered(applicationContext);
        } else
        {
            //componentRuntime.initializeEagerComponents(isDefaultApp());
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static class UserUnlockReceiver extends BroadcastReceiver
    {

        private static AtomicReference<UserUnlockReceiver> INSTANCE = new AtomicReference<>();
        private final Context applicationContext;

        public UserUnlockReceiver(Context applicationContext)
        {
            this.applicationContext = applicationContext;
        }

        private static void ensureReceiverRegistered(Context applicationContext)
        {
            if (INSTANCE.get() == null)
            {
                UserUnlockReceiver receiver = new UserUnlockReceiver(applicationContext);
                if (INSTANCE.compareAndSet(null /* expected */, receiver))
                {
                    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_USER_UNLOCKED);
                    applicationContext.registerReceiver(receiver, intentFilter);
                }
            }
        }

        @Override
        public void onReceive(Context context, Intent intent)
        {
            // API initialization is idempotent.
            synchronized (LOCK)
            {
                for (DolphPireApp app : INSTANCES.values())
                {
                    app.initializeAllApis();
                }
            }
            unregister();
        }

        public void unregister()
        {
            applicationContext.unregisterReceiver(this);
        }
    }

    private static class UiExecutor implements Executor
    {
        private static final Handler HANDLER = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command)
        {
            HANDLER.post(command);
        }
    }
}
