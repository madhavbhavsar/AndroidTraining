package com.mad.androidtraining.july3Profile.model

import android.os.Parcel
import android.os.Parcelable

data class ProfileIntentModel(
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
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

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

    companion object CREATOR : Parcelable.Creator<ProfileIntentModel> {
        override fun createFromParcel(parcel: Parcel): ProfileIntentModel {
            return ProfileIntentModel(parcel)
        }

        override fun newArray(size: Int): Array<ProfileIntentModel?> {
            return arrayOfNulls(size)
        }
    }


}