package viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyDataViewModel extends ViewModel {

    private MutableLiveData<List<String>> shoppingList;
    private MutableLiveData<String> country_id;

    public LiveData<String> getCounty_id(){
        if(country_id == null){
            country_id = new MutableLiveData<>();
            BranchView();
        }
        return country_id;
    }


    public LiveData<List<String>> getShoppingList() {
        if (shoppingList == null) {
            shoppingList = new MutableLiveData<>();
            loadShoppingList();
        }
        return shoppingList;
    }


    private void loadShoppingList() {
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            // 3
            List<String> shoppingListSample = new ArrayList<>();
            shoppingListSample.add("Bread");
            shoppingListSample.add("Bananas");
            shoppingListSample.add("Peanut Butter");
            shoppingListSample.add("Eggs");
            shoppingListSample.add("Chicken breasts");
            // 4
            long seed = System.nanoTime();
            Collections.shuffle(shoppingListSample, new Random(seed));
            // 5
            shoppingList.setValue(shoppingListSample);
            // 6
        }, 0);
    }

    private void BranchView(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
               /* try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                country_id.postValue("Bananas");
            }
        });


    }


}
