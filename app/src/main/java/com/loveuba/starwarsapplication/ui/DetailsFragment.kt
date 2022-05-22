package com.loveuba.starwarsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loveuba.starwarsapplication.R

// name
// birth_year
// height (in cm and feet/inches)
// language
// homeworld
// population (planets)
//• films (movies the character appeared in)
//• opening_crawl (detailed description of each movie)

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

}