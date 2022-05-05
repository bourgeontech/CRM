package com.git.crm.Splash

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.git.crm.Login.LoginActivity
import com.git.crm.MainActivity
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.PreferenceRequestHelper

class SplashActivity : AppCompatActivity() {
    private var userId: String?=""
    private lateinit var preferenceObj: PreferenceRequestHelper
    internal var appPermissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,Manifest.permission.CALL_PHONE
    )
    val PERMISSIONS_REQUEST_CODE = 1240;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        preferenceObj = PreferenceRequestHelper(this)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
     // Toast.makeText(applicationContext,userId,Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            val currentAPIVersion = Build.VERSION.SDK_INT
            if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
                if (checkAndRequestPermissions()) {
                if (userId!="") {
                   // Toast.makeText(applicationContext,"1",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                   // Toast.makeText(applicationContext,"2",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            }else
            {
                if (userId!="") {
                   // Toast.makeText(applicationContext,"3",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    //makeText(applicationContext,"4",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }


        }, 3000)
    }


    fun checkAndRequestPermissions(): Boolean {
        val listPermisionsNeeded = ArrayList<String>()
        for (perm in appPermissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    perm
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermisionsNeeded.add(perm)
            }
        }

        if (!listPermisionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermisionsNeeded.toTypedArray(),
                PERMISSIONS_REQUEST_CODE
            )
            return false
        }
        return true
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE) {

            val permissionResults = HashMap<String, Int>()
            var deniedCount = 0
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    permissionResults[permissions[i]] = grantResults[i]
                    deniedCount++
                }
            }

            if (deniedCount == 0) {
                println("myid..............3" + userId + "3")
                println("count  denied 0 m.......jj"+userId+"/"+userId?.length)
                if (userId != "") {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                for (entry in permissionResults.entries) {
                    val permName = entry.key
                    val permResult = entry.value
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permName)) {

                        val alertDialog = AlertDialog.Builder(this@SplashActivity)
                        alertDialog.setTitle("This app needs Storage and Phone permissions to work without any problems ")
                        alertDialog.setPositiveButton("ok") { dialog, which ->
                            // TODO Auto-generated method stub
                            dialog.dismiss()
                            checkAndRequestPermissions()
                        }
                        alertDialog.setNegativeButton(
                            "cancel"
                        ) { dialog, which ->
                            // TODO Auto-generated method stub
                            dialog.dismiss()
                            finish()
                        }
                        alertDialog.show()


                    } else {

                        val alertDialog = AlertDialog.Builder(this@SplashActivity)
                        alertDialog.setTitle(("You have denied some permissions.Allow all permissions at" + "[Setting] > [Permissions]"))
                        alertDialog.setPositiveButton(
                            "Go to Settings"
                        ) { dialog, which ->
                            // TODO Auto-generated method stub
                            dialog.dismiss()

                            val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", packageName, null)
                            )
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                        alertDialog.setNegativeButton(
                            "No, Exit app",
                            object : DialogInterface.OnClickListener {

                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    // TODO Auto-generated method stub
                                    dialog.dismiss()
                                    finish()

                                }
                            })
                        alertDialog.show()
                    }
                }
            }
        }

    }
}