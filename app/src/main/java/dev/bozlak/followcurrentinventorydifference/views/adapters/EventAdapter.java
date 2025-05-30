package dev.bozlak.followcurrentinventorydifference.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.AffectingTypeService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.EventAffectingInventoryService;
import dev.bozlak.followcurrentinventorydifference.databinding.RecyclerRowEventBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventForListOfEvents;
import dev.bozlak.followcurrentinventorydifference.views.ServiceContainer;

public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private final List<EventForListOfEvents> events;
    private final AffectingTypeService affectingTypeService = ServiceContainer.affectingTypeService;
    private final EventAffectingInventoryService eventAffectingInventoryService =
            ServiceContainer.eventAffectingInventoryService;

    public EventAdapter(List<EventForListOfEvents> events) {
        this.events = events;
    }

    protected static class EventViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerRowEventBinding binding;

        protected EventViewHolder(RecyclerRowEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowEventBinding binding =
                RecyclerRowEventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EventViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder holder, int position) {
        holder.binding.tvProductCodeInRecyclerRowEvent.setText(
                this.events.get(position).getProductCode()
        );
        holder.binding.tvProductNameInRecyclerRowEvent.setText(
                this.events.get(position).getProductName()
        );
        holder.binding.tvAmountValueInRecyclerRowEvent.setText(
                String.valueOf(this.events.get(position).getEventAmount())
        );
        Date date = new Date(this.events.get(position).getEventDateAndTime());
        String dateString = date.toString();
        holder.binding.tvDateValueInRecyclerRowEvent.setText(dateString);
        holder.binding.tvEventTypeValueInRecyclerRowEvent.setText(
                this.events.get(position).getEventType()
        );

        holder.binding.btnDeleteInRecyclerRowEvent.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Olay Silme İşlemi");
            String productName = this.events.get(position).getProductName();
            double eventAmount = this.events.get(position).getEventAmount();
            String eventDateAndTime = dateString;
            String message = "Ürün adı : " + productName
                    + "\nMiktar : " + eventAmount
                    + "\nTarih : " + eventDateAndTime
                    + "\n\nBu olayı silmek istediğinize emin misiniz?";
            builder.setMessage(message);
            builder.setPositiveButton("Evet", (dialog, which) -> {
                int eventId = this.events.get(position).getEventId();
                if(this.deleteEventGivenEventId(eventId, position)){
                    Toast.makeText(v.getContext(), "Olay silindi!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Olay silme başarısız!!!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }

    private boolean deleteEventGivenEventId(int eventId, int position){
        if(this.affectingTypeService.deleteAffectingTypeGivenEventId(eventId)){
            if(this.eventAffectingInventoryService.deleteEventGivenEventId(eventId)) {
                this.events.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, this.events.size());
                return true;
            }
        }
        return false;
    }
}
