package es.iessaladillo.pedrojoya.pr06.ui.add_user


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.DataBase
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.utils.Event
import java.util.*


// TODO:
//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.
const val STATE_IMAGE: String = "STATE_IMAGE"
const val STATE_USER: String = "STATE_USER"

class AddUserViewModel(savedStateHandle: SavedStateHandle, private val dataSource : DataSource, private val application: Application) : ViewModel() {


    private val random: Random = Random()
    private val _onShowMessage : MutableLiveData<Event<String>> = MutableLiveData()
    val onShowMessage : LiveData<Event<String>>
        get() = _onShowMessage

    private val _image : MutableLiveData<String> = savedStateHandle.getLiveData(STATE_IMAGE, getRandomPhotoUrl())
    val image : LiveData<String>
        get() = _image

    private var id :Long = 0

    // Para obtener un URL de foto de forma aleatoria (tendrás que definir
    // e inicializar el random a nivel de clase.
     fun getRandomPhotoUrl(): String =
            "https://picsum.photos/id/${random.nextInt(100)}/400/300"

    fun checkForm(name: String, phone: String, email: String): Boolean {

        if( name.isBlank() || email.isBlank() || phone.isBlank()) {
            _onShowMessage.value = Event(application.getString(R.string.user_invalid_data))
            return false
        } else {
            return true
        }

    }



    fun createNewUser(name: String, phone: String, email: String, address: String, image: String, web: String) {
        val user = User(id++, name, email, phone, address, web, image)
        DataBase.insertUser(user)

    }

    fun changeRandomImage() {
        _image.value = getRandomPhotoUrl()
    }

}
