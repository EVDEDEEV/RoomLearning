package my.project.roomlearning.presentation.fragments.listFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root


    }
}