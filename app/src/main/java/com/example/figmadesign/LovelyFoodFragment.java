package com.example.figmadesign;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.figmadesign.Adapter.MainFoodAdapter;
import com.example.figmadesign.model.MainFood;

import java.util.ArrayList;
import java.util.List;

public class LovelyFoodFragment extends Fragment implements MainFoodAdapter.Example2 {
    private List<MainFood> mainFoods;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_lovely_food, container, false);
        initRecView(view);
        return view;
    }
    public void initRecView(View v){
        RecyclerView recyclerView = v.findViewById(R.id.lovely_rv2);
        layoutManager = new  LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        MainFoodAdapter adapter = new MainFoodAdapter(LovelyFoodFragment.this, mainFoods);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFoods = new ArrayList<>();
        mainFoods.add(new MainFood("Pizza", "15.00", R.drawable.card1));
        mainFoods.add(new MainFood("Vegetable Salad", "20.00", R.drawable.card2));
        mainFoods.add(new MainFood("Hamburger", "5.00", R.drawable.card3));
        mainFoods.add(new MainFood("Spaghetti Gabonese", "10.00", R.drawable.card4));
    }

    @Override
    public void createFragment(String name, float price, int imageUrl) {
        FoodDetails foodDetails = new FoodDetails();
        if (getActivity()!= null) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_layout, foodDetails);
            ft.commit();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putFloat("price", price);
            bundle.putInt("image", imageUrl);
            foodDetails.setArguments(bundle);
        }
    }

}