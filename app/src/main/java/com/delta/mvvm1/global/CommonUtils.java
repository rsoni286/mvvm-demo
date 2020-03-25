package com.delta.mvvm1.global;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.Html;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.delta.mvvm1.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class CommonUtils {

    Context mContext;
    private final Gson gson;
    private final SharedPref sharedPref;
    int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    String selectedLauguage = "";
    String selectedCurrency = "";
    String[] var_title_en, var_title;


    public CommonUtils(Context mContext, Gson gson, SharedPref sharedPref) {
        this.mContext = mContext;
        this.gson = gson;
        this.sharedPref = sharedPref;
    }


    public void toastShow(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    /*public String variationData(String title) {
        var_title_en=  mContext.getResources().getStringArray(R.array.var_title_en);
        var_title=  mContext.getResources().getStringArray(R.array.var_title);
        for (int i = 0; i < var_title_en.length; i++) {
            if (var_title_en[i].equalsIgnoreCase(title)) {

                return var_title[i];
            }
        }

        return title;
    }*/


    //check internet connection
    public boolean isNetworkAvailable() {
        /* getting systems Service connectivity manager */
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (mConnectivityManager != null) {
            NetworkInfo[] mNetworkInfos = mConnectivityManager.getAllNetworkInfo();
            if (mNetworkInfos != null) {
                for (int i = 0; i < mNetworkInfos.length; i++) {
                    if (mNetworkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // is service running ? check here
    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    // soft keyboard hide
    public void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        try {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {

        }
    }

    // soft keyboard hide
    public void hideKeyboard(Activity activity, View view) {
        // Check if no view has focus:
        try {
            if (view != null) {
                InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    // soft keyboard show
    public void showKeyboard(Activity activity, View view) {
        // Check if no view has focus:
        try {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        } catch (Exception e) {

        }
    }

    //create dialog
    public ProgressDialog createDialog() {
        ProgressDialog progress = new ProgressDialog(mContext, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        progress.setMessage(Html.fromHtml("<font color='black'  ><big>"
                + "Loading..." + "</big></font>"));
        progress.setCancelable(false);
        return progress;
    }

    public void showDialog(ProgressDialog progressDialog) {
        if (progressDialog != null) {
            if (!progressDialog.isShowing())
                progressDialog.show();
        }
    }

    public void dismissDialog(ProgressDialog progressDialog) {
        if (progressDialog != null) {
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }

    //show simple message print for bottom of screen
    public void snackbar(View coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);

        snackbar.show();
//        snackbar.setActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark1));
    }

    //show simple message print for bottom of screen
    public void internetConnectionSnackbar(View coordinatorLayout) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, mContext.getString(R.string.internet_connection_error), Snackbar.LENGTH_INDEFINITE);

        snackbar.show();

    }//show simple message print for bottom of screen

    public void internetConnectionToast() {
        Toast.makeText(mContext, mContext.getString(R.string.internet_connection_error), Toast.LENGTH_SHORT).show();

    }//show simple message print for bottom of screen

    public void errorToast() {

        Toast.makeText(mContext, mContext.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();

    }//show simple message print for bottom of screen

    public void errorSnackbar(View coordinatorLayout) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, mContext.getString(R.string.something_went_wrong), Snackbar.LENGTH_INDEFINITE);

        snackbar.show();

    }


    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp - 1));
    }

    /*public boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(mContext);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog((Activity) mContext, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }*/


    public boolean isAboveOrEqualLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


    //check GPS or location is on or not
    public boolean canGetLocation() {
        boolean result = true;
        LocationManager lm = null;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        // exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {

        }
        try {
            network_enabled = lm
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (gps_enabled == false || network_enabled == false) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

    public boolean isAboveOrEqualAndroid6() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAboveOrEqualAndroid21() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public Dialog createCustomLoader(Context mContext, boolean isCancelable) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(isCancelable);
        dialog.setContentView(R.layout.loader_progress_dialog);

//        // set values for custom dialog components - text, image and button
//        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
//        //progressbar color
//        if (!CommonUtils.isAboveOrEqualAndroid21())
//            progressBar.setIndeterminateDrawable(ContextCompat.getDrawable(mContext, R.drawable.progress_gold));

//        ImageView imageView = (ImageView) dialog.findViewById(R.id.ivLoader);
//        imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.custom_loader));

//        dialog.show();

        //Grab the window of the dialog, and change the width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        return dialog;
    }

    public void showCustomDialog(Dialog dialog, Context context) {
        if (dialog != null) {
            if (!dialog.isShowing())
                if (!((Activity) context).isFinishing()) {
                    dialog.show();
                }
        }
    }

    public void dismissCustomDialog(Dialog dialog) {
        if (dialog != null) {
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }

    public void changeLayoutAnimation(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeBounds mySwapTransition = new ChangeBounds();
            TransitionManager.beginDelayedTransition(viewGroup, mySwapTransition);
        }
    }

   /* public void showSnackbarMessage(View view, String message, int color) {
        Snackbar snackbar;
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(mContext, color));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            textView.setGravity(Gravity.CENTER_HORIZONTAL);

        textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));


        snackbar.show();
    }
*/

    // EDate format EX : 18 AUG, THU
    public String dateFormatDD_MMM_EEE(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("dd MMM , EEE", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }

    // EDate format EX : Jul 13-Thu
    public String dateFormatMMM_DD_EEE(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("MMM dd-EEE", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }

    // EDate format EX : 2016-09-02
    public String dateFormatYYYY_MM_DD(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }// EDate format EX : 2016-09-02


    public String dateFormatYYYY_MMM_DD(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }// EDate format EX : 2016-09-02

    public String dateFormatDD_M_YYYY(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("dd/M/yyyy", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }

    public String dateFormatEEEE(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }

    // EDate format EX : Jul 13-Thu
    public String dateFormatDD_MMM(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("dd MMM", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }

    // compare two date
    public boolean checkDataCompare(int day, int month, int year, Locale locale) {
//        Calendar cal = new GregorianCalendar(year, month, day);
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        String deliveryDate = formatter.format(calendar.getTime());

        String todayDate = giveDate();


        GlobalLog.e("==>>", todayDate + "  " + deliveryDate);
        if (!todayDate.equals(deliveryDate)) {
            return false;
        } else {
            return true;
        }
    }

    // compare tow time slot
    public boolean checkDataCompare(int day, int month, int year) {
//        Calendar cal = new GregorianCalendar(year, month, day);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        GlobalLog.e("==>>", calendar.getTimeInMillis() + "  " + System.currentTimeMillis());
        if ((calendar.getTimeInMillis() - System.currentTimeMillis()) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(cal.getTime());
    }

    public String getDeviceID() {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    // convert data and time UTC to mobile device timezone
    public String getDate(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM , EEE", Locale.getDefault()); //this format changeable
            formatter.setTimeZone(TimeZone.getDefault());
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            OurDate = "00-00-0000 00:00";
        }
        return OurDate;
    }

    public String dateFormat(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", locale);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }

    // Ex : 2016-12-14T06:05:19.031Z
    public String dateFormatTZ(int day, int month, int year, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return formatter.format(calendar.getTime());
    }

    // 13 Dec, 2016
    // convert data and time UTC to mobile device timezone
    public String getDateFromTZ(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()); //this format changeable
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM , EEE");
            dateFormatter.setTimeZone(TimeZone.getDefault());
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            OurDate = "00-00-0000 00:00";
        }
        return OurDate;
    }

    // 3:15 PM
    // convert data and time UTC to mobile device timezone
    public String getTimeFromTZ(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()); //this format changeable
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
            dateFormatter.setTimeZone(TimeZone.getDefault());
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            OurDate = "00:00";
        }
        return OurDate;
    }

    // 23 Jun, 2016 3:15 PM
    // convert data and time UTC to mobile device timezone
    public String getDateTimeFromTZ(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()); //this format changeable
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("PST"));
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            GlobalLog.e("TAG", e.getMessage());
            OurDate = "00/00/0000 00:00";
        }
        return OurDate;
    }

    // 23 Jun, 2016 3:15 PM
    // convert data and time UTC to mobile device timezone
    public String getDateTimeFromT(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()); //this format changeable
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM , yyyy");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("PST"));
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            GlobalLog.e("TAG", e.getMessage());
            OurDate = "00-00-0000 00:00";
        }
        return OurDate;
    }

    // 23 Jun, 2016 3:15 PM
    // convert data and time UTC to mobile device timezone
    public String getDateandTimeFromT(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()); //this format changeable
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("PST"));
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            GlobalLog.e("TAG", e.getMessage());
            OurDate = "00-00-0000 00:00";
        }
        return OurDate;
    }

    // 23 Jun, 2016 3:15 PM
    // convert data and time UTC to mobile device timezone
    public String getDateTimeFromTForPlaceOrder(String OurDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()); //this format changeable
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(OurDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("PST"));
            OurDate = dateFormatter.format(value);

            //Log.d("OurDate", OurDate);
        } catch (Exception e) {
            GlobalLog.e("TAG", e.getMessage());
            OurDate = "00-00-0000";
        }
        return OurDate;
    }

    public String round(double actualPrice) {

        return new DecimalFormat("##.###").format(actualPrice);
    }

    public float getPriceWithDiscountInFloat(float actualPrice, int percentage) {
        float newPrice = actualPrice - ((actualPrice * percentage) / 100);
//        GlobalLog.e("Price after discount : "+newPrice);
        return newPrice;
    }


    // DP to PX converter
    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }




    /*public void setImageWithLoaderPercentage(String imageURL, ImageView imageView, final ProgressBar progressBar, DisplayImageOptions displayImageOptions) {
        ImageLoader.getInstance()
                .displayImage(imageURL, imageView, displayImageOptions, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        progressBar.setProgress(0);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressBar.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        progressBar.setProgress(Math.round(100.0f * current / total));
                    }
                });
    }*/

}
