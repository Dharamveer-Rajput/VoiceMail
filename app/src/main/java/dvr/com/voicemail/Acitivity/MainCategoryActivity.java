package dvr.com.voicemail.Acitivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import dvr.com.voicemail.Adapters.CallDetailAdapter;
import dvr.com.voicemail.DataBase.DBHelper;
import dvr.com.voicemail.Fragment.CallListFragment;
import dvr.com.voicemail.Model.CallDetailModel;
import dvr.com.voicemail.R;

import static dvr.com.voicemail.Fragment.CallListFragment.mRecyclerView;

public class MainCategoryActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    @BindView(R.id.relativeLayout)  RelativeLayout   rlAll;
    @BindView(R.id.relativeLayout2) RelativeLayout   rlInComing;
    @BindView(R.id.relativeLayout3) RelativeLayout   rlOutGoing;
    @BindView(R.id.relativeLayout4) RelativeLayout   rlMissed;
    @BindView(R.id.tv1) TextView tv1;
    @BindView(R.id.tv2)  TextView tv2;
    @BindView(R.id.tv3) TextView tv3;
    @BindView(R.id.tv4) TextView tv4;
    ArrayList<CallDetailModel> allCallList= new ArrayList<>();
    ArrayList<CallDetailModel> missCallList= new ArrayList<>();
    ArrayList<CallDetailModel> incomingCallList= new ArrayList<>();
    ArrayList<CallDetailModel> outGoingCallList= new ArrayList<>();
    CallDetailAdapter callDetailAdapter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);
               // getCalldetailsNow();

        ButterKnife.bind(this);
        setOnClickListeners();
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainCategoryActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainCategoryActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.READ_CALL_LOG},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        else
        {
            Fragment fragment = new CallListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.container,fragment).commit();



        }

       //rlAll.performClick();
    }

    private void setOnClickListeners() {


        rlAll.setOnClickListener(this);
        rlInComing.setOnClickListener(this);
        rlMissed.setOnClickListener(this);
        rlOutGoing.setOnClickListener(this);

        /*tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);*/
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Fragment fragment = new CallListFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().add(R.id.container,fragment).commit();



                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.relativeLayout:
                tv1.setTextColor(getResources().getColor(R.color.black));
                rlAll.setBackgroundResource(R.drawable.round_white);
                ///////////////////////////////////////////////////////////
                tv2.setTextColor(getResources().getColor(R.color.white));
                rlInComing.setBackgroundResource(R.drawable.roundblack);
                tv3.setTextColor(getResources().getColor(R.color.white));
                rlOutGoing.setBackgroundResource(R.drawable.roundblack);
                tv4.setTextColor(getResources().getColor(R.color.white));
                rlMissed.setBackgroundResource(R.drawable.roundblack);
                allCall();



                break;
            case R.id.relativeLayout2:
                tv1.setTextColor(getResources().getColor(R.color.white));
                rlAll.setBackgroundResource(R.drawable.roundblack);

                tv2.setTextColor(getResources().getColor(R.color.black));
                rlInComing.setBackgroundResource(R.drawable.round_white);

                tv3.setTextColor(getResources().getColor(R.color.white));
                rlOutGoing.setBackgroundResource(R.drawable.roundblack);
                tv4.setTextColor(getResources().getColor(R.color.white));
                rlMissed.setBackgroundResource(R.drawable.roundblack);





                inComingCall();
                break;
            case R.id.relativeLayout3:
                tv1.setTextColor(getResources().getColor(R.color.white));
                rlAll.setBackgroundResource(R.drawable.roundblack);

                tv2.setTextColor(getResources().getColor(R.color.white));
                rlInComing.setBackgroundResource(R.drawable.roundblack);

                tv3.setTextColor(getResources().getColor(R.color.black));
                rlOutGoing.setBackgroundResource(R.drawable.round_white);


                tv4.setTextColor(getResources().getColor(R.color.white));
                rlMissed.setBackgroundResource(R.drawable.roundblack);

                outGoingCall();

                break;
            case R.id.relativeLayout4:
                tv1.setTextColor(getResources().getColor(R.color.white));
                rlAll.setBackgroundResource(R.drawable.roundblack);

                tv2.setTextColor(getResources().getColor(R.color.white));
                rlInComing.setBackgroundResource(R.drawable.roundblack);

                tv3.setTextColor(getResources().getColor(R.color.white));
                rlOutGoing.setBackgroundResource(R.drawable.roundblack);


                tv4.setTextColor(getResources().getColor(R.color.black));
                rlMissed.setBackgroundResource(R.drawable.round_white);

                missedCall();

                break;

            case R.id.tv1:


                break;
            case R.id.tv2:



                break;
            case R.id.tv3:


                break;
            case R.id.tv4:


                break;


        }
    }

    private void missedCall() {

        if(missCallList.size()>0)
        {
            missCallList.clear();
        }

        dbHelper= DBHelper.getObject(getApplicationContext());

        Cursor c = null;
        try {
            c = dbHelper.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                CallDetailModel callDetailModel = new CallDetailModel();
                String number=c.getString(0);
                String date=c.getString(1);
                String time=c.getString(2);
                String duration=c.getString(3);
                String type=c.getString(4);
                String name=c.getString(5);

                callDetailModel.setPhNumber(number);
                callDetailModel.setType(type);
                callDetailModel.setTime(time);
                callDetailModel.setName(name);
                callDetailModel.setDate(date);


                if(type.equals("MISSED"))
                    missCallList.add(callDetailModel);
            }while(c.moveToNext());
        }

        callDetailAdapter = new CallDetailAdapter(missCallList);
        mRecyclerView.setAdapter(callDetailAdapter);

        missCallList.clear();




    }

    private void outGoingCall() {
        if(outGoingCallList.size()>0)
        {
            outGoingCallList.clear();
        }

        dbHelper= DBHelper.getObject(getApplicationContext());

        Cursor c = null;
        try {
            c = dbHelper.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                CallDetailModel callDetailModel = new CallDetailModel();
                String number=c.getString(0);
                String date=c.getString(1);
                String time=c.getString(2);
                String duration=c.getString(3);
                String type=c.getString(4);
                String name=c.getString(5);

                callDetailModel.setPhNumber(number);
                callDetailModel.setType(type);
                callDetailModel.setTime(time);
                callDetailModel.setName(name);
                callDetailModel.setDate(date);
                if(type.equals("OUTGOING"))
                    outGoingCallList.add(callDetailModel);
            }while(c.moveToNext());
        }

        callDetailAdapter = new CallDetailAdapter(outGoingCallList);
        mRecyclerView.setAdapter(callDetailAdapter);




    }

    private void inComingCall() {
        if(incomingCallList.size()>0)
        {
            incomingCallList.clear();
        }

        dbHelper= DBHelper.getObject(getApplicationContext());

        Cursor c = null;
        try {
            c = dbHelper.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                CallDetailModel callDetailModel = new CallDetailModel();
                String number=c.getString(0);
                String date=c.getString(1);
                String time=c.getString(2);
                String duration=c.getString(3);
                String type=c.getString(4);
                String name=c.getString(5);

                callDetailModel.setPhNumber(number);
                callDetailModel.setType(type);
                callDetailModel.setTime(time);
                callDetailModel.setName(name);
                callDetailModel.setDate(date);
                if(type.equals("INCOMING"))
                    incomingCallList.add(callDetailModel);
            }while(c.moveToNext());
        }

        callDetailAdapter = new CallDetailAdapter(incomingCallList);
        mRecyclerView.setAdapter(callDetailAdapter);




    }

    private void allCall() {



        if(allCallList.size()>0)
        {
            allCallList.clear();
        }

        dbHelper= DBHelper.getObject(getApplicationContext());

        Cursor c = null;
        try {
            c = dbHelper.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                CallDetailModel callDetailModel = new CallDetailModel();
                String number=c.getString(0);
                String date=c.getString(1);
                String time=c.getString(2);
                String duration=c.getString(3);
                String type=c.getString(4);
                String name=c.getString(5);

                callDetailModel.setPhNumber(number);
                callDetailModel.setType(type);
                callDetailModel.setTime(time);
                callDetailModel.setName(name);
                callDetailModel.setDate(date);
                allCallList.add(callDetailModel);
            }while(c.moveToNext());
        }

        callDetailAdapter = new CallDetailAdapter(allCallList);
        mRecyclerView.setAdapter(callDetailAdapter);






    }




    private void getCalldetailsNow() {
        // TODO Auto-generated method stub

        //  Cursor managedCursor=getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, android.provider.CallLog.Calls.DATE + " DESC");
        Cursor managedCursor=getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, android.provider.CallLog.Calls.DATE + " DESC");

        int number = managedCursor.getColumnIndex( CallLog.Calls.NUMBER );
        int name = managedCursor.getColumnIndex( CallLog.Calls.CACHED_NAME );

        int duration1 = managedCursor.getColumnIndex( CallLog.Calls.DURATION);
        int type1=managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date1=managedCursor.getColumnIndex(CallLog.Calls.DATE);
        if(managedCursor.getCount()>0)
        {
            managedCursor.moveToFirst();
            do{
                String phNumber = managedCursor.getString(number);
                String cName = managedCursor.getString(name);
                String callDuration = managedCursor.getString(duration1);
                String type=managedCursor.getString(type1);
                String date=managedCursor.getString(date1);

                String dir = null;
                int dircode = Integer.parseInt(type);
                switch (dircode)
                {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                    default:
                        dir = "MISSED";
                        break;
                }

                SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf_time = new SimpleDateFormat("h:mm a");
                // SimpleDateFormat sdf_dur = new SimpleDateFormat("KK:mm:ss");

                String dateString = sdf_date.format(new Date(Long.parseLong(date)));
                String timeString = sdf_time.format(new Date(Long.parseLong(date)));
                //  String duration_new=sdf_dur.format(new Date(Long.parseLong(callDuration)));

                DBHelper db=DBHelper.getObject(getApplicationContext());
                db.insertdata(phNumber, dateString, timeString, callDuration, dir,cName);


            }while (managedCursor.moveToNext());

        }


        /*if( managedCursor.moveToFirst() == true ) {

        }*/

        managedCursor.close();
    }


}
