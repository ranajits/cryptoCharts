package com.ranajit.tredzerv.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ranajit.tredzerv.utils.LoadingDialog
import com.ranajit.tredzerv.utils.LocaleHelper


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(), UICallbacks<V> {

    protected lateinit var mViewModel: V
    protected lateinit var mBinding: T

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this@BaseActivity, getLayoutId())
        mViewModel = ViewModelProvider(this@BaseActivity).get(getViewModel())
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        createDialog()
        onBindingUi()
    }


    private fun createDialog() {
        val dialog = LoadingDialog(this)
        mViewModel.getVisibility().observe(this, Observer { show ->
            dialog.run {
                if (show) show() else dismiss()
            }
        })
        mViewModel.getMessage().observe(this, Observer {
            dialog.setMessage(it)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.wrap(newBase!!, "en"))
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

}


