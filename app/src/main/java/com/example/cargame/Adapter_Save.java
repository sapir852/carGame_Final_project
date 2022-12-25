package com.example.cargame;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Adapter_Save extends RecyclerView.Adapter<Adapter_Save.SaveViewHolder> {

    private fragment_list fragment_list;
    private ArrayList<Save> saves;


    public Adapter_Save(fragment_list fragment_list, ArrayList<Save> saves) {
        this.fragment_list = fragment_list;
        this.saves = saves;
    }

    @Override
    public int getItemCount() {
        return saves == null ? 0 : saves.size();
    }


    @Override
    public SaveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false);
        SaveViewHolder mySaveViewHolder = new SaveViewHolder(view);
        return mySaveViewHolder;
    }



    @Override
    public void onBindViewHolder(final SaveViewHolder holder, final int position) {
        Log.d("pttt", "Pos= " + position);
        Save save = saves.get(position);

        holder.list_LBL_name.setText("" + save.getName());
        holder.list_LBL_score.setText(""+save.getScore());

    }


    class SaveViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView list_LBL_name;
        private MaterialTextView list_LBL_score;
        private MaterialTextView list_IMG_stare;

        public SaveViewHolder(View itemView) {
            super(itemView);
            list_LBL_name = itemView.findViewById(R.id.list_LBL_name);
            list_LBL_score = itemView.findViewById(R.id.list_LBL_score);
            list_IMG_stare = itemView.findViewById(R.id.list_IMG_stare);


        }

    }

}
