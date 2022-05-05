package com.git.crm.Login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.git.crm.MainActivity
import com.git.crm.Pojo.LoginDetails
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {
    private var deviceId: String?=""
    private lateinit var preferenceObj: PreferenceRequestHelper
    val presenter = LoginPresenter(this, LoginInteractor())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferenceObj = PreferenceRequestHelper(this)
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as
                TelephonyManager
        deviceId = getIMEI()
       // Toast.makeText(applicationContext,deviceId,Toast.LENGTH_SHORT).show()
        tvLogin.setOnClickListener {
            if(NetworkConnection.isNetworkAvailable(applicationContext))
            presenter.validateCredentials(edtUsername.text.toString(), edtPassword.text.toString(),deviceId)
            else  Toast.makeText(applicationContext, "No internet", Toast.LENGTH_SHORT).show()
        }

    }

    override fun showProgress() {
        progress.visibility=View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility=View.GONE
    }

    override fun navigateToHome(login: List<LoginDetails>?) {

        preferenceObj.setValue(Constants.PRES_ID, login?.get(0)?.userCode.toString())
        preferenceObj.setValue(
            Constants.PRES_NAME,
            login?.get(0)?.firstName + " " + login?.get(0)?.lastName
        )
        preferenceObj.setValue(Constants.PRES_MOBILE, login?.get(0)?.mobileNumber)
        //preferenceObj.setValue(Constants.PRES_ADDRESS,  login?.get(0)?.a)
        preferenceObj.setValue(Constants.PRES_EMAIL, login?.get(0)?.eMail)
        preferenceObj.setValue(Constants.PRES_Branch, login?.get(0)?.Branch)
        preferenceObj.setValue(Constants.PRES_USERTYPE, login?.get(0)?.User_Type)
        preferenceObj.setValue(Constants.PRES_BRANCH_ID, login?.get(0)?.BranchID.toString())
        preferenceObj.setValue(Constants.DASHBOARD, "Week")
        finish()

        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun loginError(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun getIMEI(): String? {
        var imei: String? = ""
        try {
            val mTelephonyMgr: TelephonyManager
            mTelephonyMgr = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(
                    this@LoginActivity,
                    Manifest.permission.READ_PHONE_STATE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return ""
            }
            mTelephonyMgr.deviceId
            imei = mTelephonyMgr.deviceId
            println("imei....$imei")
            if (imei == null || imei === "" || imei.length == 0) {
                imei = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                println("imei....$imei")
            } else if (imei == null || imei == "" || imei.length == 0) {
                println("no imei....$imei")
                imei = try {
                    val wifiManager =
                        applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                    val wInfo: WifiInfo = wifiManager.connectionInfo
                    val macAddress: String = wInfo.getMacAddress()
                    macAddress
                } catch (e: java.lang.Exception) {
                    Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                }
            }
        } catch (e: java.lang.Exception) {
            imei = try {
                e.printStackTrace()
                println("imei exception....$imei")
                val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                val wInfo: WifiInfo = wifiManager.connectionInfo
                val macAddress: String = wInfo.getMacAddress()
                macAddress
            } catch (e1: java.lang.Exception) {
                e1.printStackTrace()
                Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                // Toast.makeText(SplashActivity.this, "MAC Error", Toast.LENGTH_SHORT).show();
            }
        }
        return imei
    }
}