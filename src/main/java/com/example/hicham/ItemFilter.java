package com.example.hicham.properties;

import android.content.Context;
import android.widget.Filter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hicham on 6/15/2016.
 */
 public class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            int filterString = Integer.decode(constraint.toString());
            FilterResults results = new FilterResults();

            int count = ListAdapter2.mEntries.size();
            final List<Appartment> nlist = new ArrayList<Appartment>(count);

            Integer filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = Integer.decode(ListAdapter2.mEntries.get(i).getAppartmentCharacteristics().getPrice());
                if (filterableString == filterString) {
                    nlist.add(ListAdapter2.mEntries.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            LoadFeedData.onPostExecute((List<Appartment>) results.values);

        }

    }


