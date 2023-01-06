package com.aymen.testapp.ui.base


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aymen.framework.global.helper.Navigation
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.Lazy
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {


    @Inject
    protected lateinit var picassoLazy: Lazy<Picasso>

    protected val picasso: Picasso
        get() = picassoLazy.get()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }


    protected fun registerBaseObservers(viewModel: BaseViewModel) {
        registerShowToastObserver(viewModel)
        registerSnackBarObserver(viewModel)
        registerNavigationObserver(viewModel)

    }


    private fun registerShowToastObserver(viewModel: BaseViewModel) {
        viewModel.showToast.observe(this, Observer(::showToast))
    }

    private fun registerSnackBarObserver(viewModel: BaseViewModel) {
        viewModel.showSnackBar.observe(this) {
            showSnackBar(it.first, it.second)
        }
    }

    private fun registerNavigationObserver(viewModel: BaseViewModel) {
        viewModel.navigation.observe(this, Observer(::navigate))
    }



    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showSnackBar(message: String, action: () -> Unit) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_LONG)
            .setAction("Refresh", MyRefreshListener(action))
            .show()
    }

    class MyRefreshListener(private val action: () -> Unit) : View.OnClickListener {

        override fun onClick(v: View) {
            action()
        }
    }


    open fun navigate(navigationTo: Navigation) {
        when (navigationTo) {
            is Navigation.Back -> navigateBack(navigationTo.ShouldFinish)
            is Navigation.NavigationDetails -> {}
        }

    }

    private fun navigateBack(shouldFinish: Boolean) {
        if (shouldFinish) {
            super.onBackPressed()
            finish()
        } else {
            onBackPressed()
        }
    }


}