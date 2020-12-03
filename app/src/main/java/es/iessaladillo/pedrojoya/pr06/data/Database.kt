package es.iessaladillo.pedrojoya.pr06.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr06.data.model.User

object DataBase : DataSource {

    private val users : MutableList<User> = mutableListOf()
    private val usersLiveData : MutableLiveData<List<User>> = MutableLiveData()

    init {
        updateUsersLiveData()
    }

    override fun getAllUsersOrderedByName(): LiveData<List<User>> = usersLiveData

    override fun insertUser(user: User) {
        users.add(user)
        updateUsersLiveData()
    }

    override fun updateUser(user: User) {

        var position : Int = users.indexOfFirst { it.id == user.id }
        if (position>= 0) {
            users[position] = user
            updateUsersLiveData()
        }


    }

    override fun deleteUser(user: User) {
        var position : Int = users.indexOfFirst { it.id == user.id }
        if (position>= 0) {
            users.removeAt(position)
            updateUsersLiveData()
        }

    }

    private fun updateUsersLiveData() {usersLiveData.value = users.sortedBy { it.name }}

}



