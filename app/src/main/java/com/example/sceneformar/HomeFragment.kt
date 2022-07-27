package com.example.sceneformar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView


class HomeFragment : Fragment(),View.OnClickListener {

    lateinit var cardElectronic:CardView
    lateinit var cardFurniture:CardView
    lateinit var cardToy:CardView
    lateinit var dealsElectronics:LinearLayout
    lateinit var dealsFurnitures:LinearLayout
    lateinit var dealsToys:LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view= inflater.inflate(R.layout.fragment_home, container, false)
        cardElectronic=view.findViewById(R.id.cardOne)
        cardFurniture=view.findViewById(R.id.cardTwo)
        cardToy=view.findViewById(R.id.cardThree)
        dealsElectronics=view.findViewById(R.id.linearOne)
        dealsFurnitures=view.findViewById(R.id.linearTwo)
        dealsToys=view.findViewById(R.id.linearThree)
        cardElectronic.setOnClickListener(this)
        cardFurniture.setOnClickListener(this)
        cardToy.setOnClickListener(this)
        dealsElectronics.setOnClickListener(this)
        dealsFurnitures.setOnClickListener(this)
        dealsToys.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.cardOne->
            {
                FragmentChange(1)
            }


            R.id.cardTwo->
            {
                FragmentChange(2)

            }
            R.id.cardThree->
            {
                FragmentChange(3)


            }
            R.id.linearOne->
            {
                FragmentChange(1)
            }
            R.id.linearTwo->
            {
                FragmentChange(2)
            }
            R.id.linearThree->
            {
                FragmentChange(3)
            }
        }
    }

    fun FragmentChange(Number:Int)
    {
        val fragment = ProductFragment()
        val args = Bundle()
        args.putInt("product",Number)
        fragment.arguments = args
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .addToBackStack("NotesOne")
            .replace(R.id.lytFrame, fragment, "NotesTwo").commit()

    }

}