package es.iessaladillo.pedrojoya.pr06.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.model.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.users_activity_item.view.*

class UsersActivityAdapter : RecyclerView.Adapter<UsersActivityAdapter.ViewHolder>() {
    var data : List<User> = emptyList()

    class ViewHolder(override val containerView: View) :RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user : User) {
            user.run {
                containerView.txtUserName.text = user.name
                containerView.txtEmail.text = user.email
                containerView.txtTelephone.text = user.phone

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.users_activity_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = data[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = data.size
}