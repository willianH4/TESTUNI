package com.unipay.uni.ui.transferencias;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.unipay.uni.R;
import com.unipay.uni.models.CheckPhoneNumber;
import com.unipay.uni.ui.adapters.ListaContactosAdapter;
import com.unipay.uni.utilidades.Settings_VAR;
import com.unipay.uni.volley.JsonArrayRequestCustom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DestinatarioFragment extends Fragment implements SearchView.OnQueryTextListener {

    ArrayList<String> lista = null;
    ArrayList<CheckPhoneNumber> contactosLocales = new ArrayList<CheckPhoneNumber>();
    ArrayList<CheckPhoneNumber> listaContactos;
    ArrayList<CheckPhoneNumber> contactosTotales = new ArrayList<CheckPhoneNumber>();
    ArrayList<CheckPhoneNumber> contactosSerializados = new ArrayList<CheckPhoneNumber>();
    JSONArray array=new JSONArray();

    private ProgressBar progressBar;
    private SearchView svBuscar;
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

        progressBar = root.findViewById(R.id.progress);
        svBuscar = root.findViewById(R.id.svBuscar);
        recyclerView = root.findViewById(R.id.rvContactos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        svBuscar.setOnQueryTextListener(this);

        agregarContactosLocales();
        recibirContactosAPI();
        compararListasContactos();
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

        Log.i("Nombre", String.valueOf(indexName));
        Log.i("Numero", String.valueOf(indexNumber));
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
        Log.i("Contactos en Json", String.valueOf(array));
    }

    private void recibirContactosAPI() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        listaContactos = new ArrayList<CheckPhoneNumber>();
        lista = new ArrayList<String>();
        progressBar.setVisibility(View.VISIBLE);
        String urlConsultaUsuarios = Settings_VAR.URL_CheckPhoneNumbers;
        JsonArrayRequestCustom request = new JsonArrayRequestCustom(
                Request.Method.POST,
                urlConsultaUsuarios,
                array,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    response = response.getJSONObject("response");
                    JSONArray respuestaJSOn = response.getJSONArray("msg");
                    int totalEnct = respuestaJSOn.length();

                    CheckPhoneNumber objContactos = null;
                    for (int i = 0; i < respuestaJSOn.length(); i++) {
                        JSONObject msg = respuestaJSOn.getJSONObject(i);
                        String phoneNumber = msg.getString("phoneNumber");
                        String contactName = msg.getString("contactName");

                        objContactos = new CheckPhoneNumber(contactName, phoneNumber);

                        listaContactos.add(objContactos);

                        contactosAdapter = new ListaContactosAdapter(getContext(), listaContactos);

                        recyclerView.setAdapter(contactosAdapter);

//                        Log.i("Nombre:    ", String.valueOf(objContactos.getContactName()));
//                        Log.i("Telefono:    ", String.valueOf(objContactos.getPhoneNumber()));

                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.i("Respuesta json ", response.toString());

                } catch (JSONException ex) {
                    String none = ex.toString();
                    Log.i("Error tipo ***** ", none);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String err = volleyError.toString();
                Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                Log.i("No se pudo **********", err);
            }
        });

        //tiempo de respuesta, establece politica de reintentos
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }

    private void compararListasContactos(){
//        for (CheckPhoneNumber local : contactosLocales) {
//            for (CheckPhoneNumber api : listaContactos) {
//                if (!local.getPhoneNumber().equalsIgnoreCase(api.getPhoneNumber())) {
//                    contactosSerializados.add(local);
//                }
//            }
//        }
        Log.i("Contactos api", String.valueOf(listaContactos));
        Log.i("Contactos locales", String.valueOf(contactosLocales));
        Log.i("cantidad", String.valueOf(listaContactos.size()));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        contactosAdapter.filtrado(s);
        return false;
    }
}