package dvr.com.voicemail.Acitivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import dvr.com.voicemail.DataBase.DBHelper;
import dvr.com.voicemail.R;


public class MainActivity extends AppCompatActivity {

    DBHelper db;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    int permissionCheck = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionCheck = checkSelfPermission(Manifest.permission.READ_CALL_LOG);
        }

        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
        {
            db=DBHelper.getObject(getApplicationContext());

            Cursor c=db.getData();

            if(c.getCount()>0)
            {
                c.moveToLast();
               // do{
                    String number=c.getString(0);
                    String date=c.getString(1);
                    String time=c.getString(2);
                    String duration=c.getString(3);
                    String type=c.getString(4);

                //}while(c.moveToNext());
            }
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==MY_PERMISSIONS_REQUEST_READ_CONTACTS)
        {
            db=DBHelper.getObject(getApplicationContext());
            Cursor c=db.getData();
            if(c.getCount()>0)
            {
                c.moveToFirst();
                do
                {   String number=c.getString(0);
                    String date=c.getString(1);
                    String time=c.getString(2);
                    String duration=c.getString(3);
                    String type=c.getString(4);
                }while(c.moveToNext());
            }
        }
    }

}