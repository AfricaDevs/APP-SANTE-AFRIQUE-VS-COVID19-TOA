package com.africadevs.toa.ui.fragments.diagnosis;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;

import com.africadevs.toa.R;
import com.africadevs.toa.databinding.FragmentDiagnosisFirstMalariaBinding;
import com.africadevs.toa.interfaces.ActivityCallbackInterface;
import com.africadevs.toa.ui.fragments.DiagnosisFragment;

import static com.africadevs.toa.ui.fragments.DiagnosisFragment.DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_MALARIA_NEXT;

public class DiagnosisFirstMalariaFragment extends Fragment implements View.OnClickListener {

    ActivityCallbackInterface mCallback;
    private FragmentDiagnosisFirstMalariaBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDiagnosisFirstMalariaBinding.inflate(getLayoutInflater());


        try {
            ((AppCompatActivity) getActivity()).setSupportActionBar(binding.appBar.toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        binding.appBar.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    //EXPANDED;

                    binding.appBar.collapsingToolabar.setTitleEnabled(false);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    //COLLAPSED;

                    binding.appBar.collapsingToolabar.setTitleEnabled(false);
                } else {

                    //IDDLE
                }
            }
        });

        binding.expandableText.setTextMaxLines(3);

        binding.btnNext.setOnClickListener(this);
        binding.btnPrevention.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_next:

                if (mCallback != null) {
                    YoYo.with(Techniques.BounceIn).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_SECOND_DEPTH, DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_MALARIA_NEXT);
                        }
                    }).playOn(v);
                }
                break;

            case R.id.btn_prevention:

                if (mCallback != null) {
                    YoYo.with(Techniques.BounceIn).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_PREVENTION_DEPTH, 0);
                        }
                    }).playOn(v);

                }

                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (mCallback == null)
            mCallback = (ActivityCallbackInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (mCallback != null)
            mCallback = null;
    }

}
