package com.yonisade.warehouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateMember extends Activity {

    String ItemName;
    TextView itemNameTV;
    EditText itemAmountET;
    Button removeAmount;
    Button addAmount;
    String Amount;
    MyDBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_member);

        // Get ItemName from Inventory Screen //
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            ItemName= null;
        } else {
            ItemName= extras.getString("myString");
        }

        db = new MyDBHandler(this);
        itemNameTV = (TextView)findViewById(R.id.itemNameText);
        itemAmountET = (EditText) findViewById(R.id.updateAmountText);
        removeAmount = (Button)findViewById(R.id.btn_remove);
        addAmount = (Button)findViewById(R.id.btn_add);

        itemNameTV.setText(ItemName);

    }

    public void RemoveButtonClicked(View v){

        Amount = itemAmountET.getText().toString();
        if(Amount.isEmpty())
        {
            return;
        }
        db.DecreaseAmount(ItemName, Amount);
        returnToInventory();
    }

    public void AddButtonClicked(View v){

        Amount = itemAmountET.getText().toString();
        if(Amount.isEmpty())
        {
            return;
        }
        db.IncreaseAmount(ItemName,Amount);
        returnToInventory();
    }

    public void returnToInventory(){

        finish();
        Intent i  = new Intent(this,Inventory.class);
        startActivity(i);
    }

    public void Delete_Item(View view){

        db.removeItemByName(ItemName);
        finish();
        Toast.makeText(UpdateMember.this, "הפריט נמחק", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,Inventory.class);
        finish();
        startActivity(intent);
    }

}
