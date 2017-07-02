package com.yonisade.warehouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class HomeActivity extends Activity {

    ImageView img;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getSupportActionBar().hide();

        img = (ImageView)findViewById(R.id.HomeImageView);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        img.startAnimation(animation);

    }

    //<editor-fold desc="Move between activates">
    public void InventoryButtonClicked(View view){
        Intent i = new Intent(this,Inventory.class);
        startActivity(i);
    }
    public void AddInventoryButtonClicked(View view){
        Intent i = new Intent(this,AddInventory.class);
        startActivity(i);
    }


    //</editor-fold>

}
