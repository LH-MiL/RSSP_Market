package com.example.rsspmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

class FragmentChariot1: Fragment(R.layout.fragment_chariot1){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chariot1, container, false)
        //Le corps de la fonction
        return view }


}

