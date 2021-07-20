package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.databinding.PhotoFragmentBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class PhotoFragment:Fragment() {
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment,
    )

    private var fragmentBinding: PhotoFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PhotoFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        fragmentBinding = binding

        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }

        viewModel.photo.observe(viewLifecycleOwner) {
            if (it.uri == null) {
                return@observe
            }
            binding.photo.setImageURI(it.uri)
        }
        return binding.root

    }
}