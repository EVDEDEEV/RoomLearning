package my.project.roomlearning.presentation.fragments.addFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import my.project.roomlearning.R
import my.project.roomlearning.data.model.User
import my.project.roomlearning.databinding.FragmentAddBinding
import my.project.roomlearning.presentation.viewModel.UserViewModel


class AddFragment : Fragment() {

    private lateinit var _binding: FragmentAddBinding
    private val binding get() = _binding
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }


    private fun insertDataToDatabase() {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.editTextNumber.text

        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, age.toString().toInt())
//            Integer.parseInt(age.toString())
            viewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added.", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}