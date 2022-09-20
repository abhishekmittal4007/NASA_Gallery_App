package com.obvious.nasagalleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.obvious.nasagalleryapp.adapters.DetailsItemAdapter
import com.obvious.nasagalleryapp.databinding.FragmentDetailsBinding
import com.obvious.nasagalleryapp.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
  private var _binding: FragmentDetailsBinding? = null
  private val binding
    get() = _binding!!
  private val args: DetailsFragmentArgs by navArgs()
  private val viewModel: MainViewModel by viewModels()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    // Inflate the layout for this fragment
    _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val arg = args.list
    val detailsItemAdapter = DetailsItemAdapter(arg.list)
    binding.pager.apply {
      adapter = detailsItemAdapter
      setCurrentItem(args.position, false)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}
