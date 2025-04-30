package dev.bozlak.followcurrentinventorydifference.dao;

public final class DbConstants {
    public static final String DB_NAME = "inventory";
    public static final String DB_PATH = "/data/user/0/dev.bozlak.followcurrentinventorydifference/databases/inventory";

    public static final String PRODUCTS_TABLE_NAME = "products";
    public static final String PRODUCT_ID_COLUMN_NAME = "product_id";
    public static final String PRODUCT_PRICE_COLUMN_NAME = "product_price";
    public static final String PRODUCT_TAX_COLUMN_NAME = "tax";
    public static final String LAST_PRODUCT_INVENTORY_DIFFERENCE_COLUMN_NAME
            = "last_product_inventory_difference";
    public static final String LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME = "last_product_inventory_date";
    public static final String SALES_UNIT_TYPE_COLUMN_NAME = "sales_unit_type";
    public static final String PRODUCT_NAME_COLUMN_NAME = "product_name";
    public static final String PRODUCT_CODE_COLUMN_NAME = "product_code";
    public static final String PRODUCT_SHORT_CODE_COLUMN_NAME = "product_short_code";
    public static final String PRODUCT_DESCRIPTION_COLUMN_NAME = "product_description";

    public static final String EVENTS_AFFECTING_INVENTORY_TABLE_NAME = "events_affecting_inventory";
    public static final String EVENT_ID_COLUMN_NAME = "event_id";
    public static final String AMOUNT_COLUMN_NAME = "amount";
    public static final String EVENT_DATE_AND_TIME_COLUMN_NAME = "event_date_and_time";

    public static final String GENERAL_INVENTORY_DATES_TABLE_NAME = "general_inventory_dates";
    public static final String GENERAL_INVENTORY_ID_COLUMN_NAME = "general_inventory_id";
    public static final String GENERAL_INVENTORY_DATE_AND_TIME_COLUMN_NAME = "general_inventory_date_and_time";

    public static final String AFFECTING_TYPES_TABLE_NAME = "affecting_types";
    public static final String AFFECTING_TYPE_ID_COLUMN_NAME = "affecting_type_id";
    public static final String AFFECTING_TYPE_COLUMN_NAME = "affecting_type";
    public static final String[] NEGATIVE_EVENT_TYPES = {
            "Kasiyer Hatası",
            "Hırsızlık",
            "Farklı Yöntemli İndirim",
            "Son Envanter Sayım Hatası",
            "Depo İrsaliye Hatası",
            "Direkt Ürün İrsaliye Hatası",
            "CH Çekleme Hatası",
            "Müşteri Hatası",
            "Diğer"
    };
    public static final String[] POSITIVE_EVENT_TYPES = {
            "Hırsız Yakalama",
            "Farklı Yöntemli İndirim",
            "Kasiyer Hatası",
            "Son Envanter Sayım Hatası",
            "Depo İrsaliye Hatası",
            "Direkt Ürün İrsaliye Hatası",
            "CH Çekleme Hatası",
            "Müşteri Hatası",
            "Diğer"
    };
}
