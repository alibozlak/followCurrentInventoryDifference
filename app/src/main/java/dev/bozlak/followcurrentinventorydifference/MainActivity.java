package dev.bozlak.followcurrentinventorydifference;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createNewDatabaseAndTables();
    }

    /**
     * Creates a new database and tables opening application first time.
     */
    private void createNewDatabaseAndTables(){
        String sqlForCreateProductsTable = "CREATE TABLE IF NOT EXISTS "
                + DbConstants.PRODUCTS_TABLE_NAME + " ("
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbConstants.PRODUCT_PRICE_COLUMN_NAME + " REAL, "
                + DbConstants.PRODUCT_TAX_COLUMN_NAME + " INTEGER, "
                + DbConstants.LAST_PRODUCT_INVENTORY_DIFFERENCE_COLUMN_NAME + " REAL, "
                + DbConstants.LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME + " INTEGER, "
                + DbConstants.SALES_UNIT_TYPE_COLUMN_NAME + " TEXT, "
                + DbConstants.PRODUCT_NAME_COLUMN_NAME + " TEXT, "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + " TEXT, "
                + DbConstants.PRODUCT_SHORT_CODE_COLUMN_NAME + " TEXT, "
                + DbConstants.PRODUCT_DESCRIPTION_COLUMN_NAME + " TEXT"
                + ");";
        String sqlForCreateEventsAffectingInventoryTable = "CREATE TABLE IF NOT EXISTS "
                + DbConstants.EVENTS_AFFECTING_INVENTORY_TABLE_NAME + " ("
                + DbConstants.EVENT_ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " INTEGER, "
                + DbConstants.AMOUNT_COLUMN_NAME + " REAL, "
                + DbConstants.EVENT_DATE_AND_TIME_COLUMN_NAME + " INTEGER"
                + ");";
        String sqlForCreateGeneralInventoryDatesTable = "CREATE TABLE IF NOT EXISTS "
                + DbConstants.GENERAL_INVENTORY_DATES_TABLE_NAME + " ("
                + DbConstants.GENERAL_INVENTORY_ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbConstants.GENERAL_INVENTORY_DATE_AND_TIME_COLUMN_NAME + " INTEGER"
                + ");";
        String sqlForCreateAffectingTypesTable = "CREATE TABLE IF NOT EXISTS "
                + DbConstants.AFFECTING_TYPES_TABLE_NAME + " ("
                + DbConstants.AFFECTING_TYPE_ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbConstants.EVENT_ID_COLUMN_NAME + " INTEGER, "
                + DbConstants.AFFECTING_TYPE_COLUMN_NAME + " TEXT"
                + ");";
        String sqlForCreateGirosTable = "CREATE TABLE IF NOT EXISTS "
                + DbConstants.GIRO_TABLE_NAME + " ("
                + DbConstants.GIRO_ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbConstants.GIRO_AMOUNT_COLUMN_NAME + " REAL, "
                + DbConstants.GIRO_SELECTED_DATE_COLUMN_NAME + " INTEGER"
                + ");";

        try (SQLiteDatabase db = openOrCreateDatabase(DbConstants.DB_NAME, MODE_PRIVATE, null)){
            db.execSQL(sqlForCreateProductsTable);
            db.execSQL(sqlForCreateEventsAffectingInventoryTable);
            db.execSQL(sqlForCreateGeneralInventoryDatesTable);
            db.execSQL(sqlForCreateAffectingTypesTable);
            db.execSQL(sqlForCreateGirosTable);
            //System.out.println(db.getPath());
        }
    }
}