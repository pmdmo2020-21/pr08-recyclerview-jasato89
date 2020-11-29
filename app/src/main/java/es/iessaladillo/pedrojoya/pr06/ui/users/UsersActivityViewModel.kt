package es.iessaladillo.pedrojoya.pr06.ui.users

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User

class UsersActivityViewModel(val database : DataSource, private val application: Application) : ViewModel() {

    val users : LiveData<List<User>> = database.getAllUsersOrderedByName()

    fun addUser(user: User) = database.insertUser(user)
    fun editUser(user: User) = database.updateUser(user)
    fun removeUser(user: User) = database.deleteUser(user)

}

// TODO:
//  Crear clase UsersActivityViewModel
