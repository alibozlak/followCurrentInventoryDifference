package dev.bozlak.followcurrentinventorydifference.views.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class AddEventSpinnerAdapter extends ArrayAdapter<String> {
    public AddEventSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }
}
