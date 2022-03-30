package com.example.etudiant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_consumer_etu.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private ArrayList<Etudiant> etudiants;
    public Adapter(Context context , ArrayList<Etudiant> list)
    {
        etudiants = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView prenom;
        TextView age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            prenom = itemView.findViewById(R.id.prenom);
            age = itemView.findViewById(R.id.age);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.itemView.setTag(etudiants.get(position));
        holder.name.setText(etudiants.get(position).getNom());
        holder.prenom.setText(etudiants.get(position).getPrenom());
        holder.age.setText(etudiants.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return etudiants.size();
    }
}
