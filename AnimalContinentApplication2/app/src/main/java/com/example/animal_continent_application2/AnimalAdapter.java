package com.example.animal_continent_application2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

    private AnimalDatabaseHelper dbHelper;


    public AnimalAdapter(Context context) {
        this.context = context;
        dbHelper = new AnimalDatabaseHelper(context);
        this.animalList = dbHelper.fetchAnimals();
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

    public ArrayList<Animal> getAnimalList()
    {
        return this.animalList;
    }


    private static OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
    }

    public boolean removeItem(int position) {
        Animal deletedAnimal = animalList.remove(position);
        notifyItemRemoved(position);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(deletedAnimal.getId())};
        int deletedRows = db.delete("animal", selection, selectionArgs);
        return deletedRows > 0;
    }

    public boolean addItem(Animal animal) {
        animalList.add(animal);
        notifyItemInserted(animalList.size() - 1);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", animal.getName());
        values.put("continent", animal.getContinent());

        long newRowId = db.insert("animal", null, values);
        notifyItemInserted(animalList.size() - 1);
        return newRowId != -1;
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

