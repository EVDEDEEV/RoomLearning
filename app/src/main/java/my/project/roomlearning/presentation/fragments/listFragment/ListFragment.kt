package my.project.roomlearning.presentation.fragments.listFragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import my.project.roomlearning.R
import my.project.roomlearning.databinding.FragmentListBinding
import my.project.roomlearning.presentation.viewModel.UserViewModel

class ListFragment : Fragment() {

    //    private val recyclerView = binding.recyclerView
    private val adapter = ListAdapter()
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var _binding: FragmentListBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        //Recycler View
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        viewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        //Add menu
        setHasOptionsMenu(true)

        return binding.root


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAllUsers()
            Toast.makeText(requireContext(),
                "Successfully removed all",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Deleted all items?")
        builder.setMessage("Are you sure you want to delete all items")
        builder.create().show()
    }
}