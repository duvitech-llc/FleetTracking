package com.siriusgps.fleettracking;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;


public class SetupAccountActivity extends FragmentActivity implements IFragmentNavigation,
        VerifyAccountFragment.OnVerifyAccountInteractionListener,
        NewAccountFragment.OnAccountCreateInteractionListener
{

    private static final String TAG = "SetupAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_setup_account);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    public void swapFragment(Fragment newFragment){
        Log.d(TAG, "swapFragment()");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onSubmitVerification() {
        Log.d(TAG, "onSubmitVerification()");

    }

    @Override
    public void onAccountCreate() {
        Log.d(TAG, "onAccountCreate()");

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String TAG = "PlaceholderFragment";
        IFragmentNavigation mCallBack;

        public PlaceholderFragment() {
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d(TAG, "onAttach()");
            try{
                mCallBack = (IFragmentNavigation) activity;
            }catch (ClassCastException e){
                throw new ClassCastException(activity.toString()
                        + " must implement IFragmentNavigation");
            }
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, "onStart()");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "onStop()");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView()");

            View rootView = inflater.inflate(R.layout.fragment_firstrun, container, false);

            final Button btnActivate = (Button)rootView.findViewById(R.id.btn_activate);
            btnActivate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VerifyAccountFragment verifyAccountFragment = new VerifyAccountFragment();
                    mCallBack.swapFragment(verifyAccountFragment);
                }
            });

            final Button btnNewAccount = (Button)rootView.findViewById(R.id.btn_new_account);
            btnNewAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewAccountFragment newAccountFragment = new NewAccountFragment();
                    mCallBack.swapFragment(newAccountFragment);
                }
            });

            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d(TAG, "onDestroyView()");
        }
    }
}
