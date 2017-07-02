package com.yonisade.warehouse;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class Inventory extends Activity {

    ListView listView;
    String[] itemname;
    TextView txt;
    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        db = new MyDBHandler(this);
        txt = (TextView) findViewById(R.id.database);
        listView = (ListView) findViewById(R.id.InventoryList);
        List<Item> itemsArray = db.getAllitems();
        final Cursor data = db.getItemForList();

        String[] namesArray = new String[data.getCount()];
        String[] amountArray = new String[data.getCount()];
        byte[][] imagesArray = new byte[data.getCount()][];
        Item tempItem;
        byte[] tempByte = new byte[0];

        // from table to itemArray //

        for (int i = 0; i < itemsArray.size(); i++) {
            tempItem = new Item(itemsArray.get(i));
            namesArray[i] = tempItem.getItem_name();
            amountArray[i] = tempItem.getItem_amount();
            if (tempItem.getItem_img() == null) {
                imagesArray[i] = tempByte;

            } else {
                imagesArray[i] = tempItem.getItem_img();
            }

        }
        // Transforming from byte[] to bitmap //

        Bitmap defaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        Bitmap[] bitmapArray = new Bitmap[data.getCount()];

        try {
            for (int i = 0; i < data.getCount(); i++) {
                if (imagesArray[i] == tempByte) // if there is no Image
                {
                    bitmapArray[i] = defaultBitmap;
                } else {
                    bitmapArray[i] = ByteToBitmap(imagesArray[i]);
                }
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Yoni Exception!");
        }

        db.close();

        // showing the list on screen //

        if (itemsArray.size() == 0)
            Toast.makeText(Inventory.this, "המאגר ללא פריטים", Toast.LENGTH_LONG).show();
        else {
            CustomListAdapter adapter = new CustomListAdapter(this, namesArray, amountArray, bitmapArray);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    String Slecteditem = itemname[+position];
                    Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                }
            });
        }

        // mange click's on list //

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String itemName = listView.getItemAtPosition(i).toString();
                moveToUpdateMember(itemName);

    }});

    }

    //<editor-fold desc="From byte[] to Bitmap Method">

    public static Bitmap ByteToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    //</editor-fold>

    //<editor-fold desc="move To Update Member with Item name">
    public void moveToUpdateMember(String itemName) {

        finish(); // close not updated Inventory
        Intent i = new Intent(this,UpdateMember.class);
        i.putExtra("myString",itemName);
        startActivity(i);

    }
    //</editor-fold>

}

    // <editor-fold desc = "Notice for my self - how to print my database">

            /*
            info = "";
            Cursor data = db.getItemForList();
            if(data.getCount()==0){
                Toast.makeText(Inventory.this, "המאגר עדיין ריק", Toast.LENGTH_LONG).show();
            }else{
                while(data.moveToNext()){
                    info += data.getString(0) + "\n" + data.getString(1) + "\n" + data.getString(2) + "\n" ;
                    //list.add(data.getString(1));//column 2 is index of column-name
                }
            }

            txt.setText(info);
            */

//</editor-fold>


