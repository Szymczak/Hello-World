package com.helloworld;




import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;


public class Launch extends Activity {
	
	private static final int INSERT_ID = Menu.FIRST;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.click_me);
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case INSERT_ID:
                changeText();
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
    
    
    public void changeText()
    {
    	TextView myTextView = (TextView) findViewById(R.id.textview);
    	myTextView.setTextSize(60);
    	myTextView.setText("BOOOM!!!");
    }
    
}
