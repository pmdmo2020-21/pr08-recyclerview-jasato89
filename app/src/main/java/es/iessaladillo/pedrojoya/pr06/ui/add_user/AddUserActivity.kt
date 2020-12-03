package es.iessaladillo.pedrojoya.pr06.ui.add_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.DataBase
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding
import es.iessaladillo.pedrojoya.pr06.utils.SoftInputUtils
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl
import es.iessaladillo.pedrojoya.pr06.utils.observeEvents

class AddUserActivity : AppCompatActivity() {

    // TODO: Código de la actividad.
    //  ...


    private val binding: UserActivityBinding by lazy {
        UserActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: AddUserViewModel by viewModels {
        AddUserViewModelFactory(DataBase, application, this)
    }


    companion object {
        fun newIntent(context: Context): Intent = Intent(context, AddUserActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        observeEvents()

    }

    private fun observeEvents() {
        viewModel.onShowMessage.observeEvents(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.image.observe(this) {modifyImage(it)}
    }

    private fun modifyImage(image: String) {
        binding.imgUser.loadUrl(image)

    }

    private fun setupViews() {
        binding.txtEditWeb.setOnEditorActionListener { _, _, _ ->
            onSave()
            true
        }

        binding.imgUser.setOnClickListener() {
            viewModel.changeRandomImage()
        }

    }

    private fun onSave() {
        SoftInputUtils.hideSoftKeyboard(binding.root)
        val name = binding.txtEditUserName.text.toString()
        val phone = binding.txtEditPhone.text.toString()
        val email = binding.txtEditEmail.text.toString()

        if(viewModel.checkForm(name, phone, email)) {
            val address = binding.txtEditAddress.text.toString()
            val image = viewModel.image.value?: ""
            val web = binding.txtEditWeb.text.toString()
            viewModel.createNewUser(name, phone, email, address, image, web)
            finish()

        }
    }

    // NO TOCAR: Estos métodos gestionan el menú y su gestión

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSave) {
            onSave()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}