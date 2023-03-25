package com.example.testapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.testapp.model.Recipe;
import com.example.testapp.network.RecipeHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private LinearLayoutManager manager;
    private NumberPicker numberPickerPopUp;
    private int numberPickerValue=1;

    private TextInputLayout namePopUp,reviewPopUp;
    private MaterialButton submitBtn;
    private  ArrayList<Item> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItem = new ArrayList<>();
        //awake();
        downloadRecipes();

    }


    public void actionBtn(int position) {
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.popup_window1,null);
        mbuilder.setView(view);
        AlertDialog dialog = mbuilder.create();
        dialog.show();
        numberPickerPopUp = view.findViewById(R.id.rate_NUP_popupWindow1);
        namePopUp = view.findViewById(R.id.name_TXT_popupWindow1);
        reviewPopUp = view.findViewById(R.id.review_TXT_popupWindow1);
        submitBtn = view.findViewById(R.id.submit_BTN_popupWindow1);
        numberPickerPopUp.setMaxValue(5);
        numberPickerPopUp.setMinValue(1);
        numberPickerPopUp.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                numberPickerValue = i1;
                Log.d("pttt",""+numberPicker);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkReview()){
                    return;
                }
                listItem.get(position).addReview(new UserReview(namePopUp.getEditText().getText().toString(),numberPickerValue,reviewPopUp.getEditText().getText().toString()));
                dialog.cancel();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void awake() {
        UserReview userReview1 = new UserReview("Arik",4,"Having good restaurant reviews is crucial these days. It is not just making our decision to pick one easier, it is also helping the restaurant be more successful");
        UserReview userReview2 = new UserReview("Nikita",5,"Having good restaurant reviews is crucial these days. It is not just making our decision to pick one easier, it is also helping the restaurant be more successful");
        UserReview userReview3 = new UserReview("Vlad",2," bad food");
        UserReview userReview4 = new UserReview("James",4,"Nice Food");
        UserReview userReview5 = new UserReview("Dorin",5,"Was A mistake");
        UserReview userReview6 = new UserReview("Max",2," bad food");
        ArrayList<UserReview> list = new ArrayList<>();
        ArrayList<UserReview> list1 = new ArrayList<>();
        list.add(userReview1);
        list.add(userReview2);
        list.add(userReview3);
        list1.add(userReview4);
        list1.add(userReview5);
        list1.add(userReview6);
        listItem.add(new Item(list,"Pasta"));
        listItem.add(new Item( ( new ArrayList<>()),"Hamburger"));
        listItem.add(new Item(list1,"Pizza"));
        listItem.add(new Item( ( new ArrayList<>()),"Steak"));
        adapter = new ReviewAdapter(listItem,getApplicationContext());
        adapter.setCallBackAddReview(callBackAddReview);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.itemList_RYC_main);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }


    private boolean checkReview(){
        int i= namePopUp.getEditText().getText().length();
        if(i<4) {
            Toast.makeText(getApplicationContext(),"At Least 4 letters in the name field ", Toast.LENGTH_SHORT).show();
            return false;
            }
        i= reviewPopUp.getEditText().getText().length();
        if(i<10) {
            Toast.makeText(getApplicationContext(),"At Least 10 letters in the describe field ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private CallBackAddReview callBackAddReview = new CallBackAddReview() {
        @Override
        public void openPopUpPage(int position) {
            actionBtn(position);

        }
    };

    private void downloadRecipes(){
        new RecipeHelper().fetchAllRecipes(getString(R.string.api_key),new RecipeHelper.CallBack_Recipes() {
            @Override

            public void recipes(List<Recipe> recipes) {

                ///Log.d("pttt",recipes.toString());
            }
        });
    }
}