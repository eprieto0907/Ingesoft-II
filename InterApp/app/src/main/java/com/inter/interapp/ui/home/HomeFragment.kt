package com.inter.interapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.inter.interapp.R
import com.inter.interapp.data.db.DatabaseHelper
import com.inter.interapp.databinding.FragmentHomeBinding
import com.inter.interapp.ui.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())

        val usuario = dbHelper.obtenerUsuario()

        /*
         * NOTA TÉCNICA DOCUMENTADA:
         * El servidor de pruebas retorna Identificacion y Nombre como null.
         * Solo el campo Usuario es retornado correctamente por el login.
         * En producción estos campos deberían contener valores válidos.
         */
        binding.tvUsuario.text = "Usuario: ${usuario?.Usuario ?: "No disponible"}"
        binding.tvIdentificacion.text = "Identificación: ${
            if (usuario?.Identificacion.isNullOrEmpty())
                "No retornado por servidor de pruebas"
            else
                usuario?.Identificacion
        }"
        binding.tvNombre.text = "Nombre: ${
            if (usuario?.Nombre.isNullOrEmpty())
                "No retornado por servidor de pruebas"
            else
                usuario?.Nombre
        }"

        /*
         * NOTA TÉCNICA DOCUMENTADA:
         * El endpoint ObtenerEsquema requiere TokenJWT (HTTP 401).
         * El servidor de pruebas retorna TokenJWT=null en el login,
         * por lo que no es posible consumir este endpoint en el ambiente actual.
         */
        viewModel.cargarEsquema()
        viewModel.tablas.observe(viewLifecycleOwner) { tablas ->
            if (tablas.isNotEmpty()) {
                dbHelper.guardarTablas(tablas)
            }
        }

        binding.btnTablas.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_tablas)
        }

        binding.btnLocalidades.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_localidades)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}