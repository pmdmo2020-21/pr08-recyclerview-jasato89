package es.iessaladillo.pedrojoya.pr06.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User

class UsersActivityViewModel(private val database :DataSource) : ViewModel() {

    val listUsers: LiveData<List<User>> = database.getAllUsersOrderedByName()

    fun removeUser(user:User) {
        database.deleteUser(user)
    }

}

// TODO:
//  Crear clase UsersActivityViewModel
