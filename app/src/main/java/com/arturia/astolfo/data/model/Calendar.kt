package com.arturia.astolfo.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Author: Arturia
 * Date: 2017/10/31
 */
data class Calendar(var date: String, var cover: String, var name: String, var url: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(cover)
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Calendar> {
        override fun createFromParcel(parcel: Parcel): Calendar {
            return Calendar(parcel)
        }

        override fun newArray(size: Int): Array<Calendar?> {
            return arrayOfNulls(size)
        }
    }
}