package com.mad.androidtraining.july3profileintent.model

import android.os.Parcel
import android.os.Parcelable

data class ProfileIntentModel2(

    val id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val password: String,
    val confpassword: String,
    val dob: String,
    val gender: String,
    val hobbies: String


) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(mobile)
        parcel.writeString(password)
        parcel.writeString(confpassword)
        parcel.writeString(dob)
        parcel.writeString(gender)
        parcel.writeString(hobbies)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfileIntentModel2> {
        override fun createFromParcel(parcel: Parcel): ProfileIntentModel2 {
            return ProfileIntentModel2(parcel)
        }

        override fun newArray(size: Int): Array<ProfileIntentModel2?> {
            return arrayOfNulls(size)
        }
    }
}