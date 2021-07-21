package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.netology.nmedia.BuildConfig
import ru.netology.nmedia.R
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
        val posturl = arguments?.getString("url")
        val url = "${BuildConfig.BASE_URL}/media/$posturl"
        Glide.with(binding.photo)
            .load(url)
            .error(R.drawable.ic_baseline_error_outline_24)
            .timeout(10_000)
            .into(binding.photo)






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