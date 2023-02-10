package com.app.livrizon.fragments.publication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.livrizon.databinding.FragmentCreatePostBinding
import com.app.livrizon.request.HttpListener
import kotlinx.coroutines.CoroutineScope

class CreatePostFragment : Fragment() {
    lateinit var binding: FragmentCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCreatePostBinding.inflate(layoutInflater)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun init() {
        binding.btnDone.setOnClickListener {
            object : HttpListener(requireContext()) {
                override suspend fun body(block: CoroutineScope) {
                    //PublicationRequest.savePost(
                    //    SavePost(
                    //        comment = true,
                    //        link = true,
                    //        address = null,
                    //        poll = null,
                    //        description = binding.edDescription.text.toString()
                    //    )
                    //)
                }

                override fun onSuccess(item: Any?) {
                    requireActivity().finish()
                }
            }.request()
        }
    }

}