package com.example.videodb.ui.activity

import android.os.Bundle
import com.example.videodb.App
import com.example.videodb.BackButtonListener
import com.example.videodb.R
import com.example.videodb.mvp.presenter.MainPresenter
import com.example.videodb.mvp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    val presenter by moxyPresenter {
        MainPresenter().apply { App.component.inject(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }
}
