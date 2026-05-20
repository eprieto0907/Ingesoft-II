package com.inter.interapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.inter.interapp.R
import com.inter.interapp.data.db.DatabaseHelper
import com.inter.interapp.databinding.FragmentLoginBinding
import com.inter.interapp.ui.MainViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())

        val versionLocal = requireContext().packageManager
            .getPackageInfo(requireContext().packageName, 0).versionName ?: "1.0.0"
        viewModel.verificarVersion(versionLocal)

        viewModel.versionMensaje.observe(viewLifecycleOwner) { mensaje ->
            binding.tvVersion.text = mensaje
        }

        binding.btnLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnLogin.isEnabled = false
            viewModel.login()
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            binding.progressBar.visibility = View.GONE
            binding.btnLogin.isEnabled = true
            result.onSuccess { loginData ->
                dbHelper.guardarUsuario(loginData)
                findNavController().navigate(R.id.action_login_to_home)
            }
            result.onFailure { error ->
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Error de Autenticación")
                    .setMessage(error.message ?: "Error desconocido")
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