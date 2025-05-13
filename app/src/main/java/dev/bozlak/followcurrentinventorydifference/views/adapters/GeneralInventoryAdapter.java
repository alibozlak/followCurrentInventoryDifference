package dev.bozlak.followcurrentinventorydifference.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GeneralInventoryDateService;
import dev.bozlak.followcurrentinventorydifference.databinding.RecyclerRowGeneralInventoryBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.GeneralInventoryDate;
import dev.bozlak.followcurrentinventorydifference.views.ServiceContainer;

public class GeneralInventoryAdapter extends RecyclerView.Adapter<GeneralInventoryAdapter.GeneralInventoryViewHolder> {
    private final GeneralInventoryDateService generalInventoryDateService =
            ServiceContainer.generalInventoryDateService;
    private final List<GeneralInventoryDate> generalInventoryDates;

    public GeneralInventoryAdapter(List<GeneralInventoryDate> generalInventoryDates) {
        this.generalInventoryDates = generalInventoryDates;
    }

    public static class GeneralInventoryViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerRowGeneralInventoryBinding binding;
        public GeneralInventoryViewHolder(RecyclerRowGeneralInventoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public GeneralInventoryAdapter.GeneralInventoryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        RecyclerRowGeneralInventoryBinding binding = RecyclerRowGeneralInventoryBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GeneralInventoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(
            @NonNull GeneralInventoryAdapter.GeneralInventoryViewHolder holder, int position) {
        Date date = new Date(this.generalInventoryDates.get(position).getGeneralInventoryDateAndTime());
        String dateString = date.toString();
        holder.binding.tvInventoryDateInRecyclerRow.setText(dateString);
        holder.binding.btnDeleteGeneralInventory.setOnClickListener(v -> {
            int generalInventoryId = this.generalInventoryDates.get(position).getGeneralInventoryId();
            if(this.generalInventoryDateService.deleteGeneralInventoryDate(generalInventoryId)){
                this.generalInventoryDates.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, this.generalInventoryDates.size());
                Toast.makeText(v.getContext(), "Envanter tarihi silindi!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Envanter tarihi silme başarısız!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.generalInventoryDates.size();
    }
}
