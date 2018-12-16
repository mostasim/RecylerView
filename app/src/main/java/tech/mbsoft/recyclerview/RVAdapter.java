package tech.mbsoft.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mbSoft on 14-Jan-18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVHolder> {

    //Adapter manages list item.
    List<Item> itemList; //this is the dataset of recycler view
    Context mContext;


    public RVAdapter(List<Item> itemList, Context mContext) {
        this.itemList = itemList;
        this.mContext = mContext;
    }

    //set the view  of item layout for each item here
    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new RVHolder(view);
    }

    //set each to the view from dataset
    @Override
    public void onBindViewHolder(RVHolder holder, int position) {
        Item item=itemList.get(position);//this is item model for each item
        holder.tvTitle.setText(item.getTitle());

    }
    //count the total item from list
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RVHolder extends RecyclerView.ViewHolder{
        //ViewHolder class is holder class that hold the view for each item
        //Component of each item
        TextView tvTitle;
        EditText etTitle;
        Button btShow,btAdd;

        public RVHolder(View itemView) {
            super(itemView);
            //find the view and attach to the component
            tvTitle=itemView.findViewById(R.id.tvTitle);
            etTitle=itemView.findViewById(R.id.etTitle);
            btAdd=itemView.findViewById(R.id.btAdd);
            btShow=itemView.findViewById(R.id.btShow);
            //if each component need to perform any thing (ex: onClick etc)
            btShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvTitle.setText(etTitle.getText());
                    Util.expand(tvTitle,1500,200);
                    Toast.makeText(mContext,"ADD"+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                }
            });
            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item=new Item("New Item"+(getLayoutPosition()+1));
                    itemList.add(item);
                    notifyDataSetChanged();
                }
            });

        }
    }
}
