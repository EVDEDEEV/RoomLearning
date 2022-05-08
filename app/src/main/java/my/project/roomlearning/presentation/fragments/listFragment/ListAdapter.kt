package my.project.roomlearning.presentation.fragments.listFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import my.project.roomlearning.data.model.User
import my.project.roomlearning.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(
        private val binding: CustomRowBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.idText.text = user.id.toString()
            binding.firstNameTxt.text = user.firstName
            binding.lastNameTxt.text = user.lastName
            binding.ageTxt.text = user.age.toString()

            binding.rowLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(user)
                binding.rowLayout.findNavController().navigate(action)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomRowBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun setData(user: List<User>) {
        userList = user
        notifyDataSetChanged()
    }


}