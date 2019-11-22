package tmdb.tharindu.movieshowcase.activity

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import tmdb.tharindu.movieshowcase.R
import tmdb.tharindu.movieshowcase.fragment.BaseFragment
import tmdb.tharindu.movieshowcase.utility.AppUtils
import tmdb.tharindu.movieshowcase.viewmodel.BaseViewModel


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity(), BaseFragment.Callback {

    var mProgressDialog: ProgressBar? = null
    private lateinit var viewDataBinding: T
    private var viewModel: V? = null

    fun getViewDataBinding(): T = viewDataBinding


    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onFragmentAttached() {}
    override fun onFragmentDetached(tag: String) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    override fun onResume() {
        super.onResume()
        if (!AppUtils.isConnected(this)) showSnackBar(getString(R.string.no_internet_connection))
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewModel = if (viewModel == null) getViewModel() else viewModel
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideLoading() {
        if (mProgressDialog?.isActivated!!) {
            mProgressDialog?.clearAnimation()
        }
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = AppUtils.showLoadingDialog(this)
    }

    fun showMessage(message : String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    fun onError(message: String?) {
        if (message != null)
            showSnackBar(message)
        else
            showSnackBar(getString(R.string.something_went_wrong))
    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_error_color))
        val textView = sbView
                .findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }



}