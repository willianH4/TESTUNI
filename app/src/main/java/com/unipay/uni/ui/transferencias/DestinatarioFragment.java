package com.unipay.uni.ui.transferencias;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.*;
import com.unipay.uni.R;
import com.unipay.uni.models.CheckPhoneNumber;
import com.unipay.uni.ui.adapters.ListaContactosAdapter;
import com.unipay.uni.utilidades.MySingleton;
import com.unipay.uni.utilidades.Settings_VAR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DestinatarioFragment extends Fragment {

    CheckPhoneNumber number = new CheckPhoneNumber();
    ArrayList<String> lista = null;
    ArrayList<CheckPhoneNumber> contactosLocales = new ArrayList<CheckPhoneNumber>();
    ArrayList<CheckPhoneNumber> listaContactos;
    final JSONArray array=new JSONArray();
//    final JSONObject array1=new JSONObject();
//    Gson gson = new Gson();
//    String jsonArray = gson.toJson(number);

    private RecyclerView recyclerView;
    private ListaContactosAdapter contactosAdapter;

    public DestinatarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_destinatario, container, false);

        recyclerView = root.findViewById(R.id.rvContactos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        agregarContactosLocales();
        recibirContacts();
        return root;
    }

    public void agregarContactosLocales() {
        // acceso a los contactos
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        Log.i("Numero", String.valueOf(indexNumber));
        Log.i("Nombre", String.valueOf(indexName));
        if(people.moveToFirst()) {
            do {
                String nuevoNumero = people.getString(indexNumber).replace(" ", "-");
                contactosLocales.add(new CheckPhoneNumber(people.getString(indexName), nuevoNumero));
            } while (people.moveToNext());
            Log.i("Contactos leidos: ", String.valueOf(contactosLocales));
        }

        for(int i=0;i<contactosLocales.size();i++){
            JSONObject obj=new JSONObject();
            try {
                obj.put("contactName",contactosLocales.get(i).getContactName());
                obj.put("phoneNumber",contactosLocales.get(i).getPhoneNumber());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(obj);
        }
        Log.i("Nuevo json:::", String.valueOf(array));
    }


    private void recibirContacts(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        Log.i("posicion 1:", String.valueOf(contactosLocales.get(0)));
        Log.i("Data del arreglo", String.valueOf(contactosLocales));

        listaContactos = new ArrayList<CheckPhoneNumber>();
        lista = new ArrayList<String>();
        String urlConsultaUsuarios = Settings_VAR.URL_CheckPhoneNumbers;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlConsultaUsuarios, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray respuestaJSOn = new JSONArray(response);
                    int totalEnct = respuestaJSOn.length();

                    CheckPhoneNumber objContactos = null;
                    for (int i = 0; i < respuestaJSOn.length(); i++){
                        JSONObject contactos = respuestaJSOn.getJSONObject(i);
                        String phoneNumber = contactos.getString("phoneNumber");
                        String contactName = contactos.getString("contactName");

                        objContactos = new CheckPhoneNumber(phoneNumber, contactName);

                        listaContactos.add(objContactos);

                        contactosAdapter = new ListaContactosAdapter(getContext(), listaContactos);

                        recyclerView.setAdapter(contactosAdapter);

                        Log.i("Telefono:    ", String.valueOf(objContactos.getPhoneNumber()));
                        Log.i("Nombre:    ", String.valueOf(objContactos.getContactName()));

                    }

                    Log.i("Respuesta json ",response.toString());

                } catch (JSONException ex){
                    String none = ex.toString();
                    Log.i("NO consulta ***** ", none);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String err = volleyError.toString();
                Log.i("No se pudo **********", err);
            }
        });

        //tiempo de respuesta, establece politica de reintentos
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

//        MySingleton.getInstance(getContext()).addToRequestQueue(request);
        queue.add(request);
    }

    private void compararContactos(){
        /**
         * metodo comparador de arreglos devuelve un arreglo con los datos
         * que no tienen el servicio UNI
         */

    }
}