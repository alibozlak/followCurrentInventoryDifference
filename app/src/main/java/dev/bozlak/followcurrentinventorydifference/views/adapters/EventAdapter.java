package dev.bozlak.followcurrentinventorydifference.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.databinding.RecyclerRowEventBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventForListOfEvents;

public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private final List<EventForListOfEvents> events;

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
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }
}
