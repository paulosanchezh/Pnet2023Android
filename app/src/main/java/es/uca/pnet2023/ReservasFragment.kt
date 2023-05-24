package es.uca.pnet2023

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uca.pnet2023.databinding.FragmentReservasBinding

class ReservasFragment : Fragment() {

    private var _binding: FragmentReservasBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReservasAdapter
    private lateinit var viewModel: ReservasViewModel
    private lateinit var root2 : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        adapter = ReservasAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        root2 = inflater.inflate(R.layout.list_item_reservas, container, false)
        setupNavigation(root2)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ReservasViewModel::class.java)
        viewModel.getReservas().observe(viewLifecycleOwner) { reservas ->
            adapter.setReservas(reservas)
            adapter.notifyDataSetChanged()
        }
    }
    private fun setupNavigation(root: View) {
        val toSalasButton = root.findViewById<Button>(R.id.editButton)
        toSalasButton.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_reservasFragment_to_detallesReservaFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}