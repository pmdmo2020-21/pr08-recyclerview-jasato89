package es.iessaladillo.pedrojoya.pr06.ui.users

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.DataBase
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityBinding
import kotlinx.android.synthetic.main.user_activity.*
import kotlinx.android.synthetic.main.users_activity_item.view.*
import javax.sql.DataSource

class UsersActivity : AppCompatActivity() {

    // TODO: Código de la actividad.
    //  Ten en cuenta que el recyclerview se muestra en forma de grid,
    //  donde el número de columnas está gestionado
    //  por R.integer.users_grid_columns
    //  ...

    private val viewModel : UsersActivityViewModel by viewModels {
        UsersActivitiyViewModelFactory(DataBase, application)
    }
    val binding: UsersActivityBinding by lazy {
        UsersActivityBinding.inflate(layoutInflater)
    }

    val listAdapter: UsersActivityAdapter = UsersActivityAdapter()

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

    // FIN NO TOCAR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
        setOnclickListeners()

    }

    private fun setOnclickListeners() {
        lstUsers.btnDelete.setOnClickListener {

        }
        lstUsers.btnEdit.setOnClickListener {

        }
    }

    private fun setupRecyclerView() {
        lstUsers.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

    }

    fun onAddUser() {
        Toast.makeText(this,"Hola", Toast.LENGTH_SHORT).show()
    }

}