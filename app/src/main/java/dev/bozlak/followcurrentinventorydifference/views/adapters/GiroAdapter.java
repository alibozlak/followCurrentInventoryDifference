package dev.bozlak.followcurrentinventorydifference.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.databinding.RecyclerRowGiroBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.Giro;
import dev.bozlak.followcurrentinventorydifference.views.ListOfGiroFragmentDirections;

public class GiroAdapter extends RecyclerView.Adapter<GiroAdapter.GiroViewHolder> {
    private final List<Giro> giros;

    public GiroAdapter(List<Giro> giros) {
        this.giros = giros;
    }

    protected static class GiroViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerRowGiroBinding binding;
        protected GiroViewHolder(RecyclerRowGiroBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public GiroAdapter.GiroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowGiroBinding binding =
                RecyclerRowGiroBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GiroViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GiroAdapter.GiroViewHolder holder, int position) {
        String dateString = new Date(this.giros.get(position).getGiroDate()).toString();
        String tvToDateString = dateString + " tarihine kadarki ciro : ";
        holder.binding.tvToDateInRecyclerRowGiro.setText(tvToDateString);
        double giroAmount = this.giros.get(position).getGiroAmount();
        String tvGiroAmountString = giroAmount + " TL";
        holder.binding.tvAmountInRecyclerRowGiro.setText(tvGiroAmountString);

        holder.binding.btnUpdateAmountInRecyclerRowGiro.setOnClickListener(view -> {
            int giroId = this.giros.get(position).getGiroId();
            var action = ListOfGiroFragmentDirections.actionListOfGiroFragmentToUpdateGiroAmountFragment();
            action.setGiroId(giroId);
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return this.giros.size();
    }
}
