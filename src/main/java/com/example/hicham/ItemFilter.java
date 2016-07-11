package com.example.hicham.properties;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hicham on 6/15/2016.
 */
 public class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            int filterString1 = -1;

            int filterString2 = -1;
            try{
                filterString1 = Integer.decode(constraint.toString().replaceAll(".*|",""));
                filterString2 = Integer.decode(constraint.toString().replaceAll("|.*",""));
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }

            FilterResults results = new FilterResults();

            final List<List<Appartment>> nlist = new ArrayList<List<Appartment>>(2);
            final List<Appartment> nlist1 = new ArrayList<Appartment>(((List<Appartment>)ListAdapter1.mEntries.get(0)).size());
            final List<Appartment> nlist2 = new ArrayList<Appartment>(((List<Appartment>)ListAdapter1.mEntries.get(1)).size());
            Integer filterableString;


            for(int i=0;i<2;++i)
            {
                List<Appartment> list = (List<Appartment>)ListAdapter1.mEntries.get(i);
                for (int j = 0; j < list.size(); j++) {
                    filterableString = Integer.decode(list.get(j).getAppartment_constitution().getNum_bathrooms());
                    if ((filterString1==-1 && filterString2==-1) || (filterableString > filterString1 && filterableString < filterString2)) {
                        nlist.get(i).add(list.get(j));
                    }
            }

            }

            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            LoadFeedData.onPostExecute((List<List<Appartment>>) results.values);

        }

    }


