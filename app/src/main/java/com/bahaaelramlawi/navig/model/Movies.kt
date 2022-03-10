package com.bahaaelramlawi.navig.model

import android.os.Parcel
import android.os.Parcelable

data class Movies(var name: String?, var year: String?, var info: String?, var image: Int): Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    //////////////////////////////////////////////////////////

    override fun writeToParcel(parcel: Parcel, flags: Int)
        {
            parcel.writeString(name)
            parcel.writeString(year)
            parcel.writeString(info)
            parcel.writeInt(image)
        }

    ////////////////////////////////////////////////////////////

    override fun describeContents(): Int
        {
            return 0
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    companion object CREATOR : Parcelable.Creator<Movies>
        {
            const val TABLE_NAME = "Movies"
            const val COL_NAME = "name"
            const val COL_YEAR = "year"
            const val COL_INFO = "info"
            const val TABLE_CREATE = "create table $TABLE_NAME ($COL_NAME TEXT PRIMARY KEY AUTOINCREMENT," + "$COL_YEAR TEXT NOT NULL, $COL_INFO TEXT)"

            override fun createFromParcel(parcel: Parcel): Movies
                {
                    return Movies(parcel)
                }

            override fun newArray(size: Int): Array<Movies?>
                {
                    return arrayOfNulls(size)
                }
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}