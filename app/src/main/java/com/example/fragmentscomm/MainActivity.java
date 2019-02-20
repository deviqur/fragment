package com.example.fragmentscomm;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{
    private Button mButton;
    private  boolean isFragmentDisplayed=false;
    private int mRadioButtonChoice=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=findViewById(R.id.open_button);
    }

    public void openClick(View view) {
        if(!isFragmentDisplayed){
            displayFragment();
        }else {
            closeFragment();
        }

    }
    public void displayFragment(){
        SimpleFragment simpleFragment= SimpleFragment.newInstance(mRadioButtonChoice);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit();

        mButton.setText(R.string.close);
        isFragmentDisplayed=true;

    }

    public void closeFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment)
                fragmentManager.findFragmentById(R.id.fragment_container);

        if(simpleFragment != null){
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        mButton.setText(R.string.open);
        isFragmentDisplayed=false;
    }


    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
        Toast.makeText(this,"CHOICE"+choice,Toast.LENGTH_LONG).show();
    }
}
