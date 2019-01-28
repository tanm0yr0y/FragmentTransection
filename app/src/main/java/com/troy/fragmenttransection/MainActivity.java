package com.troy.fragmenttransection;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    FragmentManager fragmentManager;
    FragmentA fragmentA;
    FragmentB fragmentB;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.message);
        fragmentManager = getSupportFragmentManager();
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentManager.addOnBackStackChangedListener(this);

    }

    public void addA(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragmentA, "fragA");
        transaction.addToBackStack("fragA");
        transaction.commit();
    }
    public void removeA(View v) {
        FragmentA fa = (FragmentA)fragmentManager.findFragmentByTag("fragA");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fa != null) {
            transaction.remove(fa);
            transaction.addToBackStack("removeA");
            transaction.commit();
        }
        else {
            Toast.makeText(this, "Add Fragment A first", Toast.LENGTH_SHORT).show();
        }
    }
    public void replaceAwithB(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragmentB, "fragB");
        transaction.addToBackStack("replaceAwithB");
        transaction.commit();
    }

    public void addB(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragmentB, "fragB");
        transaction.addToBackStack("fragB");
        transaction.commit();
    }
    public void removeB(View v) {
        FragmentB fb = (FragmentB) fragmentManager.findFragmentByTag("fragB");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fb != null) {
            transaction.remove(fb);
            transaction.addToBackStack("removeB");
            transaction.commit();
        }
        else {
            Toast.makeText(this, "Add Fragment B first", Toast.LENGTH_SHORT).show();
        }
    }
    public void replaceBwithA(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragmentA, "fragA");
        transaction.addToBackStack("replaceBwithA");
        transaction.commit();
    }
    public void attachA(View v) {
        FragmentA fa =(FragmentA) fragmentManager.findFragmentByTag("fragA");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fa != null) {
            transaction.attach(fa);
            transaction.addToBackStack("attachA");
            transaction.commit();
        }

    }
    public void detachA(View v) {
        FragmentA fa =(FragmentA) fragmentManager.findFragmentByTag("fragA");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fa != null) {
            transaction.detach(fa);
            transaction.addToBackStack("detachA");
            transaction.commit();
        }
    }

    public void back(View v) {
        fragmentManager.popBackStack();
    }
    public void popAddB(View v) {
        //fragmentManager.popBackStack("fragB", 0);
        fragmentManager.popBackStack("fragB", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    @Override
    public void onBackStackChanged() {
        Log.d("RRR", "onBackStackChanged called");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\nCurrent Status of BackStack:\n");
        int count = fragmentManager.getBackStackEntryCount();
        for(int i = count-1; i >= 0; i--) {
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
            textView.setText(textView.getText()+" "+entry.getName() + "\n");
        }
        textView.setText(textView.getText()+"\n");
    }
}
