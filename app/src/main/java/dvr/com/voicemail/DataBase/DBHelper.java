package dvr.com.voicemail.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

  private  static   DBHelper  dbHelper = null;


    private DBHelper(Context context, String name, CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    public static  DBHelper getObject(Context context)
    {

        if(dbHelper == null)
        {
            dbHelper = new DBHelper(context, "ZnSoftech.db", null, 2);

        }
        else
            return dbHelper;

        return dbHelper;

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table if not exists call_history(number varchar PRIMARY KEY , date varchar , time varchar, duration varchar, type varchar,name varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS call_history");
        onCreate(db);
    }

    public boolean insertdata(String number, String date, String time,String duration, String type,String name)
    {
        SQLiteDatabase sdb=this.getWritableDatabase();
       // sdb.execSQL("insert into call_history values('"+number+"','"+date+"','"+time+"','"+duration+"','"+type+"')");
        try {
            sdb.execSQL("insert into call_history values('"+number+"','"+date+"','"+time+"','"+duration+"','"+type+ "','"+name+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Cursor getData()
    {
        SQLiteDatabase sdb=this.getReadableDatabase();


        return sdb.rawQuery("select * from call_history", null);
    }
    public void deleteTable()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS call_history");
        onCreate(db);
    }

}