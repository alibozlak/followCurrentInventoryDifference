package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Date;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GeneralInventoryDateService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.GiroService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentListOfGiroBinding;
import dev.bozlak.followcurrentinventorydifference.views.adapters.GiroAdapter;

public class ListOfGiroFragment extends Fragment {
    private final GiroService giroService = ServiceContainer.giroService;
    private final GeneralInventoryDateService generalInventoryDateService
            = ServiceContainer.generalInventoryDateService;
    private FragmentListOfGiroBinding binding;


    public ListOfGiroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListOfGiroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        String headerLastGeneralInventoryDate = "Son Genel Envanter Tarihi : ";
        long lastGeneralInventoryDate = generalInventoryDateService.getLastGeneralInventoryDate();
        String lastGeneralInventoryDateInString = new Date(lastGeneralInventoryDate).toString();
        headerLastGeneralInventoryDate += lastGeneralInventoryDateInString;
        binding.tvLastGeneralInventoryDateInListGiroFragment.setText(headerLastGeneralInventoryDate);

        var giroList = giroService.getAllGirosAfterLastGeneralInventoryDate();
        binding.rvGirosInListOfGiroFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvGirosInListOfGiroFragment.setAdapter(new GiroAdapter(giroList));
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}