package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Date;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GiroService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentUpdateGiroAmountBinding;

public class UpdateGiroAmountFragment extends Fragment {
    private final GiroService giroService = ServiceContainer.giroService;
    private FragmentUpdateGiroAmountBinding binding;
    private int giroId;

    public UpdateGiroAmountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateGiroAmountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.giroId = getArguments().getInt("giroId");
        var giroWithStartAndEndDate = giroService.getGiroWithStartToEndDate(giroId);

        long fromDate = giroWithStartAndEndDate.getStartDate();
        String fromDateString = new Date(fromDate).toString() + " tarihinden";
        binding.tvFromDateInUpdateGiroAmountFragment.setText(fromDateString);

        long toDate = giroWithStartAndEndDate.getGiroDate();
        String toDateString = new Date(toDate).toString() + " tarihine kadarki";
        binding.tvToDateInGiroAmountFragment.setText(toDateString);

        double giroAmount = giroWithStartAndEndDate.getGiroAmount();
        binding.etGiroAmountInUpdateGiroFragment.setText(String.valueOf(giroAmount));

        binding.btnUpdateInUpdateGiroFragment.setOnClickListener(this::updateGiroAmount);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    private void updateGiroAmount(View view){
        double newGiroAmount = Double.parseDouble(binding.etGiroAmountInUpdateGiroFragment.getText().toString());
        if(giroService.updateGiroAmount(this.giroId, newGiroAmount)){
            var action = UpdateGiroAmountFragmentDirections.actionUpdateGiroAmountFragmentToListOfGiroFragment();
            Navigation.findNavController(view).navigate(action);
            Toast.makeText(requireContext(), "Ciro Güncelleme başarılı", Toast.LENGTH_SHORT).show();
            this.onDestroyView();
        } else {
            Toast.makeText(getContext(), "Ciro Güncelleme başarısız!!", Toast.LENGTH_SHORT).show();
        }
    }

}