package com.duyangs.znet

import android.os.Parcel
import android.os.Parcelable

/**
 * Base 数据类
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2018/7/3.
 * @param msg 返回请求信息
 * @param code 返回请求码
 * @param E 泛型对象，实现网络请求框架可行性，代码复用性
 */
data class ZBaseBean<E:Parcelable>(var msg: String = "",
                                   var code: Int = 0,
                                   var data: E? = null)
    : Parcelable {

    private val EMPTY = "empty"

    constructor(parcel: Parcel) : this() {
        msg = parcel.readString()
        code = parcel.readInt()
        val className = parcel.readString()
        data = if(className == EMPTY) null else parcel.readParcelable(Class.forName(className).classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(msg)
        parcel.writeInt(code)
        parcel.writeString(if(data==null) EMPTY else data!!::class.java.name)
        parcel.writeParcelable(data, flags)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ZBaseBean<Parcelable>> {
        override fun createFromParcel(parcel: Parcel): ZBaseBean<Parcelable> {
            return ZBaseBean(parcel)
        }

        override fun newArray(size: Int): Array<ZBaseBean<Parcelable>?> {
            return arrayOfNulls(size)
        }
    }

}
