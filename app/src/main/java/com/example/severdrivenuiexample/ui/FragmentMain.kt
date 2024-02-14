package com.example.severdrivenuiexample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.severdrivenuiexample.R
import com.google.android.material.button.MaterialButton

class FragmentMain:Fragment(R.layout.fragment_main_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.show_divkit).setOnClickListener {
           findNavController().navigate(R.id.to_show_divkit)
            Log.d("value","yes")
        }
    }
}