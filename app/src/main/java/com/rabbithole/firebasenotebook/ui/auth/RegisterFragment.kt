package com.rabbithole.firebasenotebook.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rabbithole.firebasenotebook.R
import com.rabbithole.firebasenotebook.data.model.User
import com.rabbithole.firebasenotebook.databinding.FragmentRegisterBinding
import com.rabbithole.firebasenotebook.ui.auth.AuthViewModel
import com.rabbithole.firebasenotebook.util.UiState
import com.rabbithole.firebasenotebook.util.hide
import com.rabbithole.firebasenotebook.util.isValidEmail
import com.rabbithole.firebasenotebook.util.show
import com.rabbithole.firebasenotebook.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    val TAG: String = "RegisterFragment"
    lateinit var binding: FragmentRegisterBinding
    val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.registerBtn.setOnClickListener {
            if (validation()){
                viewModel.register(
                    email = binding.emailEt.text.toString(),
                    password = binding.passEt.text.toString(),
                    user = getUserObj()
                )
            }
        }
    }

    fun observer() {
        viewModel.register.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.registerBtn.setText("")
                    binding.registerProgress.show()
                }
                is UiState.Failure -> {
                    binding.registerBtn.setText("Register")
                    binding.registerProgress.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.registerBtn.setText("Register")
                    binding.registerProgress.hide()
                    toast(state.data)
                    findNavController().navigate(R.id.action_registerFragment_to_noteListingFragment)
                }
            }
        }
    }

    fun getUserObj(): User {
        return User(
            id = "",
            first_name = binding.firstNameEt.text.toString(),
            last_name = binding.lastNameEt.text.toString(),
            job_title = binding.jobTitleEt.text.toString(),
            email = binding.emailEt.text.toString(),
        )
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.firstNameEt.text.isNullOrEmpty()){
            isValid = false
            toast("Fill all")
        }

        if (binding.lastNameEt.text.isNullOrEmpty()){
            isValid = false
            toast("Fill all")
        }

        if (binding.jobTitleEt.text.isNullOrEmpty()){
            isValid = false
            toast("Fill all")
        }

        if (binding.emailEt.text.isNullOrEmpty()){
            isValid = false
            toast("Fill all")
        }else{
            if (!binding.emailEt.text.toString().isValidEmail()){
                isValid = false
                toast("Fill all")
            }
        }
        if (binding.passEt.text.isNullOrEmpty()){
            isValid = false
            toast("Fill all")
        }else{
            if (binding.passEt.text.toString().length < 8){
                isValid = false
                toast("Fill all")
            }
        }
        return isValid
    }

}

