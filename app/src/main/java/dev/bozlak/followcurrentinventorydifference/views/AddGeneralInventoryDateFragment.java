package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GeneralInventoryDateService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentAddGeneralInventoryDateBinding;

public class AddGeneralInventoryDateFragment extends Fragment {
    private FragmentAddGeneralInventoryDateBinding binding;
    private final GeneralInventoryDateService generalInventoryDateService = ServiceContainer.generalInventoryDateService;

    public AddGeneralInventoryDateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddGeneralInventoryDateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cvGeneralInvetoryDate.setOnDateChangeListener(Utils.onDateChangeListener);
        binding.btnInAddGeneralInventoryDateFragment.setOnClickListener(v -> doOperations(v));
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    private void doOperations(View view){
        long date = binding.cvGeneralInvetoryDate.getDate();
        if(generalInventoryDateService.addGeneralInventoryDate(date)){
            binding.cvGeneralInvetoryDate.setDate(Calendar.getInstance().getTimeInMillis());
            navigateToMainFragment(view);
            Toast.makeText(requireContext(), "Envanter tarihi eklendi!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Envanter tarihi ekleme başarısız!", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMainFragment(View view){
        var action = AddGeneralInventoryDateFragmentDirections.addGeneralInventoryDateFragmentToMainFragment();
        Navigation.findNavController(view).navigate(action);
    }
}