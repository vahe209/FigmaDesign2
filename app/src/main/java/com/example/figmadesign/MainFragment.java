package com.example.figmadesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.figmadesign.Adapter.MainFoodAdapter;
import com.example.figmadesign.model.MainFood;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainFoodAdapter.Example2 {

    private List<MainFood> mainFoods;
    private RecyclerView.LayoutManager layoutManager;
    private Bundle bundle = new Bundle();
    private ImageView menubar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFoods = new ArrayList<>();
        mainFoods.add(new MainFood("Pizza", "15.00", R.drawable.card1));
        mainFoods.add(new MainFood("Vegetable Salad", "20.00", R.drawable.card2));
        mainFoods.add(new MainFood("Hamburger", "5.00", R.drawable.card3));
        mainFoods.add(new MainFood("Spaghetti Gabonese", "10.00", R.drawable.card4));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        initRecView(v);
     return v;
    }

    private void initRecView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        MainFoodAdapter adapter = new MainFoodAdapter(MainFragment.this, mainFoods);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void createFragment(String name, float price, int imageUrl) {
        FoodDetails foodDetails = new FoodDetails();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_layout, foodDetails);
        ft.commit();
            bundle.putString("name", name);
            bundle.putFloat("price", price);
            bundle.putInt("image", imageUrl);
            foodDetails.setArguments(bundle);
    }
}




