package es.iessaladillo.pedrojoya.pr06.ui.users

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr06.data.DataSource

@Suppress("UNCHECKED_CAST")

class UsersActivitiyViewModelFactory (private val database : DataSource, private val application: Application) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersActivityViewModel(database, application) as T
    }
}