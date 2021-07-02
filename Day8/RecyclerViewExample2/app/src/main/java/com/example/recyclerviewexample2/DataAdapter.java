package com.example.recyclerviewexample2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context context;
    private List<DataModel> dataModelList;

    public DataAdapter(Context context, List<DataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @NotNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataAdapter.DataViewHolder holder, int position) {
        DataModel dataModel=dataModelList.get(position);
        holder.setData(dataModel, position);
        holder.setListeners();
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        private TextView title;
        private ImageView imageViewThumb, imageViewDelete, imageViewCopy;

        private int position;
        private DataModel dataModel;

        public DataViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewThumb= (ImageView) itemView.findViewById(R.id.imageViewThumb);
            imageViewCopy=(ImageView)itemView.findViewById(R.id.imageViewCopy);
            imageViewDelete=(ImageView)itemView.findViewById(R.id.imageViewDelete);
        }
        public void setData(DataModel dataModel, int position){
            this.title.setText(dataModel.getImageTitle());
            this.imageViewThumb.setImageResource(dataModel.getImageID());
            this.position=position;
            this.dataModel=dataModel;
        }
        public void setListeners(){
            imageViewThumb.setOnClickListener(DataViewHolder.this);
            imageViewCopy.setOnClickListener(DataViewHolder.this);
            imageViewDelete.setOnClickListener(DataViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageViewDelete:
                    removeItem(position);
                    break;
                case R.id.imageViewCopy:
                    addItem(position, dataModel);
                    break;
                case R.id.imageViewThumb:
                    Toast.makeText(context,this.title.getText().toString(),
                            Toast.LENGTH_SHORT)
                    .show();
                    break;
            }
        }
        public void removeItem(int position){
            dataModelList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,dataModelList.size());
        }
        public void addItem(int position, DataModel dataModel){
            dataModelList.add(position, dataModel);
            notifyItemInserted(position);
            notifyItemRangeChanged(position,dataModelList.size());
        }
    }
}
