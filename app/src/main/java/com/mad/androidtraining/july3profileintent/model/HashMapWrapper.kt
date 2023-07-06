package com.mad.androidtraining.july3profileintent.model

import android.os.Parcel
import android.os.Parcelable
class HashMapWrapper : Parcelable {
    private var hashMap: HashMap<String, Any> = HashMap()

    constructor()

    constructor(hashMap: HashMap<String, Any>) {
        this.hashMap = hashMap
    }

    fun getHashMap(): HashMap<String, Any> {
        return hashMap
    }

    constructor(parcel: Parcel) {
        val size = parcel.readInt()
        for (i in 0 until size) {
            val key = parcel.readString() ?: ""
            val value = parcel.readValue(Any::class.java.classLoader)
            if (key.isNotEmpty() && value != null) {
                hashMap[key] = value
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(hashMap.size)
        for ((key, value) in hashMap) {
            parcel.writeString(key)
            parcel.writeValue(value)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HashMapWrapper> {
        override fun createFromParcel(parcel: Parcel): HashMapWrapper {
            return HashMapWrapper(parcel)
        }

        override fun newArray(size: Int): Array<HashMapWrapper?> {
            return arrayOfNulls(size)
        }
    }
}