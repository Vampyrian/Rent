package gf.nuoma.pv.rent.ui.signInFragment;

import android.arch.lifecycle.Lifecycle;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gf.nuoma.pv.rent.MainActivity;
import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.SignInFragmentBinding;

public class SignInFragment extends Fragment {

    private static final String LOG_TAG = "SignInFragment";

    private SignInFragmentBinding mBinding;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.sign_in_fragment, container, false);
        mBinding.setSignInFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void singIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(LOG_TAG, "Pavyko prisijungti");
                            showRequestFragment();
                        } else {
                            Log.w(LOG_TAG, "Nepavyko prisijungti");
                            Toast.makeText(getContext(), R.string.toastNepavykoPrisijungti, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void singUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(LOG_TAG, "Paskyra sekmingai sukurta");
                            showRequestFragment();
                        } else {
                            Log.w(LOG_TAG, "Paskyra nesukurta", task.getException());
                            Toast.makeText(getContext(), R.string.toastPaskyraNesukurta, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean emailIsBad(String email) {
        if (email.isEmpty()){
            Toast.makeText(getContext(), R.string.toastNeivestasEmailas, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private boolean passwordIsBad(String password) {
        if (password.isEmpty()) {
            Toast.makeText(getContext(), R.string.toastNeivestasSlaptazodis, Toast.LENGTH_LONG).show();
            return true;
        }
        if (password.length() < 6) {
            Toast.makeText(getContext(), R.string.toastSlaptazodisPerMazas, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private void hideKeyboard (View v) {
        InputMethodManager imn = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imn.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void showRequestFragment() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).showRequestFragment();
        }
    }



    /*
    **********************************Navigacija is fragmento
     */
    public void onSingUpClicked(View v) {
        hideKeyboard(v);
        String email = mBinding.editTextEmail.getText().toString();
        String password = mBinding.editTextPassword.getText().toString();

        if(!emailIsBad(email) && !passwordIsBad(password)) {
            singUp(email, password);
        }

    }

    public void onSingInClicked(View v) {
        hideKeyboard(v);
        String email = mBinding.editTextEmail.getText().toString();
        String password = mBinding.editTextPassword.getText().toString();
        if(!emailIsBad(email) && !passwordIsBad(password)) {
            singIn(email, password);
        }
    }
}
