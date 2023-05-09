package com.example.animal_continent_application2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animal_continent_application2.Animal;
import com.example.animal_continent_application2.R;

import java.text.BreakIterator;
import java.util.ArrayList;


public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private Context context;
    public static ArrayList<Animal> animalList;
    private LayoutInflater inflater;



    public AnimalAdapter(Context context, ArrayList<Animal> animalList) {
        this.context = context;
        this.animalList = animalList;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data for the current position
        Animal animal = animalList.get(position);

        // Set data to views
        holder.nameTextView.setText(animal.getName());
        holder.continentTextView.setText(animal.getContinent());
    }



    @Override
    public int getItemCount() {
        return animalList.size();
    }


    private static OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
    }

    public void removeItem(int position) {
        animalList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(Animal animal) {
        animalList.add(animal);
        notifyItemInserted(animalList.size() - 1);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView continentTextView;
        androidx.appcompat.widget.AppCompatButton deleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.animal_name);
            continentTextView = itemView.findViewById(R.id.continent_name);
            deleteButton = itemView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onDeleteClickListener.onDeleteClick(position);
                        }
                    }
                }
            });
        }

    }
}

