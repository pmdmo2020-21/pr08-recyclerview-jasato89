package es.iessaladillo.pedrojoya.pr06.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO:
//  Crear una clase de datos User que implemente Parcelable y que
//  contenga el id de tipo Long, nombre, email, phoneNumber, address, web y photoUrl
//  todas ellas de tipo String.

@Parcelize
data class User(var id : Long, var name: String, var email: String, var phone: String, var address: String, var web : String, var photo: String) : Parcelable

