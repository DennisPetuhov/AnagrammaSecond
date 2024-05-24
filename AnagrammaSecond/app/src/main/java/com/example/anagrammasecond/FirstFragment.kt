package com.example.anagrammasecond

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.anagrammasecond.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    val myViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
                    edittextListener(editText = binding.enterText) { myViewModel.onValueChangedTextInput(it) }
                    edittextListener(editText = binding.filter) { myViewModel.onValueChangedFilterInput(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAnagram(binding.text)
    }

    private fun showAnagram(textView: TextView) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) textView.visibility =
            View.GONE
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                myViewModel.anagram.collect {
                    textView.text = it
                }
            }
        }
    }
private fun edittextListener(editText: EditText, input: (String) -> Unit){
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            input(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })}

}




