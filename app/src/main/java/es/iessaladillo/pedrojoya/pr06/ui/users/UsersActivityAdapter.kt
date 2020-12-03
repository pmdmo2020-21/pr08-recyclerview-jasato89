package es.iessaladillo.pedrojoya.pr06.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityItemBinding
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl

typealias OnEditClickListener = (position: Int) -> Unit
typealias OnDeleteClickListener = (position: Int) -> Unit

class UsersActivityAdapter : ListAdapter<User, UsersActivityAdapter.ViewHolder>(UserDiffCallback) {

    var onEditClickListener: OnEditClickListener? = null
    var onDeleteClickListener: OnDeleteClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UsersActivityItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = currentList[position]
        holder.bind(user)
    }

    inner class ViewHolder(private val binding: UsersActivityItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnEdit.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onEditClickListener?.invoke(position)
                }

            }
            binding.btnDelete.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClickListener?.invoke(position)

                }
            }
        }

        fun bind(user: User) {
            user.run {
                binding.txtUserName.text = name
                binding.txtEmail.text = email
                binding.txtTelephone.text = phone
                binding.imageAvatar.loadUrl(photo)
            }
        }
    }

    object UserDiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }


}

