package com.duyangs.zbase.mvp.model.impl

import android.app.Application

import com.duyangs.zbase.mvp.model.face.BaseModel


/**
 * <p>Project:ZBase</p>
 * <p>Package:com.duyangs.zbase</p>
 * <p>Description:BaseModelImpl Basic data model Implementation</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/07/18
 */
open class BaseModelImpl : BaseModel {

    private var application: Application? = null

    override fun init(application: Application) {

        this.application = application
    }
}
