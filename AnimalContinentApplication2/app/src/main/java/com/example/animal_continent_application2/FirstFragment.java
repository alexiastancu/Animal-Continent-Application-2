package com.example.animal_continent_application2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment {
    private View fragmentView;
    private RecyclerView recyclerView;
    private AnimalAdapter adapter;
    private ArrayList<Animal> animalList;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_first, container, false);


        // Initialize data
        animalList = new ArrayList<>();
        animalList.add(new Animal(1,"Lion", "Africa"));
        animalList.add(new Animal(2,"Panda", "Asia"));
        animalList.add(new Animal(3,"Bear", "Americas"));
        animalList.add(new Animal(4,"Kangaroo", "Australia"));
        animalList.add(new Animal(5,"Cat", "Europa"));
        animalList.add(new Animal(6,"Koala", "Australia"));
        animalList.add(new Animal(7,"Elephant", "Asia"));
        animalList.add(new Animal(8,"Penguin", "Americas"));
        animalList.add(new Animal(9,"Kangaroo", "Australia"));
        animalList.add(new Animal(10,"Bear", "Americas"));
//        animalList.add(new Animal("Tiger", "Asia"));
//        animalList.add(new Animal("Hippopotamus", "Africa"));
//        animalList.add(new Animal("Koala", "Australia"));
//        animalList.add(new Animal("Giraffe", "Africa"));
//        animalList.add(new Animal("Zebra", "Africa"));
//        animalList.add(new Animal("Panda", "Asia"));
//        animalList.add(new Animal("Kangaroo", "Australia"));
//        animalList.add(new Animal("Lion", "Africa"));
//        animalList.add(new Animal("Bear", "Americas"));
//        animalList.add(new Animal("Penguin", "Americas"));
//        animalList.add(new Animal("Koala", "Australia"));
//        animalList.add(new Animal("Elephant", "Asia"));
//        animalList.add(new Animal("Hippopotamus", "Africa"));
//        animalList.add(new Animal("Tiger", "Asia"));
//        animalList.add(new Animal("Leopard", "Africa"));

// Additional animals
//        animalList.add(new Animal("Gorilla", "Africa"));
//        animalList.add(new Animal("Orangutan", "Asia"));
//        animalList.add(new Animal("Red Panda", "Asia"));
//        animalList.add(new Animal("Camel", "Africa"));
//        animalList.add(new Animal("Llama", "Americas"));
//        animalList.add(new Animal("Moose", "Americas"));
//        animalList.add(new Animal("Hedgehog", "Europa"));
//        animalList.add(new Animal("Sloth", "Americas"));
//        animalList.add(new Animal("Peacock", "Asia"));
//        animalList.add(new Animal("Crocodile", "Australia"));
//        animalList.add(new Animal("Wolf", "Europa"));
//        animalList.add(new Animal("Manta Ray", "Oceans"));
//        animalList.add(new Animal("Flamingo", "Americas"));
//        animalList.add(new Animal("Koala", "Australia"));
//        animalList.add(new Animal("Puma", "Americas"));
//        animalList.add(new Animal("Gazelle", "Africa"));
//        animalList.add(new Animal("Kangaroo", "Australia"));
//        animalList.add(new Animal("Elephant", "Africa"));
//        animalList.add(new Animal("Toucan", "Americas"));
//        animalList.add(new Animal("Lion", "Africa"));
//        animalList.add(new Animal("Grizzly Bear", "Americas"));
//        animalList.add(new Animal("Polar Bear", "Europa"));
//        animalList.add(new Animal("Koala", "Australia"));
//        animalList.add(new Animal("Puma", "Americas"));
//        animalList.add(new Animal("Kangaroo", "Australia"));
//        animalList.add(new Animal("Tiger", "Asia"));

        // Initialize adapter
        adapter = new AnimalAdapter(this.getContext(), animalList);
        adapter.setOnDeleteClickListener(new AnimalAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                adapter.removeItem(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                Snackbar.make(fragmentView, "The selected item was deleted successfully", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                //animalList.remove(position);
            }
        });

        recyclerView = fragmentView.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

        // Find the button by its ID
        androidx.appcompat.widget.AppCompatButton addButton = fragmentView.findViewById(R.id.add_animal_button);

        // Set click listener for the button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click here
                // Get the input values from the fields
                EditText animalNameEditText = fragmentView.findViewById(R.id.inserted_animal_name);
                EditText animalContinentEditText = fragmentView.findViewById(R.id.inserted_animal_continent);

                String animalName = animalNameEditText.getText().toString();
                String continentName = animalContinentEditText.getText().toString();

                // Perform the verifications
                boolean isNameAlreadyExists = false;
                boolean isAnyFieldEmpty = false;
                boolean isValidContinent = false;

                // Verification (a): Check if an animal with the same name already exists
                isNameAlreadyExists = animalList.stream()
                        .anyMatch(animal -> animal.getName().equalsIgnoreCase(animalName));

                // Verification (b): Check if any of the fields are empty or contain only whitespace
                if (animalName.trim().isEmpty() || continentName.trim().isEmpty()) {
                    isAnyFieldEmpty = true;
                }

                // Verification (c): Check if the continent name exists
                ArrayList<String> validContinents = new ArrayList<>(Arrays.asList(
                        "africa", "asia", "europa", "americas", "australia"));
                if (validContinents.contains(continentName.toLowerCase())) {
                    isValidContinent = true;
                }

                if (isNameAlreadyExists) {
                    // Display error message: Animal name already exists, perform data update instead
                    Snackbar.make(view, "Animal name already exists. Perform data update instead.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (isAnyFieldEmpty) {
                    // Display error message: All fields must be filled
                    Snackbar.make(view, "All fields must be filled.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!isValidContinent) {
                    // Display error message: Invalid continent name
                    Snackbar.make(view, "Invalid continent name.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    // Proceed with adding the animal to the database
                    Animal newAnimal = new Animal(1, animalName, continentName);
//                    dbHelper.addAnimal(newAnimal);
                    //animalList.add(newAnimal);
                    adapter.addItem(newAnimal);
                    int insertedPosition = animalList.size() - 1;
                    adapter.notifyItemInserted(insertedPosition);
                    Snackbar.make(view, "Animal added successfully.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        return fragmentView;
    }
}


