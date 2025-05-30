package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Date;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GiroService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentAddGiroBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;

public class AddGiroFragment extends Fragment {
    private final GiroService giroService = ServiceContainer.giroService;
    private FragmentAddGiroBinding binding;

    public AddGiroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddGiroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.fillHeaderAndCalenderView();
        binding.cvDateInAddGiroFragment.setOnDateChangeListener(Utils.onDateChangeListener);
        binding.btnAddGiroInAddGiroFragment.setOnClickListener(this::addGiro);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    private void fillHeaderAndCalenderView(){
        long minimumDayForCalendarViewInAddGiroFragment = giroService.getMinimumDayForCalendarViewInAddGiroFragment();
        String minimumDay = new Date(minimumDayForCalendarViewInAddGiroFragment).toString();
        binding.tvHeaderFromDateToDate.setText(minimumDay + " tarihinden");
        binding.cvDateInAddGiroFragment.setMinDate(minimumDayForCalendarViewInAddGiroFragment);
    }

    private void addGiro(View view){
        long selectedDate = binding.cvDateInAddGiroFragment.getDate();
        double giroAmount = 0;
        String giroAmountString = binding.etGiroAmountInAddGiroFragment.getText().toString();
        if (!giroAmountString.isBlank()){
            giroAmount = Double.parseDouble(giroAmountString);
        }
        var giroDateAndAmount = new GiroDateAndAmount(selectedDate, giroAmount);
        if(giroService.addGiro(giroDateAndAmount)){
            Toast.makeText(this.getContext(), "Ciro Ekleme Başarılı", Toast.LENGTH_LONG).show();
            this.fillHeaderAndCalenderView();
            binding.etGiroAmountInAddGiroFragment.setText("");
        } else {
            Toast.makeText(this.getContext(), "Ciro Ekleme Başarısız!!", Toast.LENGTH_SHORT).show();
        }
    }
}