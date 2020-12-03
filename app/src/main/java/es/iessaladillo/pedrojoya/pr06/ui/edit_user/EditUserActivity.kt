package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
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

class EditUserActivity : AppCompatActivity() {


    companion object {
        val EXTRA_USER = "EXTRA_USER"
        fun newIntent(context: Context, user: User): Intent {
            return Intent(context, EditUserActivity::class.java).putExtra(EXTRA_USER, user)
        }
    }

    private val binding: UserActivityBinding by lazy {
        UserActivityBinding.inflate(layoutInflater)
    }

    private lateinit var user: User

    private val viewModel: EditUserViewModel by viewModels {
        EditUserViewModelFactory(user, application, DataBase, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getUser()
        setupViews()
        observeActivity()

    }



    private fun observeActivity() {
        viewModel.user.observe(this) {
            showFormFields(it)
        }
        viewModel.image.observe(this) {updateUserImage(it)}
        viewModel.onShowMessage.observeEvents(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun updateUserImage(imageUrl: String) {
        binding.imgUser.loadUrl(imageUrl)

    }

    private fun showFormFields(user : User) {
        user.run {
            //binding.imgUser.loadUrl()
            binding.txtEditUserName.text = stringToEditable(name)
            binding.txtEditEmail.text = stringToEditable(email)
            binding.txtEditPhone.text = stringToEditable(phone)
            binding.txtEditAddress.text = stringToEditable(address)
            binding.txtEditWeb.text = stringToEditable(web)

        }

    }

    private fun stringToEditable(string: String): Editable =
            Editable.Factory.getInstance().newEditable(string)




    private fun getUser() {
        if (intent != null && intent.hasExtra(EXTRA_USER)) {
            user = intent.getParcelableExtra(EXTRA_USER)!!
        }
    }

    private fun setupViews() {
        binding.imgUser.setOnClickListener { changeImage()}


    }

    private fun changeImage() {
        viewModel.changeRandomImage()
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

    // FIN NO TOCAR

    private fun onSave() {
        SoftInputUtils.hideSoftKeyboard(binding.root)
        val name = binding.txtEditUserName.text.toString()
        val phone = binding.txtEditPhone.text.toString()
        val email = binding.txtEditEmail.text.toString()

        if(viewModel.checkForm(name, phone, email)) {
            val address = binding.txtEditAddress.text.toString()
            val image = viewModel.image.value?: ""
            val web = binding.txtEditWeb.text.toString()
            val newUser = User(user.id, name, phone, email, address, web, image)
            viewModel.updateUser(newUser)
            finish()

        }
    }

}