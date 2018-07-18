package com.duyangs.zbase.mvp.model.action.impl

import com.duyangs.zbase.mvp.model.action.face.Action

/**
 * <p>Project:ZBase</p>
 * <p>Package:com.duyangs.zbase</p>
 * <p>Description:ActionEmpty</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/07/18
 */
class ActionEmpty : Action {
    override fun cancel() {

    }

    override fun isExecuted(): Boolean {
        return false
    }

    override fun isCanceled(): Boolean {
        return false
    }

    companion object {
        /**
         * Empty action
         */
        val ACTION_EMPTY: Action = ActionEmpty()
    }

}
