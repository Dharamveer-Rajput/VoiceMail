package dvr.com.voicemail.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dvr.com.voicemail.Model.CallDetailModel;
import dvr.com.voicemail.R;

public class CallDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<CallDetailModel> mDataset;
    Context context;
    ArrayList<String> dateArrayList;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;
        TextView time;
        ImageView  imgpic;
        ImageView  threedot;
        TextView tvDate;

        public DataObjectHolder(View itemView) {
            super(itemView);


            name = (TextView)itemView.findViewById(R.id.cname);
            number= (TextView)itemView.findViewById(R.id.cnumber);
            time= (TextView)itemView.findViewById(R.id.ctime);
            imgpic= (ImageView)itemView.findViewById(R.id.cpic);
            threedot= (ImageView)itemView.findViewById(R.id.threedot);
            tvDate= (TextView) itemView.findViewById(R.id.tvdate);


        }
    }
    public static class DataObjectHolderChild extends RecyclerView.ViewHolder {
        TextView tvDate;

        public DataObjectHolderChild(View itemView) {
            super(itemView);

            tvDate= (TextView) itemView.findViewById(R.id.tvdate);


        }
    }


    public CallDetailAdapter(ArrayList<CallDetailModel> myDataset) {
        mDataset = myDataset;
        dateArrayList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        context = parent.getContext();
/*

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.call_list_row, parent, false);

        context = parent.getContext();

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
*/
        if (viewType == TYPE_ITEM) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_list_row, parent, false);
            return new DataObjectHolder(layoutView);
            //     } else if (viewType == TYPE_ITEM) {
            //       View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_list_row, parent, false);
            //        return new DataObjectHolder(layoutView);
        }

        throw new RuntimeException("No match for " + viewType + ".");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if(holder instanceof DataObjectHolder)
        {

            ((DataObjectHolder) holder).name.setText(mDataset.get(position).getName());
            ((DataObjectHolder) holder).number.setText(mDataset.get(position).getPhNumber());
            ((DataObjectHolder) holder).time.setText(mDataset.get(position).getTime());
            ((DataObjectHolder) holder).tvDate.setText(mDataset.get(position).getDate());

            if (position>0) {
                if(((DataObjectHolder) holder).tvDate.getText().toString().equalsIgnoreCase(mDataset.get(--position).getDate()))
                {
                    ((DataObjectHolder) holder).tvDate.setVisibility(View.GONE);

                }
                else
                {
                    ( (DataObjectHolder) holder).tvDate.setVisibility(View.VISIBLE);

                }
            }
            else
            {
                ( (DataObjectHolder) holder).tvDate.setVisibility(View.VISIBLE);
                ((DataObjectHolder) holder).tvDate.setText(matchwithtodaysDateorTommorrow(mDataset.get(position).getDate()));
            }



        }

        else if(holder instanceof DataObjectHolderChild)
        {

            //   ((DataObjectHolderChild) holder).tvDate.setText(mDataset.get(position).getDate());
        }
    }

    private boolean isPositionHeader(int position) {



        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        // if (isPositionHeader(position))
        //        return TYPE_HEADER;
        return TYPE_ITEM;


       /*// if(position ==0)
        return TYPE_HEADER;

        */






    }

   /* @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {


        holder.name.setText(mDataset.get(position).getName());
        holder.number.setText(mDataset.get(position).getPhNumber());
        holder.time.setText(mDataset.get(position).getTime());

        //matchwithtodaysDateorTommorrow(mDataset.get(position).getDate());














    }*/

    public void addItem(CallDetailModel dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public String matchwithtodaysDateorTommorrow(String date)
    {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());

        if(formattedDate.equalsIgnoreCase(date))
            return "Today";





        return date;
    }

}