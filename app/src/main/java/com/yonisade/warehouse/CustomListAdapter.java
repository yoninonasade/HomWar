package com.yonisade.warehouse;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] itemamount;
    private final Bitmap[] imgid;

    // Constructor //

    public CustomListAdapter(Activity context, String[] itemname,String[] itemamount, Bitmap[] imgid) {
        super(context, R.layout.inventory_member, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.itemamount=itemamount;
        this.imgid=imgid;
    }

    // Creating the List View According to inventory member //

    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.inventory_member, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.inventoryItemNametextView);
        TextView extratxt = (TextView) rowView.findViewById(R.id.inventoryItemAmounttextView);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.inventoryImagetextView);

        txtTitle.setText(itemname[position]);
        imageView.setImageBitmap(imgid[position]);
        extratxt.setText("כמות : "+itemamount[position]);
        return rowView;

    };
}