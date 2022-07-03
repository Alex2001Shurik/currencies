package com.currencies.presentation.main.convert

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.currencies.R
import com.currencies.databinding.FragmentConvertBinding
import com.currencies.presentation.main.callback.ToolbarOptions
import com.currencies.presentation.main.callback.UpdateToolbarCallback
import com.currencies.utils.view_binding.viewBinding

class ConvertFragment : Fragment(R.layout.fragment_convert), UpdateToolbarCallback {

    private val binding by viewBinding(FragmentConvertBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar(ToolbarOptions.WithBackOptions(R.string.convert))
    }

    companion object {
        fun newInstance() = ConvertFragment()
    }
}
