package com.example.figmadesign;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.figmadesign.Adapter.MainFoodAdapter;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements MainFoodAdapter.Example1, MainFoodAdapter.Example2, NavigationView.OnNavigationItemSelectedListener {
    private MainFragment mainFragment = new MainFragment();
    private LovelyFoodFragment lovelyFoodFragment = new LovelyFoodFragment();
    private BuyFragment buyFragment = new BuyFragment();
    private DoNotKnowFragment doNotKnowFragment = new DoNotKnowFragment();
    private TextView countView,
                     priceView;
    private ImageView lovelyFragmentButton,
                      mainFragmentButton,
                      doNotKnowFragmentButton,
                      buyFragmentButton,
                      deleteButton,
                      spinner,
                      menuBar;

    private DrawerLayout drawerLayout;

    private int localCount;
    private SearchView searchItem;
    private float money;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countView = findViewById(R.id.countView);
        priceView = findViewById(R.id.priceView);
        mainFragmentButton = findViewById(R.id.mainPageIcon);
        drawerLayout = findViewById(R.id.navigation_container);
        menuBar = findViewById(R.id.menuBar);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        menuBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        CreateFragment(mainFragment);


        mainFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(mainFragment);

            }
        });
        lovelyFragmentButton = findViewById(R.id.likedItemsPageIcon);
        lovelyFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(lovelyFoodFragment);

            }
        });
        doNotKnowFragmentButton = findViewById(R.id.doNotKnowPageIcon);
        doNotKnowFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(doNotKnowFragment);

            }
        });
        buyFragmentButton = findViewById(R.id.buyFragmentIcon);
        buyFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(buyFragment);

            }
        });
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countView.setText(0 + " items");
                priceView.setText("");
                money = 0;
                localCount = 0;
            }
        });
        spinner = findViewById(R.id.spinner);
        spinner.setOnClickListener(v -> showPopup(v));

    }


    private void CreateFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_layout, fragment);
        ft.commit();
    }

    @Override
    public void passDataToMainActivity(int counter, float price) {
        money += price;
        if (counter > 0) {
            countView.setText(++localCount + " items");
        } else if (counter <= 0) {
            countView.setText(--localCount + " items");
        }
        priceView.setText("$ " + money);
    }

    @Override
    public void createFragment(String name, float price, int imageUrl) {
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:{
                CreateFragment(mainFragment);
                break;
            }
            case R.id.nav_lovely: {
                CreateFragment(lovelyFoodFragment);
                break;
            }
                case R.id.nav_doNotKnow: {
                    CreateFragment(doNotKnowFragment);
                    break;
                }
            case R.id.nav_ordered: {
                        CreateFragment(buyFragment);
                        break;
                    }
            case R.id.nav_logOut: {
                CreateFragment(mainFragment);
                countView.setText(0 + " items");
                priceView.setText("");
                money = 0;
                localCount = 0;
                break;
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}




