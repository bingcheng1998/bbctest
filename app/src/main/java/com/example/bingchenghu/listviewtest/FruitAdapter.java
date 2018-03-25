package com.example.bingchenghu.listviewtest;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;



    static class  ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public  ViewHolder(View view){
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }


    }

    public FruitAdapter(List<Fruit> fruitList){
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);

        //setOnItemClickListener();
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
//                Fruit fruit = mFruitList.get(position);
//                Toast.makeText(v.getContext(),"you clickd view: " + fruit.getName(), Toast.LENGTH_SHORT
//                ).show();
                //addData(position);
                notifyItemInserted(position);
                //notifyDataSetChanged();
                //notifyItemRangeChanged(position,mFruitList.size()-position);
            }
        });

//        holder.fruitView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                //int position = holder.getAdapterPosition();
//                //removeData(position);
//                //DialogSingle();
//                return false;
//            }
//        });
//
//        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                Fruit fruit = mFruitList.get(position);
//                Toast.makeText(v.getContext(),"you clickd view: " + fruit.getName(), Toast.LENGTH_SHORT
//                ).show();
//                //removeData(position);
//            }
//        });

        return holder;
    }
    private OnItemClickListener onItemClickListener;

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){

        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.itemView, pos);
            }
        });
        holder.fruitImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemLongClick(holder.itemView, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount(){
        return  mFruitList.size();
    }

    public interface OnItemClickListener {
        /*点击事件*/
        void onItemClick(View view, int position);
        /*长按事件*/
        void onItemLongClick(View view, int position);
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }



    public void removeData(int position) {
        mFruitList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        //notifyDataSetChanged();
    }

    public void addData() {
//   在list中添加数据，并通知条目加入一条
        Fruit apple=new Fruit("Fruit",R.drawable.fruit_pic);
        mFruitList.add(0, apple);
        //添加动画
        //notifyItemInserted(0);

        notifyDataSetChanged();
    }

    public void moveToRoof(int position){
        notifyItemMoved(position,0);
        //notifyItemRangeChanged( 0,position);
        //notifyDataSetChanged();
    }
//    public void DialogSingle() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder();
//        builder.setTitle("单选框");
//        builder.setIcon(android.R.drawable.ic_dialog_info);
//        builder.setSingleChoiceItems(new String[] { "编辑", "删除" }, 0,
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//                        dialog.dismiss();
//                    }
//                }).setPositiveButton("确定", null).show();
//        builder.show();
//    }

}