package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.R.layout;
import it.chalmers.mufasa.android_budget_app.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BudgetEditActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_budget_edit, menu);
        return true;
    }
}
