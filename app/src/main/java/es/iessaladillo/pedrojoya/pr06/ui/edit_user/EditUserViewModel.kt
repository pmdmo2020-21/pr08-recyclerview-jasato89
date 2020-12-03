package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import android.app.Application
import androidx.lifecycle.*
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.ui.add_user.STATE_IMAGE
import es.iessaladillo.pedrojoya.pr06.utils.Event
import kotlin.random.Random

// TODO:
//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.

class EditUserViewModel(savedStateHandle: SavedStateHandle, private val database : DataSource, private val application: Application, user: User) : ViewModel() {

    var random : Random = Random

    private val _user : MutableLiveData<User> = MutableLiveData(user)
    val user : LiveData<User>
        get() = _user

    private val _image : MutableLiveData<String> = savedStateHandle.getLiveData(STATE_IMAGE, user.photo)
    val image : LiveData<String>
        get() = _image

    fun changeRandomImage() {
        _image.value = getRandomPhotoUrl()
    }

    private val _onShowMessage : MutableLiveData<Event<String>> = MutableLiveData()
    val onShowMessage : LiveData<Event<String>>
        get() = _onShowMessage




    // Para obtener un URL de foto de forma aleatoria (tendrás que definir
    // e inicializar el random a nivel de clase.
    private fun getRandomPhotoUrl(): String =
            "https://picsum.photos/id/${random.nextInt(100)}/400/300"

    fun checkForm(name: String, phone: String, email: String): Boolean {
        if (name.isBlank()|| phone.isBlank() || email.isBlank()) {
            _onShowMessage.value = Event(application.getString(R.string.user_invalid_data))
            return false
        } else {
            return true
        }

    }

    fun updateUser(updatedUser : User) {
         database.updateUser(updatedUser)

    }

}
