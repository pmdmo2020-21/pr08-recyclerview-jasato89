package es.iessaladillo.pedrojoya.pr06.data.model

import android.provider.ContactsContract
import kotlinx.android.parcel.Parcelize

// TODO:
//  Crear una clase de datos User que implemente Parcelable y que
//  contenga el id de tipo Long, nombre, email, phoneNumber, address, web y photoUrl
//  todas ellas de tipo String.

data class User(val id : Long, val name: String, val email: String, val phone: String, val address: String?, val web : String?)

