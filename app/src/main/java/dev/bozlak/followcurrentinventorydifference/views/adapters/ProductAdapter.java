package dev.bozlak.followcurrentinventorydifference.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.databinding.RecyclerRowProductBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductIdCodeNameAndPrice;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<ProductIdCodeNameAndPrice> products;
    public ProductAdapter(List<ProductIdCodeNameAndPrice> products){
        this.products = products;
    }

    protected static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerRowProductBinding binding;
        public ProductViewHolder(RecyclerRowProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowProductBinding binding =
                RecyclerRowProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.binding.tvProductCodeInRecyclerRowProduct.setText(
                this.products.get(position).getProductCode()
        );
        holder.binding.tvProductNameInRecyclerRowProduct.setText(
                this.products.get(position).getProductName()
        );
        holder.binding.tvLabelCurrentPriceInRecyclerRowProduct.setText("Ürünün Güncel Fiyatı : ");
        holder.binding.tvCurrentPriceInRecyclerRowProduct.setText(
                String.valueOf(this.products.get(position).getPrice())
        );

    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }
}
