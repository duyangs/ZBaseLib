package com.duyangs.zbase.mvp.model.face;

import android.app.Application;


/**
 * <p>Project:ZBase</p>
 * <p>Package:com.duyangs.zbase</p>
 * <p>Description:BaseModelInterface Basic data model，Do not Constructor</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/07/18
 */
interface BaseModelInterface {
    /**
     * 初始化
     *
     * @param application 应用
     */
    fun init(application: Application)

}
