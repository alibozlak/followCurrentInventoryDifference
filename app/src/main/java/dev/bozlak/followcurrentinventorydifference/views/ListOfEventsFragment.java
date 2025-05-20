package dev.bozlak.followcurrentinventorydifference.views;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.EventAffectingInventoryService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentListOfEventsBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventForListOfEvents;
import dev.bozlak.followcurrentinventorydifference.views.adapters.EventAdapter;

public class ListOfEventsFragment extends Fragment {
    private final EventAffectingInventoryService eventAffectingInventoryService =
            ServiceContainer.eventAffectingInventoryService;
    private FragmentListOfEventsBinding binding;

    public ListOfEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListOfEventsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        boolean isEventNegative = getArguments().getBoolean("isEventNegative");
        var tvHeader = binding.tvHeaderInListOfEvents;
        binding.rvInListOfEventsFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        List<EventForListOfEvents> events = null;
        if(isEventNegative){
            tvHeader.setText("Son Genel Envanter Sonrası Mağazayı Olumsuz Etkileyen Olaylar");
            tvHeader.setTextColor(Color.RED);
            events = this.eventAffectingInventoryService.getAllNegativeEvents();
        } else {
            tvHeader.setText("Son Genel Envanter Sonrası Mağazayı Olumlu Etkileyen Olaylar");
            tvHeader.setTextColor(Color.GREEN);
            events = this.eventAffectingInventoryService.getAllPositiveEvents();
        }
        binding.rvInListOfEventsFragment.setAdapter(new EventAdapter(events));
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}