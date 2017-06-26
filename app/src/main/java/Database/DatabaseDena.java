package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import Constant.ConstantLena;
import Constant.ConstantsDena;
import Constant.ConstantsEvent;
import Model.DataOfDebt;

/**
 * Created by Dhanraj on 03-01-2017.
 */
public class DatabaseDena extends SQLiteOpenHelper {
    private final ArrayList<DataOfDebt> sumofdebt = new ArrayList<>();
    private final ArrayList<DataOfDebt> denewaladata = new ArrayList<>();
    private final ArrayList<DataOfDebt> denewaladata1 = new ArrayList<>();

    private final ArrayList<DataOfDebt> sumofowedmoney = new ArrayList<>();
    private final ArrayList<DataOfDebt> lenewaladata = new ArrayList<>();
    private final ArrayList<DataOfDebt> lenewaladata1 = new ArrayList<>();

    private final ArrayList<DataOfDebt> eventwaladata = new ArrayList<>();
    private final ArrayList<DataOfDebt> listofMEMBERS = new ArrayList<>();

    private final ArrayList<DataOfDebt> listofEXPEND = new ArrayList<>();

    private static final String create_event_table="CREATE TABLE " + ConstantsEvent.Table_Name+
            " ("+ConstantsEvent.Key_Id+" INTEGER PRIMARY KEY AUTOINCREMENT," + ConstantsEvent.EventName+ " TEXT,"+
            ConstantsEvent.Time + " LONG)";

    private static  final String create_event_expend_table="CREATE TABLE " + ConstantsEvent.List_of_expend_table+
            " ("+ConstantsEvent.List_Key_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ConstantsEvent.nameofmembers+ " TEXT,"+
            ConstantsEvent.reasonofexpend + " TEXT,"+ConstantsEvent.parent_id+" INTEGER,"+ConstantsEvent.amountofexpend + " INTEGER,"+ ConstantsEvent.Time+
            " LONG)";

    private static final String Create_debt_table="CREATE TABLE " + ConstantsDena.Debt_Table_Name+
            " ("+ConstantsDena.Debt_Key_id+" INTEGER PRIMARY KEY AUTOINCREMENT," + ConstantsDena.Debt+
            " INTEGER," + ConstantsDena.Time + " LONG,"+ConstantsDena.radio_value + " TEXT," +ConstantsDena.ReasonOFDebt + " TEXT,"+
            ConstantsDena.parent_id+" INTEGER)";

    private static final String Create_Owed_table="CREATE TABLE " + ConstantLena.owed_Table_Name+
            " ("+ConstantLena.Owed_Key_id+" INTEGER PRIMARY KEY AUTOINCREMENT," + ConstantLena.Owed_money+
            " INTEGER," + ConstantLena.Time + " LONG,"+ConstantLena.radio_value + " TEXT," +ConstantLena.ReasonOFOwingMoney + " TEXT,"+
            ConstantLena.parent_id+" INTEGER)";

    private static final String create_listofmembers_table = "CREATE TABLE " + ConstantsEvent.List_of_members_table+
            " ("+ConstantsEvent.List_Key_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ConstantsEvent.nameofmembers+" TEXT,"+
            ConstantsEvent.EmailID+ " TEXT,"+ConstantsEvent.parent_id+" INTEGER)";

    public DatabaseDena(Context context) {
        super(context, ConstantsDena.Database_Name, null, ConstantsDena.Database_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_ListofUdhari_Table = "CREATE TABLE " + ConstantsDena.Table_Name+
                " ("+ ConstantsDena.Key_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ ConstantsDena.PersonName +
                " TEXT," + ConstantsDena.MobileNumber + "  TEXT," + ConstantsDena.EmailID + " TEXT)";

        String Create_Oweing_money_Table = "CREATE TABLE " + ConstantLena.Table_Name+
                " ("+ ConstantLena.Key_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ ConstantLena.PersonName +
                " TEXT," + ConstantLena.MobileNumber + "  TEXT," + ConstantLena.EmailID + " TEXT)";

        db.execSQL(create_event_expend_table);
        db.execSQL(create_event_table);
        db.execSQL(create_listofmembers_table);
        db.execSQL(Create_Owed_table);
        db.execSQL(Create_Oweing_money_Table);
        db.execSQL(Create_ListofUdhari_Table);
        db.execSQL(Create_debt_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF IT EXISTS "+ ConstantsDena.Table_Name);
        db.execSQL("DROP TABLE IF IT EXISTS "+ ConstantLena.owed_Table_Name);
        db.execSQL("DROP TABLE IF IT EXISTS "+ ConstantLena.Table_Name);
        db.execSQL("DROP TABLE IF IT EXISTS "+ ConstantsDena.Debt_Table_Name);
        db.execSQL("DROP TABLE IF IT EXISTS "+ ConstantsEvent.Table_Name);
        db.execSQL("DROP TABLE IF IT EXISTS "+ ConstantsEvent.List_of_members_table);
        db.execSQL("DROP TABLE IF IT EXISTS "+ ConstantsEvent.List_of_expend_table);
        onCreate(db);

    }

    public void addData(DataOfDebt data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantsDena.PersonName,data.getName());
        contentValues.put(ConstantsDena.MobileNumber,data.getNumber());
        contentValues.put(ConstantsDena.EmailID,data.getEmailid());


        db.insert(ConstantsDena.Table_Name,null,contentValues);
        Log.v("DHANRAJ","check1");
        db.close();

    }


    public void addData1(DataOfDebt data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantLena.PersonName,data.getName());
        contentValues.put(ConstantLena.MobileNumber,data.getNumber());
        contentValues.put(ConstantLena.EmailID,data.getEmailid());



        db.insert(ConstantLena.Table_Name,null,contentValues);
        Log.v("DHANRAJ SAhu","check2");
        db.close();
    }

    public void addEvent(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantsEvent.EventName,name);
        contentValues.put(ConstantsEvent.Time,String.valueOf(java.lang.System.currentTimeMillis()));


        db.insert(ConstantsEvent.Table_Name,null,contentValues);
        Log.v("DHANRAJ SAhu","check3");
        db.close();
    }




    public void addDebt(DataOfDebt data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(ConstantsDena.Debt,data.getDebtamount());
        contentValues.put(ConstantsDena.Time,String.valueOf(java.lang.System.currentTimeMillis()));
        contentValues.put(ConstantsDena.parent_id,data.getParentid());
        contentValues.put(ConstantsDena.radio_value,"Not paid");
        contentValues.put(ConstantsDena.ReasonOFDebt,data.getReasonofdebt());


        db.insert(ConstantsDena.Debt_Table_Name,null,contentValues);
        Log.v("DHANRAJ SAhu","check4");
        db.close();
    }

    public void addDebt1(DataOfDebt data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(ConstantLena.Owed_money,data.getDebtamount());
        contentValues.put(ConstantLena.Time,String.valueOf(java.lang.System.currentTimeMillis()));
        contentValues.put(ConstantLena.parent_id,data.getParentid());
        contentValues.put(ConstantLena.radio_value,"Not Taken");
        contentValues.put(ConstantLena.ReasonOFOwingMoney,data.getReasonofdebt());


        db.insert(ConstantLena.owed_Table_Name,null,contentValues);
        Log.v("DHANRAJ SAhu","check5");
        db.close();
    }

    public void addMember1(DataOfDebt data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(ConstantsEvent.nameofmembers,data.getName());
        contentValues.put(ConstantsEvent.parent_id,data.getParentid());
        contentValues.put(ConstantsEvent.EmailID,data.getEmailid());


        db.insert(ConstantsEvent.List_of_members_table,null,contentValues);
        Log.v("DHANRAJ SAhu","check6");
        db.close();
    }

    public void addexpend(DataOfDebt dena)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantsEvent.nameofmembers,dena.getName());
        contentValues.put(ConstantsEvent.parent_id,dena.getParentid());
        contentValues.put(ConstantsEvent.reasonofexpend,dena.getReasonofdebt());
        contentValues.put(ConstantsEvent.amountofexpend,dena.getDebtamount());
        contentValues.put(ConstantsEvent.Time,String.valueOf(java.lang.System.currentTimeMillis()));

        db.insert(ConstantsEvent.List_of_expend_table,null,contentValues);
        Log.v("DHANRAJ SAhu","check7");
        db.close();

    }


    public void addradio(String text,Integer ID,String UpdatedReason,String upadtedDebt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantsDena.radio_value,text);
        contentValues.put(ConstantsDena.ReasonOFDebt,UpdatedReason);
        contentValues.put(ConstantsDena.Debt,upadtedDebt);


        db.update(ConstantsDena.Debt_Table_Name,contentValues,ConstantsDena.Debt_Key_id + " = "+ID,null);
        Log.v("DHANRAJ SAhu","check8"+ID+","+text);
        db.close();
    }

    public void addradio1(String text,Integer ID,String UpdatedReason,String upadtedDebt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantLena.radio_value,text);
        contentValues.put(ConstantLena.ReasonOFOwingMoney,UpdatedReason);
        contentValues.put(ConstantLena.Owed_money,upadtedDebt);


        db.update(ConstantLena.owed_Table_Name,contentValues,ConstantLena.Owed_Key_id + " = "+ID,null);
        Log.v("DHANRAJ SAhu","check9"+ID+","+text);
        db.close();
    }

    public void updatetheinfoofmember(DataOfDebt dena)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantsEvent.EmailID,dena.getEmailid());
        contentValues.put(ConstantsEvent.nameofmembers,dena.getName());

        db.update(ConstantsEvent.List_of_members_table,contentValues,ConstantsEvent.List_Key_id+ " = "+dena.getDebt_keyid(),null);

        db.close();
    }

    public void updatetheinfoofexpend(DataOfDebt dena)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantsEvent.nameofmembers,dena.getName());
        contentValues.put(ConstantsEvent.reasonofexpend,dena.getReasonofdebt());
        contentValues.put(ConstantsEvent.amountofexpend,dena.getDebtamount());


        db.update(ConstantsEvent.List_of_expend_table,contentValues,ConstantsEvent.List_Key_id+ " = "+dena.getDebt_keyid(),null);

        db.close();
    }




    public ArrayList<DataOfDebt> getDataDebt(Integer ID)
    {
        String selectQuery = "SELECT * FROM " + ConstantsDena.Debt_Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();



      /*  SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(ConstantsDena.Debt_Table_Name
                        + " INNER JOIN "
                        + ConstantsDena.Table_Name
                        + " ON "
                        + ConstantsDena.parent_id
                        + " = "
                        + (ConstantsDena.Table_Name + "." + ConstantsDena.Key_Id));

        Cursor cursor = queryBuilder.query(db,new String[]{ConstantsDena.Debt_Table_Name+ "." +ConstantsDena.Debt_Key_id,ConstantsDena.Debt,
             ConstantsDena.Time,ConstantsDena.parent_id},null,null,null,null,null);*/

        Cursor cursor = db.query(ConstantsDena.Debt_Table_Name,new String[]{ConstantsDena.Debt_Key_id,
                ConstantsDena.Debt,ConstantsDena.Time,ConstantsDena.parent_id,ConstantsDena.radio_value,ConstantsDena.ReasonOFDebt},
                ConstantsDena.parent_id + " = "+ ID + "",null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();

                dena.setDebt_keyid(cursor.getInt(cursor.getColumnIndex(ConstantsDena.Debt_Key_id)));
                dena.setRadiostringvalue(cursor.getString(cursor.getColumnIndex(ConstantsDena.radio_value)));
                dena.setParentid(cursor.getInt(cursor.getColumnIndex(ConstantsDena.parent_id)));
                dena.setDebtamount(cursor.getString(cursor.getColumnIndex(ConstantsDena.Debt)));
                dena.setReasonofdebt(cursor.getString(cursor.getColumnIndex(ConstantsDena.ReasonOFDebt)));
                java.text.DateFormat dateFormat =java.text.DateFormat.getDateInstance();
                String dataData = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(ConstantsDena.Time))).getTime());



                dena.setTime(dataData);

                denewaladata1.add(dena);

            }while(cursor.moveToNext());
        }
        Log.v("DHANRAJ ssss","check10");
        return  denewaladata1;
    }



    public  ArrayList<DataOfDebt> getDebt()
    {
        String selectQuery = "SELECT * FROM " + ConstantsDena.Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(ConstantsDena.Table_Name,new String[]{ConstantsDena.Key_Id,ConstantsDena.PersonName,
        ConstantsDena.MobileNumber,ConstantsDena.EmailID},null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();
                dena.setUdhariTableId(cursor.getInt(cursor.getColumnIndex(ConstantsDena.Key_Id)));
                dena.setName(cursor.getString(cursor.getColumnIndex(ConstantsDena.PersonName)));
                dena.setNumber(cursor.getString(cursor.getColumnIndex(ConstantsDena.MobileNumber)));
                dena.setEmailid(cursor.getString(cursor.getColumnIndex(ConstantsDena.EmailID)));



                denewaladata.add(dena);

            }while(cursor.moveToNext());
        }
        Log.v("DHANRAJ hhhh","check11");
        return denewaladata;
    }


    public void UpdateNumber(Integer ID,String Number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantsDena.MobileNumber,Number);
        Integer args[] = {ID};
        db.update(ConstantsDena.Table_Name,contentValues,ConstantsDena.Key_Id + " = " +ID,null);
        db.close();
    }


    public void UpdateNumber1(Integer ID, String Number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantLena.MobileNumber,Number);
        Integer args[] = {ID};
        db.update(ConstantLena.Table_Name,contentValues,ConstantLena.Key_Id + " = " +ID,null);
        db.close();
    }
    public void UpdateEmail(Integer ID,String Emailid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantsDena.EmailID,Emailid);
        Integer args[] = {ID};
        db.update(ConstantsDena.Table_Name,contentValues,ConstantsDena.Key_Id + " = " +ID,null);
        db.close();
    }

    public void UpdateEmail1(Integer ID,String Emailid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantLena.EmailID,Emailid);
        Integer args[] = {ID};
        db.update(ConstantLena.Table_Name,contentValues,ConstantLena.Key_Id + " = " +ID,null);
        db.close();
    }

    public void deleteRowofinnerlist(Integer ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsDena.Debt_Table_Name,ConstantsDena.parent_id +" = "+ID,null);
        db.close();
    }

    public void deleteRowofouterlist(Integer ID)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsDena.Table_Name,ConstantsDena.Key_Id + " = " +ID,null);
        db.close();

    }


    public void deleteRowofinnerlist1(Integer ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantLena.owed_Table_Name,ConstantLena.parent_id +" = "+ID,null);
        db.close();
    }

    public void deleteRowofinnerlistmember(Integer ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsEvent.List_of_members_table,ConstantsEvent.parent_id+" = "+ID,null);
        db.close();
    }

    public void deleteRowofinnerlistexpend(Integer ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsEvent.List_of_expend_table,ConstantsEvent.parent_id+" = "+ID,null);
        db.close();
    }

    public void deleteRowofouterlistevent(Integer ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsEvent.Table_Name,ConstantsEvent.Key_Id+" = "+ID,null);
        db.close();
    }

    public void deleteRowofouterlist1(Integer ID)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantLena.Table_Name,ConstantLena.Key_Id + " = " +ID,null);
        db.close();

    }


    public ArrayList<DataOfDebt> getsumofdebt(Integer ID) {


        String selectQuery = "SELECT * FROM " + ConstantsDena.Debt_Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();



      /*  SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(ConstantsDena.Debt_Table_Name
                        + " INNER JOIN "
                        + ConstantsDena.Table_Name
                        + " ON "
                        + ConstantsDena.parent_id
                        + " = "
                        + (ConstantsDena.Table_Name + "." + ConstantsDena.Key_Id));

        Cursor cursor = queryBuilder.query(db,new String[]{ConstantsDena.Debt_Table_Name+ "." +ConstantsDena.Debt_Key_id,ConstantsDena.Debt,
             ConstantsDena.Time,ConstantsDena.parent_id},null,null,null,null,null);*/

        Cursor cursor = db.query(ConstantsDena.Debt_Table_Name,new String[]{ConstantsDena.Debt,ConstantsDena.radio_value,ConstantsDena.parent_id},
                ConstantsDena.parent_id + " = "+ ID,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();

                dena.setSumofdebt(cursor.getInt(cursor.getColumnIndex(ConstantsDena.Debt)));
                dena.setRadiostringvalue(cursor.getString(cursor.getColumnIndex(ConstantsDena.radio_value)));

                sumofdebt.add(dena);


            }while(cursor.moveToNext());
        }
        Log.v("DHANRAJ ssss","check12");
        return  sumofdebt;
    }

    public void deleteRowofinnerlist2(Integer iDuwanttodelete)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsDena.Debt_Table_Name,ConstantsDena.Debt_Key_id +" = "+iDuwanttodelete,null);
        db.close();
    }

    public void deleteRowofinnerlist22(Integer iDuwanttodelete)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantLena.owed_Table_Name,ConstantLena.Owed_Key_id +" = "+iDuwanttodelete,null);
        db.close();
    }

    public void deleteRowofinnerlistevent(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsEvent.List_of_members_table,ConstantsEvent.List_Key_id +" = "+id,null);
        db.close();
    }

    public void deleteRowofinnerlisteventexpend(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantsEvent.List_of_expend_table,ConstantsEvent.List_Key_id +" = "+id,null);
        db.close();
    }

    public ArrayList<DataOfDebt> getEvent()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(ConstantsEvent.Table_Name,new String[]{ConstantsEvent.Key_Id,ConstantsEvent.EventName,
        ConstantsEvent.Time},null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();
                dena.setUdhariTableId(cursor.getInt(cursor.getColumnIndex(ConstantsEvent.Key_Id)));
                dena.setName(cursor.getString(cursor.getColumnIndex(ConstantsEvent.EventName)));
                java.text.DateFormat dateFormat =java.text.DateFormat.getDateInstance();
                String dataDataofevent = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(ConstantsEvent.Time))).getTime());
                dena.setTime(dataDataofevent);


                eventwaladata.add(dena);

            }while(cursor.moveToNext());
        }
        Log.v("DHANRAJ hhhh","check13");
        return eventwaladata;

    }
    public ArrayList<DataOfDebt> getOwedmoney() {

        String selectQuery = "SELECT * FROM " + ConstantLena.Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(ConstantLena.Table_Name,new String[]{ConstantLena.Key_Id,ConstantLena.PersonName,
                ConstantLena.MobileNumber,ConstantLena.EmailID},null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();
                dena.setUdhariTableId(cursor.getInt(cursor.getColumnIndex(ConstantLena.Key_Id)));
                dena.setName(cursor.getString(cursor.getColumnIndex(ConstantLena.PersonName)));
                dena.setNumber(cursor.getString(cursor.getColumnIndex(ConstantLena.MobileNumber)));
                dena.setEmailid(cursor.getString(cursor.getColumnIndex(ConstantLena.EmailID)));



                lenewaladata.add(dena);

            }while(cursor.moveToNext());
        }
        Log.v("DHANRAJ hhhh","check14");
        return lenewaladata;
    }


    public ArrayList<DataOfDebt> getDataOwed(Integer ID)
    {
        String selectQuery = "SELECT * FROM " + ConstantsDena.Debt_Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.query(ConstantLena.owed_Table_Name,new String[]{ConstantLena.Owed_Key_id,
                        ConstantLena.Owed_money,ConstantLena.Time,ConstantLena.parent_id,ConstantLena.radio_value,ConstantLena.ReasonOFOwingMoney},
                ConstantLena.parent_id + " = "+ ID + "",null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();

                dena.setDebt_keyid(cursor.getInt(cursor.getColumnIndex(ConstantLena.Owed_Key_id)));
                dena.setRadiostringvalue(cursor.getString(cursor.getColumnIndex(ConstantLena.radio_value)));
                dena.setParentid(cursor.getInt(cursor.getColumnIndex(ConstantLena.parent_id)));
                dena.setDebtamount(cursor.getString(cursor.getColumnIndex(ConstantLena.Owed_money)));
                dena.setReasonofdebt(cursor.getString(cursor.getColumnIndex(ConstantLena.ReasonOFOwingMoney)));
                java.text.DateFormat dateFormat =java.text.DateFormat.getDateInstance();
                String dataData = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(ConstantLena.Time))).getTime());



                dena.setTime(dataData);

                lenewaladata1.add(dena);

            }while(cursor.moveToNext());
        }

        return  lenewaladata1;
    }

    public ArrayList<DataOfDebt> getlistofmembers(Integer ID)
    {

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.query(ConstantsEvent.List_of_members_table,new String[]{ConstantsEvent.List_Key_id,
                        ConstantsEvent.nameofmembers,ConstantsEvent.EmailID,ConstantsEvent.parent_id},
                ConstantsEvent.parent_id + " = "+ ID + "",null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();

                dena.setDebt_keyid(cursor.getInt(cursor.getColumnIndex(ConstantsEvent.List_Key_id)));
                dena.setParentid(cursor.getInt(cursor.getColumnIndex(ConstantsEvent.parent_id)));
                dena.setEmailid(cursor.getString(cursor.getColumnIndex(ConstantsEvent.EmailID)));
                dena.setName(cursor.getString(cursor.getColumnIndex(ConstantsEvent.nameofmembers)));

                listofMEMBERS.add(dena);

            }while(cursor.moveToNext());
        }

        return  listofMEMBERS;
    }

    public ArrayList<DataOfDebt> getlistofexpend(Integer ID)
    {

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.query(ConstantsEvent.List_of_expend_table,new String[]{ConstantsEvent.List_Key_id,
                        ConstantsEvent.nameofmembers,ConstantsEvent.Time,ConstantsEvent.parent_id,ConstantsEvent.reasonofexpend,ConstantsEvent.amountofexpend},
                ConstantsEvent.parent_id + " = "+ ID + "",null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();

                dena.setDebt_keyid(cursor.getInt(cursor.getColumnIndex(ConstantsEvent.List_Key_id)));
                dena.setParentid(cursor.getInt(cursor.getColumnIndex(ConstantsEvent.parent_id)));
                dena.setReasonofdebt(cursor.getString(cursor.getColumnIndex(ConstantsEvent.reasonofexpend)));
                dena.setName(cursor.getString(cursor.getColumnIndex(ConstantsEvent.nameofmembers)));
                dena.setDebtamount(cursor.getString(cursor.getColumnIndex(ConstantsEvent.amountofexpend)));
                java.text.DateFormat dateFormat =java.text.DateFormat.getDateInstance();
                String dataDataofevent = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(ConstantsEvent.Time))).getTime());
                dena.setTime(dataDataofevent);

                listofEXPEND.add(dena);

            }while(cursor.moveToNext());
        }

        return  listofEXPEND;
    }



    public ArrayList<DataOfDebt> getsumofowedmoney(Integer ID) {


        String selectQuery = "SELECT * FROM " + ConstantsDena.Debt_Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();



        Cursor cursor = db.query(ConstantLena.owed_Table_Name,new String[]{ConstantLena.Owed_money,ConstantLena.radio_value,ConstantLena.parent_id},
                ConstantLena.parent_id + " = "+ ID,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                DataOfDebt dena = new DataOfDebt();

                dena.setSumofdebt(cursor.getInt(cursor.getColumnIndex(ConstantLena.Owed_money)));
                dena.setRadiostringvalue(cursor.getString(cursor.getColumnIndex(ConstantLena.radio_value)));

                sumofowedmoney.add(dena);


            }while(cursor.moveToNext());
        }

        return  sumofowedmoney;
    }




}
