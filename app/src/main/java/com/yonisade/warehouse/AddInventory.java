package com.yonisade.warehouse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;


public class AddInventory extends Activity {

    //<editor-fold desc="attributes">

    MyDBHandler db;
    static final int REQUEST_IMAGE_CAPTURE = 0;
    ImageView myImageView;
    byte[] imageMe;
    Button DoneButton;
    EditText ItemNameEditText;
    EditText ItemAmountEdieText;

    //</editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        db=new MyDBHandler(this);
        DoneButton = (Button) findViewById(R.id.addYoInventoryDone);
        ItemNameEditText = (EditText) findViewById(R.id.EnterNewItemName);
        ItemAmountEdieText = (EditText) findViewById(R.id.EnterNewItemAmount);
        myImageView = (ImageView) findViewById(R.id.imageView);
    }

    //<editor-fold desc="Dealing With Camera">


    public void takePicture_onClick(View v) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            myImageView = (ImageView) findViewById(R.id.imageView);
            myImageView.setImageBitmap(photo);
            imageMe = getBytes(photo);
        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , stream);
        return stream.toByteArray();
    }

    //</editor-fold>

    //<editor-fold desc= "Working with DB">

    public void DoneButtonClicked(View v){

        String itemName = ItemNameEditText.getText().toString().trim();
        String itemAmount = ItemAmountEdieText.getText().toString().trim();
        // Item checkName = db.getRecipeByName(Name); add a check if this on DB already

        if(itemName.isEmpty())
            ItemNameEditText.setError("נא הכנס שם פריט");
        if(itemAmount.isEmpty()){
            ItemAmountEdieText.setError("נא הכנס כמות");
        }
        else {
            if(imageMe==null) // if there is no photo
            {
                Item item = new Item(ItemNameEditText.getText().toString().trim(), ItemAmountEdieText.getText().toString());
                db.addItem(item);
            }
            else // if there is photo
            {
                Item item = new Item(ItemNameEditText.getText().toString().trim(), ItemAmountEdieText.getText().toString(), imageMe);
                db.addItem(item);
            }

            Toast.makeText(AddInventory.this, "נוסף בהצלחה!", Toast.LENGTH_SHORT).show();

            finish();

        }
    }
    //</editor-fold>

}
