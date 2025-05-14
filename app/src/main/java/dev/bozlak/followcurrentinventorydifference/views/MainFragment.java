package dev.bozlak.followcurrentinventorydifference.views;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.ProductService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {
    private final ProductService productService = ServiceContainer.productService;
    private FragmentMainBinding binding;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        double summaryCurrentInventoryDifferencePrice = productService.getSummaryCurrentInventoryDifferencePrice();
        binding.tvSummaryDifferencePrice.setText(String.valueOf(summaryCurrentInventoryDifferencePrice));
        binding.btnAddProduct.setOnClickListener(this::navigateToAddProductFragment);
        binding.btnAddGeneralInventoryDate.setOnClickListener(this::navigateToAddGeneralInventoryDateFragment);
        binding.btnListGeneralInventoryDates.setOnClickListener(this::navigateToListOfGeneralInventoryFragment);
        binding.btnListProducts.setOnClickListener(this::navigateToListOfProductFragment);
        Button btnAddPositiveEvent = binding.btnAddEventInMainFragment;
        btnAddPositiveEvent.setOnClickListener(this::navigateToAddPositiveEventFragment);
        btnAddPositiveEvent.setBackgroundColor(Color.GREEN);
        var btnAddNegativeEvent = binding.btnAddNegativeEventInMainFragment;
        btnAddNegativeEvent.setOnClickListener(this::navigateToAddNegativeEventFragment);
        btnAddNegativeEvent.setBackgroundColor(Color.RED);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToAddGeneralInventoryDateFragment(View view){
        var action = MainFragmentDirections.mainFragmentToAddGeneralInventoryDateFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void navigateToListOfGeneralInventoryFragment(View view){
        var action = MainFragmentDirections.mainFragmentToListOfGeneralInventoryFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void navigateToListOfProductFragment(View view){
        var action = MainFragmentDirections.mainFragmentToListOfProductFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void navigateToAddProductFragment(View view){
        var action = MainFragmentDirections.mainFragmentToAddProductFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void navigateToAddPositiveEventFragment(View view){
        this.navigateToAddEventFragment(view, true);
    }

    private void navigateToAddNegativeEventFragment(View view){
        this.navigateToAddEventFragment(view, false);
    }

    private void navigateToAddEventFragment(View view, boolean isEventPositive){
        var action = MainFragmentDirections.mainFragmentToAddPositiveEventFragment();
        action.setIsEventPositive(isEventPositive);
        Navigation.findNavController(view).navigate(action);
    }
}