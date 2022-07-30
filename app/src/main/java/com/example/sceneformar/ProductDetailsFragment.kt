package com.example.sceneformar


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
import androidx.fragment.app.Fragment


class ProductDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        val nameText = view.findViewById<TextView>(R.id.txtOneD)
        val priceText = view.findViewById<TextView>(R.id.txtTwoD)
        val WarrantyText = view.findViewById<TextView>(R.id.txtThreeD)
        val ratingText = view.findViewById<TextView>(R.id.txtFourD)
        val photoImage = view.findViewById<ImageView>(R.id.photoD)
        val buttonAr = view.findViewById<Button>(R.id.buttonOneD)
        val buttonBuyNow = view.findViewById<Button>(R.id.buttonTwoD)
        val photo = arguments?.getInt("photo")
        val name = arguments?.getString("name")
        val rating = arguments?.getString("rating")
        val price = arguments?.getString("price")
        val waranty = arguments?.getString("warranty")
        photoImage.setImageResource(photo!!)
        nameText.text = "Product name: " + name.toString()
        priceText.text = "Price: " + price.toString()
        ratingText.text = "Ratings: " + rating.toString()
        WarrantyText.text = "Warranty: " + waranty.toString()
        buttonAr.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
        buttonBuyNow.setOnClickListener {
            val appName = "Metamask"
            val packageName = "io.metamask"
//            openApp(activity as Context, appName, packageName)
            var intent = activity?.packageManager?.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                Toast.makeText(context,
                    "Set Metamask app as your default browser for transaction\n", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://flipkartgrid.vercel.app"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(Intent.createChooser(intent,"USE METAMASK ONLY"))
            } else {
                // Bring user to the market or let them choose an app?
                intent = Intent(Intent.ACTION_VIEW)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                Toast.makeText(context,
                "$appName app is not found.", Toast.LENGTH_SHORT).show()
                intent.data = Uri.parse("market://details?id=$packageName")
                startActivity(intent)
            }
        }
        return view
    }

//    fun openApp(context: Context, appName: String, packageName: String) {
//        if (isAppInstalled(context, packageName))
//
//            if (isAppEnabled(context, packageName))
//            {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://flipkartgrid.vercel.app"))
//                startActivity(Intent.createChooser(int))
//            }
//
//            else Toast.makeText(context,
//                "$appName app is not enabled.", Toast.LENGTH_SHORT).show()
//
//        else Toast.makeText(context,
//            "$appName app is not installed.", Toast.LENGTH_SHORT).show()
//    }

//    private fun isAppInstalled(context: Context, packageName: String): Boolean {
//        val pm: PackageManager = context.getPackageManager()
//        try {
//            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
//            return true
//        } catch (ignored: PackageManager.NameNotFoundException) {
//        }
//        return false
//    }
//
//    private fun isAppEnabled(context: Context, packageName: String): Boolean {
//        var appStatus = false
//        try {
//            val ai: ApplicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0)
//            if (ai != null) {
//                appStatus = ai.enabled
//            }
//        } catch (e: PackageManager.NameNotFoundException) {
//            e.printStackTrace()
//        }
//        return appStatus
//    }


}