package com.bahaaelramlawi.navig.Profile

import android.os.Parcel
import android.os.Parcelable

data class profile(var imgProfile:Int , var imgGallery:Int , var  edName:String?, var edPhone:Int  , var edPassword:String?): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imgProfile)
        parcel.writeInt(imgGallery)
        parcel.writeString(edName)
        parcel.writeInt(edPhone)
        parcel.writeString(edPassword)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<profile> {
        const val TABLE_NAME = "Profile"
        const val COL_NAME = "name"
        const val COL_PHONE = "phone"
        const val COL_PASSWORD = "password"
        const val TABLE_CREATE =
                "create table $TABLE_NAME ($COL_NAME TEXT PRIMARY KEY," +
                        "$COL_PHONE INTEGER NOT NULL, $COL_PASSWORD TEXT)"
        override fun createFromParcel(parcel: Parcel): profile {
            return profile(parcel)
        }

        override fun newArray(size: Int): Array<profile?> {
            return arrayOfNulls(size)
        }


    }
}
