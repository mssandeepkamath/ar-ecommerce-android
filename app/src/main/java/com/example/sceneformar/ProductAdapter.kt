package com.example.sceneformar


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class ProductAdapter(
    var context: Context,
    var itemArray: ArrayList<ProductData>
) :
    RecyclerView.Adapter<ProductAdapter.Viewholder>() {

    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.txtOne)
        val priceText = view.findViewById<TextView>(R.id.txtTwo)
        val WarrantyText = view.findViewById<TextView>(R.id.txtThree)
        val photo=view.findViewById<ImageView>(R.id.photo)
        val card=view.findViewById<CardView>(R.id.cardPdf)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
            return Viewholder(view)
    }


    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.nameText.text = "Name: " + itemArray[position].Name
        holder.priceText.text = "Price: " + itemArray[position].Price
        holder.WarrantyText.text = "Warranty: " + itemArray[position].warranty
        holder.photo.setImageResource(itemArray[position].imageId)
        holder.card.setOnClickListener {

            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putInt("photo",itemArray[position].imageId)
            args.putString("name",itemArray[position].Name)
            args.putString("price",itemArray[position].Price)
            args.putString("rating","4.5/5")
            args.putString("warranty",itemArray[position].warranty)
            fragment.arguments = args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .addToBackStack("NotesTwo")
                .replace(R.id.lytFrame, fragment, "NotesThree").commit()
        }

    }

    override fun getItemCount(): Int {
            return itemArray.size
    }



}