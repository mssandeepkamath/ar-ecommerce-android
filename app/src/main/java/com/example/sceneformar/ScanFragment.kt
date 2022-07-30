package com.example.sceneformar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

class ScanFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        val view=inflater.inflate(R.layout.fragment_scan, container, false)
        return view;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                val intent = Intent(activity, ARActivity::class.java)
             val name = when(it.text)
              {
                  "prod1" ->
                  {
                      "Laptop (Windows 10)"
                  }

                   "prod5" ->{
                       "Smart TV (Android)"
                   }

                    else ->
                    {
                        "no"
                    }
              }
                if(name.equals("no"))
                {
                    Toast.makeText(activity,"Sorry, currently AR view for this product isn't available",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    intent.putExtra("name",name)
                    startActivity(intent)
                }

            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


}