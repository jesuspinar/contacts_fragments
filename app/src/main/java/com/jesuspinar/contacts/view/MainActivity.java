package com.jesuspinar.contacts.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.jesuspinar.contacts.controller.ContactParser;
import com.jesuspinar.contacts.controller.IOnClickListener;
import com.jesuspinar.contacts.fragments.FragmentDetail;
import com.jesuspinar.contacts.fragments.FragmentList;
import com.jesuspinar.contacts.model.Contact;
import com.jesuspinar.fragments.R;

public class MainActivity extends AppCompatActivity implements FragmentList.IOnAttachListener, IOnClickListener, FragmentDetail.IOnAttachListener {
    private final static String CONTACTS_KEY = "com.jesuspinar.fragments.contacts";
    private final static String SELECTED_CONTACT_KEY = "com.jesuspinar.fragments.selectedcontact";

    private Contact[] contacts;
    private Contact selectedContact;

    private FragmentDetail fragmentDetalle;
    private boolean tablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(savedInstanceState != null) {
            contacts = (Contact[]) savedInstanceState.getSerializable(CONTACTS_KEY);
            selectedContact = (Contact) savedInstanceState.getSerializable(SELECTED_CONTACT_KEY);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablet = findViewById(R.id.frgDetalle) != null;

        if (tablet) {
            FragmentManager manager = getSupportFragmentManager();
            fragmentDetalle = (FragmentDetail) manager.findFragmentById(R.id.frgDetalle);
        }
    }

    private void loadData(){
        ContactParser parser = new ContactParser(this);
        if (parser.parse()) {
            contacts = parser.getContactos();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(CONTACTS_KEY, contacts);
        outState.putSerializable(SELECTED_CONTACT_KEY, selectedContact);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(int position) {
        selectedContact = contacts[position];
        if(tablet) {
            //TODO : select first
            setTitle(selectedContact.getName());
            fragmentDetalle.displayDetail(selectedContact);
        } else {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.frgListado, FragmentDetail.class, null)
                    .commit();
        }
    }

    @Override
    public Contact[] getContacts(){
        if (contacts == null) {
            loadData();
        }
        return contacts;
    }

    @Override
   public Contact getContact(){
       if (contacts == null) {
           loadData();
       }
       if (selectedContact == null) {
           selectedContact = contacts[0];
       }
       Contact c = selectedContact;
       setTitle(c.getName() + c.getFirstSurname());
       return c ;
   }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setTitle(R.string.app_name);
    }

}
