package dvr.com.voicemail.Fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import dvr.com.voicemail.Adapters.CallDetailAdapter;
import dvr.com.voicemail.DataBase.DBHelper;
import dvr.com.voicemail.Model.CallDetailModel;
import dvr.com.voicemail.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallListFragment extends Fragment /*implements View.OnClickListener*/{

  public static  RecyclerView mRecyclerView;
    DBHelper dbHelper;
    ArrayList<CallDetailModel> callDetailModels= new ArrayList<>();
    LinearLayoutManager mLayoutManager;
    CallDetailAdapter callDetailAdapter;



    RelativeLayout rlAll;
    RelativeLayout   rlInComing;
    RelativeLayout   rlOutGoing;
    RelativeLayout   rlMissed;

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    public CallListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_call_list, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.calllist);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


      //  getCalldetailsNow();
        allCall();

       // setOnClickListeners();



        return  view ;
    }


  /*  private void setOnClickListeners() {


        rlAll.setOnClickListener(this);
        rlInComing.setOnClickListener(this);
        rlMissed.setOnClickListener(this);
        rlOutGoing.setOnClickListener(this);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
    }*/
/*
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.relativeLayout:
                tv1.setTextColor(getResources().getColor(R.color.black));
                rlAll.setBackgroundResource(R.drawable.roundwhite);
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
                rlInComing.setBackgroundResource(R.drawable.roundwhite);

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
                rlOutGoing.setBackgroundResource(R.drawable.roundwhite);


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
                rlMissed.setBackgroundResource(R.drawable.roundwhite);



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
    }*/

    private void missedCall() {

    }

    private void outGoingCall() {
    }

    private void inComingCall() {
    }

    private void allCall() {

        dbHelper=DBHelper.getObject(getActivity());

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
                callDetailModels.add(callDetailModel);
            }while(c.moveToNext());
        }

        callDetailAdapter = new CallDetailAdapter(callDetailModels);
        mRecyclerView.setAdapter(callDetailAdapter);







    }


}
