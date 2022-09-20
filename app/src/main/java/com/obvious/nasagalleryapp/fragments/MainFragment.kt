package com.obvious.nasagalleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.obvious.domain.models.ModalGallery
import com.obvious.nasagalleryapp.adapters.GalleryAdapter
import com.obvious.nasagalleryapp.databinding.FragmentMainBinding
import com.obvious.nasagalleryapp.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
  private var _binding: FragmentMainBinding? = null
  private val binding
    get() = _binding!!
  private val viewModel: MainViewModel by viewModels()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpObservers()
    viewModel.fetchGalleryList(30)
  }

  private fun setUpObservers() {
    viewModel.list.observe(viewLifecycleOwner) {
      if (!it.isNullOrEmpty()) {
        binding.mainProgress.visibility = View.GONE
        binding.mainRecyclerView.visibility = View.VISIBLE
        val galleryAdapter =
            GalleryAdapter(
                it,
                requireContext(),
                { pos ->
                  findNavController()
                      .navigate(
                          MainFragmentDirections.actionMainFragmentToDetailsFragment(
                              pos, ModalGallery(viewModel.list.value!!)))
                },
                { pos ->
                  val newItems = viewModel.list.value!!
                  newItems[pos].isFavorite = newItems[pos].isFavorite.not()
                  viewModel.list.postValue(newItems)
                })

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.mainRecyclerView.apply {
          adapter = galleryAdapter
          layoutManager = gridLayoutManager
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
