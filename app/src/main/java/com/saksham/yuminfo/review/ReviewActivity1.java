/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.saksham.yuminfo.review;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.saksham.yuminfo.Cheeses;
import com.saksham.yuminfo.R;

public class ReviewActivity1 extends AppCompatActivity {

    public static final String EXTRA_NAME = "cheese_name";
    // this context will use when we work with Alert Dialog
    final Context context = this;
    private Context mContext;
    LinearLayout mRelativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review1);

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        // Get the application context
        mContext = getApplicationContext();
        // Get the widgets reference from XML layout
        mRelativeLayout = (LinearLayout) findViewById(R.id.rl);

        FloatingActionButton review1 = (FloatingActionButton) findViewById(R.id.review1);
        review1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Snackbar.make(view, "The Yum Menu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                /* Alert Dialog Code Start*/
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Review "); //Set Alert dialog title here
                alert.setMessage("Enter Your Comment Here"); //Message here

                // Set an EditText view to get user input
                final EditText input = new EditText(context);
                alert.setView(input);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //You will get as string input data in this variable.
                        // here we convert the input to a string and show in a toast.
                        String srt = input.getEditableText().toString();
                        Toast.makeText(context,srt,Toast.LENGTH_LONG).show();

                        // Initialize a new CardView
                        CardView card = new CardView(mContext);
                        // Set the CardView layoutParams
                        CollapsingToolbarLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(
                                CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT,
                                CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT
                        );
                        card.setLayoutParams(params);

                        // Set CardView corner radius
                        card.setRadius(9);
                        // Set cardView content padding
                        card.setContentPadding(15, 15, 15, 15);
                        // Set a background color for CardView
                        card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));
                        // Set the CardView maximum elevation
                        card.setMaxCardElevation(15);
                        // Set CardView elevation
                        card.setCardElevation(9);
                        // Initialize a new TextView to put in CardView
                        TextView tv = new TextView(mContext);
                        tv.setLayoutParams(params);
                        //tv.setText("CardView\nProgrammatically");
                        tv.setText(srt);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                        tv.setTextColor(Color.RED);
                        // Put the TextView in CardView
                        card.addView(tv);
                        // Finally, add the CardView in root layout
                        mRelativeLayout.addView(card);

                    } // End of onClick(DialogInterface dialog, int whichButton)
                }); //End of alert.setPositiveButton
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        dialog.cancel();
                    }
                }); //End of alert.setNegativeButton
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
       /* Alert Dialog Code End*/
            }
        });

        loadBackdrop();
    }

    private void loadBackdrop() {
        Intent intent = getIntent();
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(Cheeses.getRandomCheeseDrawable(intent.getIntExtra("POS",0))).centerCrop().into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }
}
