package com.inter.interapp.ui.localidades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.inter.interapp.databinding.FragmentLocalidadesBinding
import com.inter.interapp.ui.MainViewModel

class LocalidadesFragment : Fragment() {

    private var _binding: FragmentLocalidadesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: LocalidadesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocalidadesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LocalidadesAdapter(emptyList())
        binding.recyclerLocalidades.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerLocalidades.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE
        viewModel.cargarLocalidades()

        viewModel.localidades.observe(viewLifecycleOwner) { result ->
            binding.progressBar.visibility = View.GONE
            result.onSuccess { lista ->
                adapter.updateData(lista)
            }
            result.onFailure { error ->
                /*
                 * NOTA TÉCNICA: Error documentado al cargar localidades.
                 */
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Información")
                    .setMessage("No se pudieron cargar las localidades.\n\nNota técnica: ${error.message}")
                    .setPositiveButton("Aceptar", null)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}