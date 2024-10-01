package com.example.debaleen.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String utcol1 = "Id";
    public static final String utcol2 = "Name";
    public static final String utcol3 = "Email";
    public static final String utcol4 = "Phone";
    public static final String utcol5 = "Gender";
    public static final String utcol6 = "Password";
    public static final String utcol7 = "Type";
    public static final String vtcol1 = "Id";
    public static final String vtcol2 = "CarNumber";
    public static final String vtcol3 = "Owner";
    public static final String vtcol4 = "Type";
    public static final String vtcol5 = "RegDate";
    public static final String vtcol6 = "Insurance";
    public static final String vtcol7 = "PollutionStatus";
    public static final String dlcol1 = "Id";
    public static final String dlcol2 = "DlNo";
    public static final String dlcol3 = "DlName";
    public static final String dlcol4 = "DlRelation";
    public static final String dlcol5 = "DlRegistrationDate";
    public static final String dlcol6 = "DlValidity";
    public static final String dlcol7 = "DlDOB";
    public static final String dlcol8 = "DlBloodGroup";
    public static final String dlcol9 = "DlVehicleType";
    public static final String dlcol10 = "DlAuthorityCode";
    public static final String dlcol11 = "DlAuthority";
    public static final String eccol1 = "Id";
    public static final String eccol2 = "PoliceName";
    public static final String eccol3 = "ChallanNumber";
    public static final String eccol4 = "VehicleNumber";
    public static final String eccol5 = "OwnerName";
    public static final String eccol6 = "LicenseNumber";
    public static final String eccol7 = "Date";
    public static final String eccol8 = "ViolateRule";
    public static final String eccol9 = "Amount";

    public static final String table1Name = "users";
    public static final String table2Name = "vehicles";
    public static final String table3Name = "drivingLicense";
    public static final String table4Name = "echallan";

    public static final String dbName = "PROJECT2.db";

    public DataBaseHelper(Context context)//constructor
    {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table1Name + "(" + utcol1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + utcol2 + " TEXT," + utcol3 + " TEXT," + utcol4 + " TEXT," + utcol5 + " TEXT," + utcol6 + " TEXT,"+ utcol7 + " TEXT)");
        db.execSQL("CREATE TABLE " + table2Name + "(" + vtcol1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + vtcol2 + " TEXT," + vtcol3 + " TEXT," + vtcol4 + " TEXT," + vtcol5 + " TEXT," + vtcol6 + " TEXT," + vtcol7 + " TEXT)");
        db.execSQL("CREATE TABLE " + table3Name + "(" + dlcol1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + dlcol2 + " TEXT," + dlcol3 + " TEXT," + dlcol4 + " TEXT," + dlcol5 + " TEXT," + dlcol6 + " TEXT," + dlcol7 + " TEXT," + dlcol8 +"TEXT," + dlcol9 +"TEXT," + dlcol10 +"TEXT," + dlcol11 +"TEXT)");
        db.execSQL("CREATE TABLE " + table4Name + "(" + eccol1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + eccol2 + " TEXT," + eccol3 + " TEXT," + eccol4 + " TEXT," + eccol5 + " TEXT," + eccol6 + " TEXT," + eccol7 + " TEXT," + eccol8 +"TEXT,"+ eccol9 +"TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table1Name);
        db.execSQL("DROP TABLE IF EXISTS " + table2Name);
        db.execSQL("DROP TABLE IF EXISTS " + table3Name);
        db.execSQL("DROP TABLE IF EXISTS " + table4Name);
    }

    public boolean insertIntoT1(String name, String email, String phone, String gender, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(utcol2, name);
        cv.put(utcol3, email);
        cv.put(utcol4, phone);
        cv.put(utcol5, gender);
        cv.put(utcol6, password);
        cv.put(utcol7, type);

        long res = db.insert(table1Name, null, cv);
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertIntoT2(String carNumber, String owner, String type, String regdate, String insurance, String pollutionstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(vtcol2, carNumber);
        cv.put(vtcol3, owner);
        cv.put(vtcol4, type);
        cv.put(vtcol5, regdate);
        cv.put(vtcol6, insurance);
        cv.put(vtcol7, pollutionstatus);

        long res = db.insert(table2Name, null, cv);
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertIntoT3(String dlno,String dlName,String dlRelative,String dlregd,String dlVal,String dlDOB,String dlbgrp,String dlvclass,String dlAutC,String dlAut)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(dlcol2,dlno);
        cv.put(dlcol3,dlName);
        cv.put(dlcol4,dlRelative);
        cv.put(dlcol5,dlregd);
        cv.put(dlcol6,dlVal);
        cv.put(dlcol7,dlDOB);
        cv.put(dlcol8,dlbgrp);
        cv.put(dlcol9,dlvclass);
        cv.put(dlcol10,dlAutC);
        cv.put(dlcol11,dlAut);
        long res= db.insert(table3Name,null,cv);
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean insertIntoT4(String policeName,String challanNumber,String vehicleNumber,String ownerName,String licenseNumber,String date,String rule,String amount)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(eccol2,policeName);
        cv.put(eccol3,challanNumber);
        cv.put(eccol4,vehicleNumber);
        cv.put(eccol5,ownerName);
        cv.put(eccol6,licenseNumber);
        cv.put(eccol7,date);
        cv.put(eccol8,rule);
        cv.put(eccol9,amount);
        long res= db.insert(table4Name,null,cv);
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean deleteFromT1(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(table1Name, utcol1 + "=?", new String[]{id});

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteFromT2(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(table2Name, vtcol1 + "=?", new String[]{id});

        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }
    /*
    public boolean Delete(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long res= db.delete(tableName,col1+"=?",new String[]{id});
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }*/

    public boolean updateInT1(String id, String name, String email, String phone, String gender, String password, String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(utcol2, name);
        cv.put(utcol3, email);
        cv.put(utcol4, phone);
        cv.put(utcol5, gender);
        cv.put(utcol6, password);
        cv.put(utcol7, type);

        long res = db.update(table1Name, cv, utcol1+"=?", new String[] {id});

        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean updateInT2(String id, String carNumber, String owner, String type, String regdate, String insurance, String pollutionstatus)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(vtcol2, carNumber);
        cv.put(vtcol3, owner);
        cv.put(vtcol4, type);
        cv.put(vtcol5, regdate);
        cv.put(vtcol6, insurance);
        cv.put(vtcol7, pollutionstatus);

        long res = db.update(table2Name, cv, vtcol1+"=?", new String[] {id});

        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean UpdateInT3(String id,String dlno,String dlName,String dlRelative,String dlregd,String dlVal,String dlDOB,String dlbgrp,String dlvclass,String dlAutC,String dlAut)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(dlcol2,dlno);
        cv.put(dlcol3,dlName);
        cv.put(dlcol4,dlRelative);
        cv.put(dlcol5,dlregd);
        cv.put(dlcol6,dlVal);
        cv.put(dlcol7,dlDOB);
        cv.put(dlcol8,dlbgrp);
        cv.put(dlcol9,dlvclass);
        cv.put(dlcol10,dlAutC);
        cv.put(dlcol11,dlAut);

        long res= db.update(table3Name,cv,dlcol1+"=?", new String[] {id});

        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean UpdateInT4(String id,String policeName,String challanNumber,String vehicleNumber,String ownerName,String licenseNumber,String date,String rule,String amount)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(eccol2,policeName);
        cv.put(eccol3,challanNumber);
        cv.put(eccol4,vehicleNumber);
        cv.put(eccol5,ownerName);
        cv.put(eccol6,licenseNumber);
        cv.put(eccol7,date);
        cv.put(eccol8,rule);
        cv.put(eccol9,amount);

        long res= db.update(table4Name,cv,eccol1+"=?", new String[] {id});

        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor displayAllInT1()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table1Name,null);
        return res;
    }

    public Cursor displayAllInT2()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table2Name,null);
        return res;
    }
    public Cursor displayAllInT3()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table3Name,null);
        return res;
    }
    public Cursor displayAllInT4()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table4Name,null);
        return res;
    }

    public Cursor displayByIdFromT1(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table1Name+" where "+utcol1+"='"+id+"'",null);
        return res;
    }

    public Cursor displayByIdFromT2(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table2Name+" where "+vtcol1+"='"+id+"'",null);
        return res;
    }
    public Cursor displayByIdFormT3(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table3Name+" where "+dlcol1+"='"+id+"'",null);
        return res;
    }
    public Cursor displayByIdFormT4(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table4Name+" where "+eccol1+"='"+id+"'",null);
        return res;
    }

    public Cursor displayByCarNumber(String carNumber)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table2Name+" where "+vtcol2+"='"+carNumber.trim()+"'", null);
        return res;
    }

    public Cursor displayByCarNumberOrOwnerName(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table2Name+" where "+vtcol2+"='"+query.trim()+"' or "+vtcol3+"='"+query.trim()+"'", null);
        return res;
    }

    public Cursor displayByEmailPasswordType(String email, String password, String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table1Name+" where "+utcol3+"='"+email.trim()+"' and "+utcol6+"='"+password+"' and "+utcol7+"='"+type+ "'", null);
        return res;
    }

    public Cursor displayByEmail(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table1Name+" where "+utcol3+"='"+email.trim()+"'", null);
        return res;
    }
    /*public Cursor DisplayByEmail(String em)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+tableName+" where "+col3+"='"+em.trim()+"'",null);
        return res;
    }
    public Cursor DisplayByEmailPassword(String em,String pw)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from Student where Email='"+em.trim()+"'"+" and Password='"+pw.trim()+"'",null);
        return res;
    }
}*/


}