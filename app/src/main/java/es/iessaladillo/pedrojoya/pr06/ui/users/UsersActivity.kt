package es.iessaladillo.pedrojoya.pr06.ui.users

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.DataBase
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.add_user.AddUserActivity
import es.iessaladillo.pedrojoya.pr06.ui.edit_user.EditUserActivity
import kotlinx.android.synthetic.main.users_activity.*
import java.util.*

class UsersActivity : AppCompatActivity() {


    private val binding: UsersActivityBinding by lazy {
        UsersActivityBinding.inflate(layoutInflater)
    }

    private val viewModel : UsersActivityViewModel by viewModels {
        UsersActivityViewModelFactory(DataBase)
    }

    private val listAdapter : UsersActivityAdapter = UsersActivityAdapter().apply {
        onEditClickListener = {editUser(it)}
        onDeleteClickListener = {deleteUser(it)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        observeList()

    }

    private fun observeList() {
        viewModel.listUsers.observe(this) {updateList(it)}
    }

    private fun updateList(users: List<User>) {
        listAdapter.submitList(users)
        binding.lblNoUser.visibility = if (users.isEmpty()) View.VISIBLE else View.GONE
        binding.lblTxtNoUser.visibility = if (users.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun setupViews() {
        binding.lblNoUser.setOnClickListener {
          navigateToAddUserActivity()
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.lstUsers.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = GridLayoutManager(this@UsersActivity,  resources.getInteger(R.integer.users_grid_columns))
            addItemDecoration(DividerItemDecoration(this@UsersActivity, RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun navigateToAddUserActivity() {
        val newIntent = AddUserActivity.newIntent(this)
        startActivity(newIntent)
    }

    private fun deleteUser(position: Int) {
        val user = listAdapter.currentList[position]
        viewModel.removeUser(user)
    }

    private fun editUser(position: Int) {
        val user = listAdapter.currentList[position]
        navigateToEditUserActivity(user)
    }

    private fun navigateToEditUserActivity(user: User) {
        val newIntent = EditUserActivity.newIntent(this, user)
        startActivity(newIntent)

    }

    //NO TOCAR
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.users, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuAdd) {
            onAddUser()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onAddUser() {
        navigateToAddUserActivity()
    }
    // FIN NO TOCAR
}