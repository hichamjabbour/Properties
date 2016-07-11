package com.example.hicham.properties;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import java.lang.reflect.Array;

/**
 * Created by Hicham on 6/22/2016.
 */
public class MyDialog extends AlertDialog {
    private AlertDialog.Builder builder;

    public MyDialog(final Context context, final View view, final Appartment appartment) {
        super(context);
        builder = new Builder(context);
        getBuilder().setTitle("Action")
                .setItems(R.array.longPress, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case 0:
                                String message = appartment.getAppartmentCharacteristics().getDescription();
                                Intent share = new Intent(Intent.ACTION_SEND);
                                share.setType("text/plain");
                                share.putExtra(Intent.EXTRA_TEXT, message);
                                context.startActivity(Intent.createChooser(share, "Share Via"));
                                break;

                            case 1:
                                   if(ListAdapter2.mEntries.contains(appartment))
                                   {
                                       Array.set(context.getResources().getStringArray(R.array.sidebar),1,"Remove Saved Property");
                                       ListAdapter2.mEntries.remove(appartment);
                                   }

                                   else
                                   {
                                       Array.set(context.getResources().getStringArray(R.array.sidebar),1,"Save Property");
                                       ListAdapter2.mEntries.add(appartment);
                                   }

                                   break;

                            case 2:
                                   ListAdapter3.mEntries.add(appartment.getAgent());
                                   break;

                            case 3:
                                Uri uri1 = Uri.parse("tel:" + appartment.getAgent().getAgent_phone());
                                Intent intent1 = new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,uri1);
                                intent1.putExtra(ContactsContract.Intents.Insert.NAME,appartment.getAgent().getAgent_name());
                                context.startActivity(intent1);
                                break;

                            case 4:
                                Uri uri = Uri.parse("tel:" + appartment.getAgent().getAgent_phone());
                                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                context.startActivity(intent);
                                break;


                        }

                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        getBuilder().create();
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }
}
