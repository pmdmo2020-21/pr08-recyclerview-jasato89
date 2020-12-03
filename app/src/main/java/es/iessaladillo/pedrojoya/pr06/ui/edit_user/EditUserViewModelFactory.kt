package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User

class EditUserViewModelFactory(private val user: User, private val application: Application, private val database: DataSource, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return EditUserViewModel(handle, database, application, user) as T
    }
}