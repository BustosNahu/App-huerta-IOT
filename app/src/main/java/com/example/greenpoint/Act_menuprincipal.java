package com.example.greenpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.greenpoint.databinding.ActivityActMenuprincipalBinding;
import com.example.greenpoint.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Act_menuprincipal extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    ActivityActMenuprincipalBinding binding;

    NavigationView nav;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private  FirebaseAuth auth;
    private ActionBarDrawerToggle toggle;
    Button bt_desing, bt_smart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_menuprincipal);


        //Declaraciones del botton nav view
        binding = ActivityActMenuprincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        bt_desing = findViewById(R.id.bt_desing);
        bt_smart = findViewById(R.id.bt_smart);
        drawerLayout = findViewById(R.id.drawer_layout);
        nav = (NavigationView) findViewById(R.id.navview);
        setToolBar();
        auth = FirebaseAuth.getInstance();

        //TITULO DEL ACTION BAR
        this.setTitle("");



        bt_desing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Act_menuprincipal.this, Desingactivity.class);
                startActivity(i);
            }
        });

        bt_smart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Act_menuprincipal.this, Smartactivity.class);
                startActivity(i);
            }
        });

        //FUNCION PARA EL BOTTON NAVEGATION VIEW
        binding.bottomNavView.setOnItemSelectedListener(item ->{

            switch (item.getItemId()){

                case R.id.bt_add:
                    replaceFFragment(new Add_menu_principal());
                    break;

                case R.id.bt_home:

                    break;
            }

            return true;


        });


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                menuItem.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (id) {
                    case R.id.nav_log_out:
                        auth.signOut();
                        Toast.makeText(getApplicationContext(), "Log Out Succesfully", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        gologing();
                        break;

                    case R.id.information:
                        replaceFFragment(new Information_fragment());
                        break;

                    case R.id.nav_account:
                        replaceFFragment(new Account_fragment());
                        break;

                    case R.id.nav_showing_calendar:
                        replaceFFragment(new Showingcalendar_fragment());
                        break;
                }

                return true;
            }
        });
    }

    //metodo para el bottom navegation bar
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setBackground(null);

    }


    //metodo para action bar desplegable
    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gologing() {
        Intent i = new Intent(this, MainActivity.class);
        //BANDERAS PARA QUE NO SE CREEN ACTIVIDADES INNECESARIAS
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void replaceFFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.drawer_layout,fragment);
        fragmentTransaction.commit();

    }
}
