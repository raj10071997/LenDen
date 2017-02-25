package com.game.dhanraj.lenden;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import Database.DatabaseDena;
import Model.DataOfDebt;

/**
 * Created by Dhanraj on 03-02-2017.
 */
public class InfoOfOwedMoney extends AppCompatActivity {


    private FloatingActionButton Add;
    private FloatingActionButton sendemail;
    private String emailID;
    private String Number;
    private int Sum;
    private Context context;
    private Integer IDuwanttodelete;
    private Integer UdhariTableID;
    private DatabaseDena db;
    private ListView listView;
    private ArrayList<DataOfDebt> OwedMoneykitnihai = new ArrayList<>();
    private OwedMoneyAdapter OwedMoneyAdapter;
    private View promptsView;
    private static final int request_code=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_of_owed_money);


        context = this;
        listView = (ListView)findViewById(R.id.owedmoneykitnihai);

        sendemail = (FloatingActionButton) findViewById(R.id.sendemailbutton);
        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        Add = (FloatingActionButton) findViewById(R.id.add1);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoOfOwedMoney.this,AddDebt1.class);
                i.putExtra("prefe",UdhariTableID);
                startActivity(i);
                //startActivityForResult(i,request_code);
                // finish();
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            UdhariTableID = extras.getInt("owedtableid1");
            emailID = extras.getString("emailid1");
            Number = extras.getString("number1");
            Number = "+91"+Number;

        }
        refreshData();
    }


    private void refreshData()
    {
        OwedMoneykitnihai.clear();
        db = new DatabaseDena(InfoOfOwedMoney.this);
        ArrayList<DataOfDebt> dena = db.getDataOwed(UdhariTableID);

        for(int i =0;i<dena.size();i++)
        {
            DataOfDebt debt = new DataOfDebt();
            debt.setReasonofdebt(dena.get(i).getReasonofdebt());
            debt.setRadiostringvalue(dena.get(i).getRadiostringvalue());
            debt.setDebt_keyid(dena.get(i).getDebt_keyid());
            debt.setParentid(dena.get(i).getParentid());
            debt.setDebtamount(dena.get(i).getDebtamount());
            debt.setTime(dena.get(i).getTime());

            OwedMoneykitnihai.add(debt);

        }

        db.close();


        OwedMoneyAdapter = new OwedMoneyAdapter(InfoOfOwedMoney.this,R.layout.custom_row_dena1,OwedMoneykitnihai);
        listView.setAdapter(OwedMoneyAdapter);
        OwedMoneyAdapter.notifyDataSetChanged();


    }


    public class OwedMoneyAdapter extends ArrayAdapter<DataOfDebt> {
        Activity activity;
        int layoutResource;
        DataOfDebt debt;
        ArrayList<DataOfDebt> mdata = new ArrayList<>();


        public OwedMoneyAdapter(Activity act, int resource, ArrayList<DataOfDebt> objects) {
            super(act, resource, objects);

            layoutResource = resource;
            activity = act;
            mdata = objects;
            notifyDataSetChanged();

        }

        @Override
        public int getCount() {
            return mdata.size();
        }

        @Override
        public DataOfDebt getItem(int position) {
            return mdata.get(position);
        }

        @Override
        public int getPosition(DataOfDebt item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;

            if (row == null || row.getTag() == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResource, null);

                holder = new ViewHolder();
                holder.OWED = (TextView) row.findViewById(R.id.amount);
                holder.TIME = (TextView) row.findViewById(R.id.time);
                holder.display = (TextView) row.findViewById(R.id.paidornot);
                holder.reason = (TextView) row.findViewById(R.id.reason);
                holder.Updatebtn = (CuboidButton) row.findViewById(R.id.deleterowbtn);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }



            holder.owed = getItem(position);
            holder.OWED.setText("Rs. " +holder.owed.getDebtamount());
            holder.TIME.setText(holder.owed.getTime());
            holder.reason.setText("Reason : "+holder.owed.getReasonofdebt());
            IDuwanttodelete = holder.owed.getDebt_keyid();
            final ViewHolder finalHolder1 = holder;
            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    IDuwanttodelete = finalHolder1.owed.getDebt_keyid();
                    prompts();
                    return true;

                }
            });


            holder.display.setText(holder.owed.getRadiostringvalue());


            final ViewHolder finalHolder = holder;
            holder.Updatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),updateRadioButton1.class);
                    i.putExtra("Debtkeyid", finalHolder.owed.getDebt_keyid());
                    i.putExtra("reason",finalHolder.owed.getReasonofdebt());
                    i.putExtra("debt",finalHolder.owed.getDebtamount());
                    startActivity(i);
                    // finish();
                }
            });


            return row;
        }

        public class ViewHolder {
            DataOfDebt owed;
            TextView OWED;
            TextView TIME;
            TextView display;
            CuboidButton Updatebtn;
            TextView reason;
        }

    }

    public void prompts()
    {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView2 = li.inflate(R.layout.prompts,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView2);



        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                delete();

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    private void delete() {
        db.deleteRowofinnerlist22(IDuwanttodelete);
        refreshData();
    }

    public void sendEmail()
    {
        db=new DatabaseDena(this);
        ArrayList<DataOfDebt> debt = db.getsumofowedmoney(UdhariTableID);
        Integer sum=0;
        for(int i=0;i<debt.size();i++)
        {
            if(debt.get(i).getRadiostringvalue().equals("Not Taken"))
            {
                sum=sum+debt.get(i).getSumofdebt();
            }

        }

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{emailID});
        i.putExtra(Intent.EXTRA_SUBJECT, "Debt");
        i.putExtra(Intent.EXTRA_TEXT   , "The amount that u have to give to me is = " +sum);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(InfoOfOwedMoney.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }


    }



}
