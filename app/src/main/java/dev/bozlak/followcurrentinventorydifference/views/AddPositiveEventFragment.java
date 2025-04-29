package dev.bozlak.followcurrentinventorydifference.views;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.EventAffectingInventoryService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.ProductService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentAddPositiveEventBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventProductIdEventAmountAndDate;
import dev.bozlak.followcurrentinventorydifference.views.adapters.AddEventSpinnerAdapter;

public class AddPositiveEventFragment extends Fragment {
    private FragmentAddPositiveEventBinding binding;
    private final ProductService productService = ServiceContainer.productService;
    private final EventAffectingInventoryService eventAffectingInventoryService
            = ServiceContainer.eventAffectingInventoryService;
    private List<String> productCodeAndNames;
    private boolean isEventPositive;


    public AddPositiveEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPositiveEventBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillProductCodeAndNameStringList();
        configProductSpinnerAdapter();
        isEventPositive = getArguments().getBoolean("isEventPositive");
        editAddEventFragment();
        binding.btnAddEventInAddEventFragment.setOnClickListener(v -> doOperations(v));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void doOperations(View view){
        String productCode = binding.spnnrProduct.getSelectedItem().toString().substring(0,7);
        int productId = productService.getProductIdByProductCode(productCode);
        double amount = 0;
        if(!binding.etAmountInAddEventFragment.getText().toString().isBlank()){
            amount = Double.parseDouble(binding.etAmountInAddEventFragment.getText().toString());
        }
        if(!this.isEventPositive){
            amount = (-1)*amount;
        }
        long eventDate = binding.cvDateInAddEventFragment.getDate();
        var eventDto = new EventProductIdEventAmountAndDate(productId, amount, eventDate);

        if(eventAffectingInventoryService.addEventAffectingInventory(eventDto)){
            Toast.makeText(this.getContext(), "İşlem başarılı.", Toast.LENGTH_SHORT).show();
            binding.spnnrProduct.setSelection(0);
            binding.etAmountInAddEventFragment.setText("");
            binding.cvDateInAddEventFragment.setDate(new Date().getTime());
        } else {
            Toast.makeText(this.getContext(), "İşlem başarısız!", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillProductCodeAndNameStringList() {
        this.productCodeAndNames = new ArrayList<>();
        for (var product : productService.productCodeAndNames()) {
            this.productCodeAndNames.add(product.toString());
        }
    }

    private void configProductSpinnerAdapter(){
        SpinnerAdapter adapter = new AddEventSpinnerAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                productCodeAndNames
        );
        binding.spnnrProduct.setAdapter(adapter);
    }

    private void editAddEventFragment(){
        TextView tvHeader = binding.tvHeaderInAddEventFragment;
        TextView tvAmount = binding.tvAmountInAddEventFragment;
        EditText etAmount = binding.etAmountInAddEventFragment;
        Button btnAddEvent = binding.btnAddEventInAddEventFragment;
        int color = Color.RED;
        if(this.isEventPositive){
            tvHeader.setText("Envanteri Olumlu Etkileyen Olay Ekle");
            color = Color.GREEN;
        } else {
            tvHeader.setText("Envanteri Olumsuz Etkileyen Olay Ekle");
        }
        this.changeColorSomeViews(color, tvHeader, tvAmount, etAmount, btnAddEvent);
    }

    private void changeColorSomeViews(int color, TextView tvHeader, TextView tvAmount,
            EditText etAmount, Button btnAddEvent){
        tvHeader.setTextColor(color);
        tvAmount.setTextColor(color);
        etAmount.setTextColor(color);
        btnAddEvent.setBackgroundColor(color);
    }
}