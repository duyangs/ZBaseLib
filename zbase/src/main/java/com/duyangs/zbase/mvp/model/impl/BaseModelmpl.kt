package com.duyangs.zbase.mvp.model.impl

import android.app.Application

import com.duyangs.zbase.mvp.model.face.BaseModelInterface


/**
 * <p>Project:ZBase</p>
 * <p>Package:com.duyangs.zbase</p>
 * <p>Description:BaseModelmpl Basic data model Implementation</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/07/18
 */
open class BaseModelmpl : BaseModelInterface {

    private var application: Application? = null

    override fun init(application: Application) {

        this.application = application
    }
}
