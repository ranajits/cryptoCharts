package com.ranajit.tredzerv.ui.splash

import android.content.Intent
import android.os.Handler
import com.ranajit.tredzerv.R
import com.ranajit.tredzerv.base.BaseActivity
import com.ranajit.tredzerv.base.BaseViewModel
import com.ranajit.tredzerv.databinding.ActivitySplashBinding
import com.ranajit.tredzerv.ui.portfolio.PortfolioActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<ActivitySplashBinding, BaseViewModel>() {
    override fun getViewModel() = BaseViewModel::class.java

    override fun getLayoutId() = R.layout.activity_splash

    override fun onBindingUi() {
        anim_text.animateText("Tredzerv.")
        Handler().postDelayed({
            startActivity(Intent(this, PortfolioActivity::class.java))
            finish()
        }, 2000.toLong())

    }
}