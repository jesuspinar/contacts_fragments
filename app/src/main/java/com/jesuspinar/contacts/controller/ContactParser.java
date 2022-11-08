package com.jesuspinar.contacts.controller;

import android.content.Context;
import android.util.Log;

import com.jesuspinar.contacts.model.Contact;
import com.jesuspinar.fragments.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

/**
 * Created by ggascon
 */
public class ContactParser {
    /** Array que contendrá los objetos Contacto */
    private Contact[] contactos;
    /** InputStream para poder leer del archivo contacts.json */
    private final InputStream contactsFile;

    /** Al constructor le pasamos el contexto para que pueda tener acceso a los recursos de la aplicación */
    public ContactParser(Context c) {
        /* Obtenemos una referencia al archivo /res/raw/contact.json */
        this.contactsFile = c.getResources().openRawResource(R.raw.contacts);
    }

    /**
     * Obtiene los datos de los países desde un archivo xml mediante DOM,
     * y los carga en el array countries.
     * @return boolean Devuelve verdadero si ha ido bien. False en caso contrario.
     */
    public boolean parse() {
        /* Parsed controla si se han podido parsear los datos. Inicialmente a false */
        boolean parsed = false;
        String json = null;
        /* Inicializamos a null el array de contactos */
        contactos = null;
        try {
            int size = contactsFile.available();
            byte[] buffer = new byte[size];
            contactsFile.read(buffer);
            contactsFile.close();
            json = new String(buffer, "UTF-8");
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);
            /* Inicializamos el array con el tamaño del array en el JSON */
            contactos = new Contact[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String firstSurname = jsonObject.getString("firstSurname");
                String secondSurname = jsonObject.getString("secondSurname");
                String photo = jsonObject.getString("photo");
                String birth = jsonObject.getString("birth");
                String company = jsonObject.getString("company");
                String email = jsonObject.getString("email");
                String phone1 = jsonObject.getString("phone1");
                String phone2 = jsonObject.getString("phone2");
                String address = jsonObject.getString("address");
                contactos[i] = new Contact(id, name, firstSurname, secondSurname, photo, birth, company, email, phone1, phone2, address);
            }

            /* Si hemos llegado hasta aquí, podemos asegurar que el documento json ha sido parseado correctamente */
            parsed = true;
        } catch (Exception e) {
            Log.e("CountryParser", "Unknown Exception: "+e.getLocalizedMessage());
        }
        return parsed;
    }

    /**
     * Devuelve la lista de contactos
     * @return Contacto[]
     */
    public Contact[] getContactos() {
        return this.contactos;
    }


}
