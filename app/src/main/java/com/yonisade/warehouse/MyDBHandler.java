package com.yonisade.warehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1 ;
    private static final String DATABASE_NAME = "items.db";
    private static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ID = "item_id";
    private static final String COLUMN_ITEM_NAME = "item_name";
    private static final String COLUMN_AMOUNT = "item_amount";
    private static final String COLUMN_IMAGE = "item_img";

    //<editor-fold desc="House Kipping">

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query  = "CREATE TABLE "  + TABLE_ITEMS + "(" +

                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ITEM_NAME + " TEXT unique," +
                COLUMN_AMOUNT + " TEXT," +
                COLUMN_IMAGE + " BLOB )";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    //</editor-fold>

    //<editor-fold desc = "clear , add item , get Items , get all items in itemArray , remove item method's">

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS,null,null);
    }

    public void addItem(Item myitem){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ITEM_NAME, myitem.getItem_name());
        values.put(COLUMN_AMOUNT, myitem.getItem_amount());
        values.put(COLUMN_IMAGE, myitem.getItem_img());
        db.insert(TABLE_ITEMS, null, values);
    }

    public Cursor getItemForList(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor courser = db.rawQuery("SELECT * FROM " + TABLE_ITEMS, null);
        return courser;
    }

    public List <Item> getAllitems() {

        List<Item> all_items = new ArrayList<Item>();
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Item item = new Item();
                item.setItem_name(c.getString(c.getColumnIndex(COLUMN_ITEM_NAME)));
                item.setItem_amount(c.getString(c.getColumnIndex(COLUMN_AMOUNT)));
                item.setItem_img(c.getBlob(c.getColumnIndex(COLUMN_IMAGE)));
                db.close();

                all_items.add(item);
            } while (c.moveToNext());
        }
        c.close();
        return all_items;
    }

    public void removeItemByName(String myItemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_ITEMS,COLUMN_ITEM_NAME + "='" + myItemName+"'",null);
        db.close();
    }

    //</editor-fold>

    //<editor-fold desc= "Decrease/ Increase Amount">

    public void DecreaseAmount(String ItemName , String Amount){

        SQLiteDatabase db = this.getReadableDatabase();
        String myQuarry = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEM_NAME

                +  " = '" + ItemName + "'";

        Cursor c = db.rawQuery(myQuarry, null);
        if(c.getCount() <= 0){
            c.close();
            return;
        }
        else
        {
            c.moveToFirst();
        }

        String curAmount = c.getString(c.getColumnIndex(COLUMN_AMOUNT));
        int temp = Integer.parseInt(curAmount);
        int decrease = Integer.parseInt(Amount);
        temp-=decrease;
        String newAmount= Integer.toString(temp);

        String newQuarry = "UPDATE "+ TABLE_ITEMS +" SET " + COLUMN_AMOUNT + "=" + newAmount +  " WHERE item_name='" + ItemName +"'"+ "";
        db.execSQL(newQuarry);
        db.close();

    }

    public void IncreaseAmount(String ItemName , String Amount){

        SQLiteDatabase db = this.getReadableDatabase();
        String myQuarry = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEM_NAME

                +  " = '" + ItemName + "'";

        Cursor c = db.rawQuery(myQuarry, null);
        if(c.getCount() <= 0){
            c.close();
            return;
        }
        else
        {
            c.moveToFirst();
        }

        String curAmount = c.getString(c.getColumnIndex(COLUMN_AMOUNT));
        int temp = Integer.parseInt(curAmount);
        int increase = Integer.parseInt(Amount);
        temp+=increase;
        String newAmount= Integer.toString(temp);

        String newQuarry = "UPDATE "+ TABLE_ITEMS +" SET " + COLUMN_AMOUNT + "=" + newAmount +  " WHERE item_name='" + ItemName +"'"+ "";

        db.execSQL(newQuarry);
        db.close();

    }

    //</editor-fold>

}
