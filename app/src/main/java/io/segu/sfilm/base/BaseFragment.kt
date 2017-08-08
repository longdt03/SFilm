package io.segu.sfilm.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

/**
 * Created by camlh on 7/19/2017.
 */


abstract class BaseFragment<T> : Fragment() {

    protected lateinit var activity: BaseActivity<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout: Any = this.onLayout()

        try {
            var layoutView: View? = null

            if (layout is Int) {
                layoutView = inflater!!.inflate(layout, container, false)
            } else if (layout is View) {
                layoutView = layout
            }

            ButterKnife.bind(this, layoutView!!)
            this.bindModel(inflater, layoutView)
        } catch(e: Exception) {
            throw UnsupportedOperationException("Unsupported: ${layout.javaClass.name}")
        }

        return super.onCreateView(inflater, container, savedInstanceState)!!
    }

    protected abstract fun onLayout(): Int

    protected abstract fun bindModel(inflater: LayoutInflater?, layout: View)
}