package com.github.dragon925.ram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import com.github.dragon925.ram.R
import com.github.dragon925.ram.adapters.CharacterEpisodesListAdapter
import com.github.dragon925.ram.adapters.CharacterHeaderAdapter
import com.github.dragon925.ram.databinding.FragmentCharacterBinding
import com.github.dragon925.ram.items.CharacterHeaderItem
import com.github.dragon925.ram.items.EpisodeItemSmall
import com.github.dragon925.ram.items.LocationItemSmall
import com.github.dragon925.ram.models.CharacterStatus
import com.github.dragon925.ram.models.Gender

private const val ARG_CHARACTER_ID = "character_id"
private const val ARG_CHARACTER_NAME = "character_name"

class CharacterFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: Int, name: String) =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CHARACTER_ID, id)
                    putString(ARG_CHARACTER_NAME, name)
                }
            }
        const val TAG = "CharacterFragment"

        private val headerExample = CharacterHeaderItem(
            id = 1,
            name = "Jacqueline",
            status = CharacterStatus.ALIVE,
            species = "Human",
            gender = Gender.FEMALE,
            type = "None",
            originLocation = LocationItemSmall(
                id = 1,
                name = "Earth",
                dimension = "Dimension C-137"
            ),
            lastLocation = LocationItemSmall(
                id = 1,
                name = "Earth",
                dimension = "Dimension C-137"
            )
        )

        private val episodesExample = listOf(
            EpisodeItemSmall(
                id = 1,
                name = "Rest and Ricklaxation",
                episode = "S03E06",
                airDate = "August 27, 2017"
            )
        )
    }

    private var characterId: Int? = null
    private var characterName: String? = null

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val headerAdapter = CharacterHeaderAdapter(::openLocation)
    private val episodesAdapter = CharacterEpisodesListAdapter(::openEpisode)
    private val dataAdapter = ConcatAdapter(headerAdapter, episodesAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(ARG_CHARACTER_ID)
            characterName = it.getString(ARG_CHARACTER_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivAvatar.setImageResource(R.drawable.character_image)
        binding.rvCharacterData.adapter = dataAdapter
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        updateUi()
    }

    private fun updateUi() {
        headerAdapter.header = headerExample
        episodesAdapter.submitList(episodesExample)
    }

    private fun openLocation(location: LocationItemSmall) {
        Log.d("CharacterFragment", "Open location")
        // TODO
    }

    private fun openEpisode(episode: EpisodeItemSmall) {
        Log.d("CharacterFragment", "Open episode")
        // TODO
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}