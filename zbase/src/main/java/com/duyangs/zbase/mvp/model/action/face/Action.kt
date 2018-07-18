package com.duyangs.zbase.mvp.model.action.face

/**
 * <p>Project:ZBase</p>
 * <p>Package:com.duyangs.zbase</p>
 * <p>Description:Action</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/07/18
 */
 interface Action {
    /**
     * Cancel current action
     */
    fun cancel()

    /**
     * Determine if it has been executed
     *
     * @return True is already executed
     */
    fun isExecuted(): Boolean

    /**
     * Determine if it has been cancelled
     *
     * @return true Is canceled
     */
    fun isCanceled(): Boolean
}
