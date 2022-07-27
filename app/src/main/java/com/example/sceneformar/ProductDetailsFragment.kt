package com.example.sceneformar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView


class ProductDetailsFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view= inflater.inflate(R.layout.fragment_product_details, container, false)
        val nameText = view.findViewById<TextView>(R.id.txtOneD)
        val priceText = view.findViewById<TextView>(R.id.txtTwoD)
        val WarrantyText = view.findViewById<TextView>(R.id.txtThreeD)
        val ratingText = view.findViewById<TextView>(R.id.txtFourD)
        val photoImage=view.findViewById<ImageView>(R.id.photoD)
        val buttonAr=view.findViewById<Button>(R.id.buttonOneD)
        val buttonBuyNow=view.findViewById<Button>(R.id.buttonTwoD)
        val photo=arguments?.getInt("photo")
        val name=arguments?.getString("name")
        val rating=arguments?.getString("rating")
        val price=arguments?.getString("price")
        val waranty=arguments?.getString("warranty")
        photoImage.setImageResource(photo!!)
        nameText.text="Product name: "+name.toString()
        priceText.text="Price: "+price.toString()
        ratingText.text="Ratings: "+rating.toString()
        WarrantyText.text="Warranty: "+waranty.toString()
        buttonAr.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
        buttonBuyNow.setOnClickListener {
            Toast.makeText(activity as Context,"comming soon",Toast.LENGTH_SHORT).show()
        }
        return view
    }

}