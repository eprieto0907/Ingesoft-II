package com.inter.interapp.ui.tablas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.inter.interapp.data.db.DatabaseHelper
import com.inter.interapp.databinding.FragmentTablasBinding

class TablasFragment : Fragment() {

    private var _binding: FragmentTablasBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TablasAdapter
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTablasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())

        adapter = TablasAdapter(emptyList())
        binding.recyclerTablas.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTablas.adapter = adapter

        val tablas = dbHelper.obtenerTablas()

        if (tablas.isEmpty()) {
            /*
             * NOTA TÉCNICA DOCUMENTADA:
             * El endpoint ObtenerEsquema retorna HTTP 401 porque requiere
             * autenticación con TokenJWT. El ambiente de pruebas no provee
             * este token en el flujo de login actual.
             */
            binding.tvMensajeVacio.visibility = View.VISIBLE
            binding.recyclerTablas.visibility = View.GONE
        } else {
            binding.tvMensajeVacio.visibility = View.GONE
            binding.recyclerTablas.visibility = View.VISIBLE
            adapter.updateData(tablas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}