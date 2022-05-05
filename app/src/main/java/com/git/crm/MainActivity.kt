package com.git.crm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.collection.ArrayMap
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.git.crm.CustomerDetails.CustomerDetailsOneFragment
import com.git.crm.Dashboard.DashboardFragment
import com.git.crm.Login.LoginActivity
import com.git.crm.Pojo.Dashboard
import com.git.crm.Pojo.LogOutPojo
import com.git.crm.UserProfile.UserProfileFragment
import com.git.crm.Utils.Constants
import com.git.crm.Utils.PreferenceRequestHelper
import com.google.android.material.navigation.NavigationView
import com.study.firebasecrash.Retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_container.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferenceObj = PreferenceRequestHelper(applicationContext)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        supportFragmentManager.beginTransaction().replace(R.id.fl_container,DashboardFragment()).commitAllowingStateLoss()
        ivback.setOnClickListener {
//            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                supportFragmentManager.popBackStack()
//
//            }

            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
//                ivback.startAnimation(
//                        AnimationUtils.loadAnimation(applicationContext, R.anim.rotate) );
            }
        }
        setupDrawerContent()
    }

    private fun setupDrawerContent() {
        nav_view?.setNavigationItemSelectedListener(this)
    }

    public fun setVisibility(text:String) {
        if(text.equals("ok"))
        {
            tvwelcome.visibility=View.VISIBLE
        }else
        {
            tvwelcome.visibility=View.GONE
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return when (item.itemId) {
            R.id.nav_logout -> {
                logout()
                true
            }
            R.id.nav_home -> {
                supportFragmentManager.popBackStackImmediate(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                );
                supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fl_container, DashboardFragment()).commit();
                true
            }
            R.id.nav_profile -> {
                supportFragmentManager.popBackStackImmediate(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                );
                supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fl_container, UserProfileFragment()).commit();
                true
            }



            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Logout")
        builder.setMessage("Are you want to logout the application?")
        builder.setPositiveButton("YES") { dialog, which ->
            _funLogin()
//            val preferenceObj = PreferenceRequestHelper(this)
//            preferenceObj.clear()
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }
    fun heading(heading: String) {

            tvwelcome.text=heading

    }

    private  var userId: String=""
    private lateinit var preferenceObj: PreferenceRequestHelper
    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.fl_container)
        if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            drawer_layout.closeDrawer(GravityCompat.START)
        }else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                supportFragmentManager.popBackStack()

            } else if ((f is DashboardFragment) && !doubleBackToExitPressedOnce) {
                this.doubleBackToExitPressedOnce = true
                Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            } else if (!(f is DashboardFragment)) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, DashboardFragment()).commit()
            } else {
                super.onBackPressed()
            }
        }
    }



    private fun _funLogin() {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<LogOutPojo> = ApiClient.getClient.userLogout(body)
            call.enqueue(object : Callback<LogOutPojo> {
                override fun onFailure(call: Call<LogOutPojo>, t: Throwable) {
                    progress?.visibility=View.GONE
                }

                override fun onResponse(call: Call<LogOutPojo>, response: Response<LogOutPojo>) {

                    progress?.visibility=View.GONE


                    if (response.isSuccessful) {
                        if (response.body()!!.status!!) {
                            val preferenceObj = PreferenceRequestHelper(applicationContext)
                            preferenceObj.clear()
                        } else {

                        }
                    }

                }


            })

    }


}