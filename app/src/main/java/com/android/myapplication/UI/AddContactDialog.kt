//package com.android.myapplication.UI
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.DialogFragment
//import com.android.myapplication.databinding.FragmentAddContactDialogBinding
//
//class AddContactDialog : DialogFragment() {
//
//    private var _binding: FragmentAddContactDialogBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentAddContactDialogBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.btnCancel.setOnClickListener {
//            dismiss()
//        }
//
//        binding.btnSave.setOnClickListener {
//            val name = binding.etName.text.toString()
//            val phone = binding.etPhone.text.toString()
//            val email = binding.etEmail.text.toString()
//
//            // TODO: 입력값을 사용하여 처리
//            dismiss()
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}