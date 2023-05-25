package com.example.figmadesign;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.figmadesign.Adapter.MainFoodAdapter;

public class FoodDetails extends Fragment implements MainFoodAdapter.Example1, MainFoodAdapter.Example2{
    private MainFoodAdapter.Example1 example1;
    private int count,
                img;
    private float priceOfFood;
    private String nameOfFood;
    private ImageView detailsImage,
                      plus,
                      minus;
    private TextView name,
                     price,
                     countOfFood;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_details, container, false);
        count = 0;
        name = view.findViewById(R.id.details_name);
        price = view.findViewById(R.id.details_price);
        detailsImage = view.findViewById(R.id.details_image);
        updateData();


        name.setText(nameOfFood);
        price.setText("$ " +(priceOfFood));
        detailsImage.setImageResource(img);

        countOfFood = view.findViewById(R.id.countOfFood);
        plus = view.findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 10) {
                    count++;
                    countOfFood.setText(String.valueOf(count));
                    example1.passDataToMainActivity(count, priceOfFood);
                }
                if (count == 10) {
                    Toast toast = Toast.makeText(getContext(), "You can order only less than 10 items", Toast.LENGTH_SHORT);
                    toast.show();
                }
                countOfFood.setText(String.valueOf(count));
            }
        });
        minus = view.findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    example1.passDataToMainActivity(-count, -priceOfFood);
                    countOfFood.setText(String.valueOf(count));
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        example1 = (MainFoodAdapter.Example1) context;
    }

    @Override
    public void passDataToMainActivity(int counter, float price) {
    }

    public void updateData() {
        Bundle bundle = getArguments();
        if (bundle!= null) {
            nameOfFood = bundle.getString("name");
            priceOfFood = bundle.getFloat("price");
            img = bundle.getInt("image");
        }
    }
    @Override
    public void createFragment(String name, float price, int imageUrl) {
    }
}