package cpa.carpurchases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // As data will be passed from this activity to another INTENT EXTRAS will be used
    // These are the keys for the data that will be sent
    public static final String INTENTEXTA_ADD_OR_EDIT_FLAG = "iextra_addoredit"; //<<<< ADDED
    public static final String INTENTEXTRA_CUSTOMER_ID_To_PASS = "iextra_customerid"; //<<<< ADDED

    // Define states/modes that can be passed (just the 2 ADD or EDIT)
    public static final boolean MODE_CUSTOMER_ADD = false;
    public static final boolean MODE_CUSTOMER_EDIT = true;


//public ListView lv;
    private ListView mLV; //<<<< ADDED The ListView
    private dataAdapter mDA; //<<<< ADDED
    private DatabaseHandler mDBH; //<<<< ADDED
    private ArrayList<Customer> mCustomers = new ArrayList<>(); //<<<< ADDED

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // even tried ListView here (R.id.listView....)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Please, click ADD button to begin! ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        mLV = (ListView) this.findViewById(R.id.listView); //<<<< ADDED
        mDBH = new DatabaseHandler(this);
        ShowRecords();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Always refresh the List when Activity is resumed
        ShowRecords();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about)
        {
            // possibly go to another page
            Toast.makeText(MainActivity.this,
                    "SQLite Database App", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    public void buttonClicked(View view)
    {
        // START NEW ACTIVITY WHEN ADD BUTTON CLICKED
        //Intent intent = new Intent(this, CustomerRcrdActivity.class);
        //startActivity(intent);
        startAddOrEdit(0);

    }

    private void ShowRecords() {

            /*
                NOTE as edit or update will require another activity update or
                edit are difficult to choose
                if just a single button for edit and another for delete
                then which customer to edit or delete?

                As such will currently use long click to update with no delete option

                Could display details for the "selected customer" along with buttons that
                are only available when a customer has been clicked.

                Could include buttons for update/delete in the list

                Could bring up dialog asking which when long-clicking or clicking
                (long-click used currently for update to reduce potential accidental
                jump to update activity)

            */

        mCustomers.clear();
        mCustomers.addAll(mDBH.getAllCustomers());
        //final ArrayList<Customer> customers = new ArrayList<>(dh.getAllCustomers());
        //final Customer customers = new Customer(dh.getAllCustomers());
        if (mDA == null) {
            mDA = new dataAdapter(this, mCustomers);
            mLV.setAdapter(mDA);
            mLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startAddOrEdit(mDA.getItem(i).getID());
                    return true;
                }
            });
        } else {
            mDA.notifyDataSetChanged();
        }
    }

    private void startAddOrEdit(long customerID) {
        Intent i = new Intent(this,CustomerRcrdActivity.class);
        if (customerID < 1) {
            i.putExtra(INTENTEXTA_ADD_OR_EDIT_FLAG,MODE_CUSTOMER_ADD);
        } else {
            i.putExtra(INTENTEXTA_ADD_OR_EDIT_FLAG,MODE_CUSTOMER_EDIT);
        }
        i.putExtra(INTENTEXTRA_CUSTOMER_ID_To_PASS,customerID);
        startActivity(i);
    }
}
