package com.github.dragon925.ram.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.github.dragon925.ram.R
import com.github.dragon925.ram.adapters.CharacterListAdapter
import com.github.dragon925.ram.databinding.FragmentCharacterListBinding
import com.github.dragon925.ram.items.CharacterItem
import com.github.dragon925.ram.models.Character
import com.github.dragon925.ram.models.CharacterStatus
import com.github.dragon925.ram.models.Gender

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val adapter = CharacterListAdapter(::openCharacterDetail)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterListBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCharacterList.adapter = adapter

        adapter.submitList(charactersExample)
    }

    private fun openCharacterDetail(character: CharacterItem) {
        Log.d("CharacterListFragment", "Character ${character.name} clicked")
        activity?.supportFragmentManager?.commit {
            replace(
                R.id.fragment_container,
                CharacterFragment.newInstance(character.id, character.name)
            )
            addToBackStack(CharacterFragment.TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterListFragment()

        private val charactersExample = List(10) { i ->
            val num = i + 1
            CharacterItem(
                id = num,
                name = "Character $num",
                status = CharacterStatus.entries.random(),
                species = "Human",
                gender = Gender.entries.random(),
                lastLocation = Character.Location(num, "Unknown"),
            )
        }
    }
}