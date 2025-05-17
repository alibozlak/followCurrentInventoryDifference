package dev.bozlak.followcurrentinventorydifference.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
    private String productCodeFromDb;


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
        boolean forAddProduct = getArguments().getBoolean("forAddProduct");
        binding.etProductInventoryDifference.setInputType(
                InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED
        );
        binding.cvLastProductInventoryDate.setOnDateChangeListener(Utils.onDateChangeListener);
        if(forAddProduct){
            binding.btnUpdateProductInAddProductFragment.setVisibility(View.GONE);
            binding.btnAddProductInAddProductFragment.setOnClickListener(v -> doOperationsForAddProduct(v));
        } else {
            binding.btnAddProductInAddProductFragment.setVisibility(View.GONE);
            this.fillEditTextsForUpdateProductFragment();
            binding.btnUpdateProductInAddProductFragment.setOnClickListener(v -> doOperationsForUpdateProduct(v));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Product doValidationsAndReturnProduct(){
        Product product = this.doOperationsWithoutProductCodeAndReturnProduct();
        String productCode = binding.etProductCode.getText().toString();
        if(checkEnteredProductCode(productCode) && !productService.existsEnteredProductCode(productCode)){
            product.setProductCode(productCode);
        } else if(!checkEnteredProductCode(productCode)){
            product.setProductCode("0");
            Toast toast = Toast.makeText(this.getContext(), "Ürün kodu geçersiz!", Toast.LENGTH_SHORT);
            if(productCode.isBlank()){
                toast.setText("Ürün kodu boş olamaz!");
            }
            toast.show();
        } else {
            product.setProductCode("0");
            Toast toast = Toast.makeText(this.getContext(), "Girilen ürün kodu zaten kayıtlı!", Toast.LENGTH_SHORT);
            toast.show();
        }
        return product;
    }

    private Product doOperationsWithoutProductCodeAndReturnProduct(){
        Product product = new Product();
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
        product.setProductCode("0");
        product.setCurrentPrice(currentPrice);
        product.setTax(tax);
        product.setInventoryDifference(inventoryDifference);
        product.setLastProductInventoryDate(lastProductInventoryDate);
        product.setProductName(productName);
        return product;
    }

    private void doOperationsForAddProduct(View view){
        Product product = this.doValidationsAndReturnProduct();
        if(product.getProductCode().length() > 1){
            if(this.productService.addProduct(product)){
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
        }
    }

    private void doOperationsForUpdateProduct(View view){
        String enteredProductCode = binding.etProductCode.getText().toString();
        if(enteredProductCode.equals(this.productCodeFromDb)){
            Product product = this.doOperationsWithoutProductCodeAndReturnProduct();
            product.setProductId(getArguments().getInt("productId"));
            product.setProductCode(this.productCodeFromDb);
            this.showMessageForUpdateAfterClickedButton(product, view);
        } else {
            Product product = this.doValidationsAndReturnProduct();
            product.setProductId(getArguments().getInt("productId"));
            if(product.getProductCode().length() > 1){
                this.showMessageForUpdateAfterClickedButton(product, view);
            }
        }
    }

    private void showMessageForUpdateAfterClickedButton(Product product, View view){
        if(this.productService.updateProduct(product)){
            var action = AddProductFragmentDirections.actionAddProductFragmentToListOfProductFragment();
            Navigation.findNavController(view).navigate(action);
            Toast toast = Toast.makeText(requireContext(), "Ürün güncellendi.", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast.makeText(requireContext(), "Ürün güncellenirken hata oluştu.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkEnteredProductCode(String productCode){
        return (!productCode.isBlank() && ProductUtil.isProductCodeValid(productCode));
    }

    private void fillEditTextsForUpdateProductFragment(){
        int productId = getArguments().getInt("productId");
        Product product = productService.getProductByProductId(productId);
        this.productCodeFromDb = product.getProductCode();
        binding.etProductCode.setText(productCodeFromDb);
        binding.etProductPrice.setText(String.valueOf(product.getCurrentPrice()));
        binding.etProductTax.setText(String.valueOf(product.getTax()));
        binding.etProductInventoryDifference.setText(String.valueOf(product.getInventoryDifference()));
        binding.cvLastProductInventoryDate.setDate(product.getLastProductInventoryDate());
        binding.etProductName.setText(product.getProductName());
    }
}