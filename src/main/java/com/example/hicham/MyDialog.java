package com.example.hicham.properties;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Hicham on 6/22/2016.
 */
public class MyDialog extends AlertDialog {
    private AlertDialog.Builder builder;

    public MyDialog(final Context context, final Appartment appartment) {
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
