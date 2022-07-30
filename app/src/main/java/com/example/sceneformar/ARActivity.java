package com.example.sceneformar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.HashMap;
import java.util.Objects;

public class ARActivity extends AppCompatActivity {
    private String name;
    private ArFragment arCam;
     HashMap<String, ARProductData> hashMap= new HashMap();
    // helps to render the 3d model
    // only once when we tap the screen
    private int clickNo = 0;

    public static boolean checkSystemSupport(Activity activity) {

        // checking whether the API version of the running Android >= 24
        // that means Android Nougat 7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            // checking whether the OpenGL version >= 3.0
            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        hashMap.put("Laptop (Windows 10)",new ARProductData(R.raw.laptop_ar_flipkart,0.59f,0.6f));
        hashMap.put("Smart TV (Android)",new ARProductData(R.raw.monitor_ar_flipkart,0.79f,0.8f));
        hashMap.put("Gaming PC (Windows 11, AMD)",new ARProductData(R.raw.pc_ar_flipkart,0.69f,0.7f));
        hashMap.put("Washing Machine (5kg)",new ARProductData(R.raw.fridge_ar_flipkart,1.1f,1.2f));
        hashMap.put("Wooden bed (Double)",new ARProductData(R.raw.wodden_beg_ar_flipkart,1.3f,1.4f));
        hashMap.put("Swing chair",new ARProductData(R.raw.swing_chair_ar_flipkart,0.9f,1.2f));
        hashMap.put("Sofa (black)",new ARProductData(R.raw.sofa_ar_flipkart,1.4f,1.5f));
        hashMap.put("Bed lamp",new ARProductData(R.raw.lamp_ar_flipkart,0.5f,0.6f));
        hashMap.put("Soldier toy",new ARProductData(R.raw.soldier_ar_flipkart,0.5f,0.6f));
        hashMap.put("Teddy bear (15 cm x40 cm)",new ARProductData(R.raw.teddy_ar_flipkart,0.5f,0.51f));
        hashMap.put("Bicycle (5 gears)",new ARProductData(R.raw.cycle_ar_view,1.4f,1.45f));
        hashMap.put("Chick toy",new ARProductData(R.raw.chick,0.5f,0.6f));
        
        
        
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             name = extras.getString("name");
        }
        if (checkSystemSupport(this)) {



            // ArFragment is linked up with its respective id used in the activity_main.xml
            arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

            assert arCam != null;
            arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                clickNo++;



                // the 3d model comes to the scene only
                // when clickNo is one that means once
                if (clickNo == 1) {
                    Anchor anchor = hitResult.createAnchor();
                    ModelRenderable.builder()
                            .setSource(this,hashMap.get(name).getId())
                            .setIsFilamentGltf(true)
                            .build()
                            .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                return null;
                            });
                }
            });
        }

    }
    private void addModel(Anchor anchor, ModelRenderable modelRenderable) {

        // Creating a AnchorNode with a specific anchor
        AnchorNode anchorNode = new AnchorNode(anchor);

        // attaching the anchorNode with the ArFragment
        anchorNode.setParent(arCam.getArSceneView().getScene());

        // attaching the anchorNode with the TransformableNode
        TransformableNode model = new TransformableNode(arCam.getTransformationSystem());
        model.setParent(anchorNode);
        model.getScaleController().setMaxScale(hashMap.get(name).getMax());
        model.getScaleController().setMinScale(hashMap.get(name).getMin());

        // attaching the 3d model with the TransformableNode
        // that is already attached with the node
        model.setRenderable(modelRenderable);
        model.select();
    }




}

