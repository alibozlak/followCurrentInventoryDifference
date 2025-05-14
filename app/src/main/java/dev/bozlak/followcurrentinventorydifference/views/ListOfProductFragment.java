package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.ProductService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentListOfProductBinding;
import dev.bozlak.followcurrentinventorydifference.views.adapters.ProductAdapter;

public class ListOfProductFragment extends Fragment {
    private final ProductService productService = ServiceContainer.productService;
    private FragmentListOfProductBinding binding;

    public ListOfProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListOfProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        var productIdCodeNameAndPriceList = this.productService.getAllProductIdCodeNameAndPrice();
        binding.rvInListOfProductFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvInListOfProductFragment.setAdapter(new ProductAdapter(productIdCodeNameAndPriceList));
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}