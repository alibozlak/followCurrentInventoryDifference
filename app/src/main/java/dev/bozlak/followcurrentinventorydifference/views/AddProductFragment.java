package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Date;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.ProductService;
import dev.bozlak.followcurrentinventorydifference.databinding.FragmentAddProductBinding;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;
import dev.bozlak.followcurrentinventorydifference.utilities.ProductUtil;


public class AddProductFragment extends Fragment {
    private final ProductService productService = ServiceContainer.productService;
    private FragmentAddProductBinding binding;

    public AddProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.etProductInventoryDifference.setInputType(
                InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED
        );
        binding.cvLastProductInventoryDate.setOnDateChangeListener(Utils.onDateChangeListener);
        binding.btnAddProductInAddProductFragment.setOnClickListener(v -> doOperation(v));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void doOperation(View view){
        String productCode = binding.etProductCode.getText().toString();
        if(checkEnteredProductCode(productCode) && !productService.existsEnteredProductCode(productCode)){
            String currentPriceString = binding.etProductPrice.getText().toString();
            double currentPrice = 0;
            if(!currentPriceString.isBlank()){
                currentPrice = Double.parseDouble(currentPriceString);
            }
            String taxString = binding.etProductTax.getText().toString();
            byte tax = 0;
            if(!taxString.isBlank()){
                tax = Byte.parseByte(taxString);
            }
            String inventoryDifferenceString = binding.etProductInventoryDifference.getText().toString();
            double inventoryDifference = 0;
            if(!inventoryDifferenceString.isBlank()){
                inventoryDifference = Double.parseDouble(inventoryDifferenceString);
            }
            long lastProductInventoryDate = binding.cvLastProductInventoryDate.getDate();
            String productName = binding.etProductName.getText().toString();

            Product product = new Product();
            product.setProductCode(productCode);
            product.setCurrentPrice(currentPrice);
            product.setTax(tax);
            product.setInventoryDifference(inventoryDifference);
            product.setLastProductInventoryDate(lastProductInventoryDate);
            product.setProductName(productName);
            if(addProduct(product)){
                Toast toast = Toast.makeText(this.getContext(), "Ürün eklendi.", Toast.LENGTH_SHORT);
                toast.show();
                binding.etProductCode.setText("");
                binding.etProductPrice.setText("");
                binding.etProductTax.setText("");
                binding.etProductInventoryDifference.setText("");
                binding.cvLastProductInventoryDate.setDate(new Date().getTime());
                binding.etProductName.setText("");
            } else {
                Toast.makeText(this.getContext(), "Ürün eklenirken hata oluştu.", Toast.LENGTH_SHORT).show();
            }
        } else if(!checkEnteredProductCode(productCode)){
            Toast toast = Toast.makeText(this.getContext(), "Ürün kodu geçersiz!", Toast.LENGTH_SHORT);
            if(productCode.isBlank()){
                toast.setText("Ürün kodu boş olamaz!");
            }
            toast.show();
        } else {
            Toast toast = Toast.makeText(this.getContext(), "Girilen ürün kodu zaten kayıtlı!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private boolean checkEnteredProductCode(String productCode){
        return (!productCode.isBlank() && ProductUtil.isProductCodeValid(productCode));
    }

    private boolean addProduct(Product product){
        return productService.addProduct(product);
    }
}