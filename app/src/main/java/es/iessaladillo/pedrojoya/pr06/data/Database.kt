package es.iessaladillo.pedrojoya.pr06.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr06.data.model.User

object DataBase : DataSource {

    private val users : MutableList<User> = emptyList<User>().toMutableList()
    private val usersLiveData : MutableLiveData<List<User>> = MutableLiveData(users)

    override fun getAllUsersOrderedByName(): LiveData<List<User>> = usersLiveData

    override fun insertUser(user: User) {
        users.add(user)
        updateStudentsLiveData()
    }

    override fun updateUser(user: User) {

        var position : Int = users.indexOfFirst { it.id == user.id }
        if (position<= 0) {
            users[position] = user
        }

        updateStudentsLiveData()
    }

    override fun deleteUser(user: User) {
        var position : Int = users.indexOfFirst { it.id == user.id }
        if (position<= 0) {
            users.removeAt(position)
            updateStudentsLiveData()
        }

    }

    private fun updateStudentsLiveData() {usersLiveData.value = users.sortedBy { it.name }}

}



