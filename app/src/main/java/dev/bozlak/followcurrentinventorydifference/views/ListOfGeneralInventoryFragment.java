package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GeneralInventoryDateService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentListOfGeneralInventoryBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.GeneralInventoryDate;
import dev.bozlak.followcurrentinventorydifference.views.adapters.GeneralInventoryAdapter;

public class ListOfGeneralInventoryFragment extends Fragment {
    private FragmentListOfGeneralInventoryBinding binding;
    private final GeneralInventoryDateService generalInventoryDateService =
            ServiceContainer.generalInventoryDateService;
    private List<GeneralInventoryDate> generalInventoryDates = null;

    public ListOfGeneralInventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListOfGeneralInventoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.generalInventoryDates = this.generalInventoryDateService.getAllGeneralInventoryDates();
        binding.rvInListOfGeneralInventoryFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvInListOfGeneralInventoryFragment.setAdapter(new GeneralInventoryAdapter(generalInventoryDates));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}