package io.segu.sfilm.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife

/**
 * Created by camlh on 7/19/2017.
 */

abstract class BaseActivity<T> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.initLayout()
    }

    fun initLayout() {
        val layout: Any = this.onLayout()!!

        try {

            if (layout is Int) {
                this.setContentView(layout)
            } else if (layout is View) {
                this.setContentView(layout)
            }

            ButterKnife.bind(this)
            this.initToolbar()
        } catch(e: Exception) {
            throw UnsupportedOperationException("Unsupported: ${layout.javaClass.name}")
        }
    }

    protected abstract fun onLayout(): T

    protected fun initToolbar() {}
}
