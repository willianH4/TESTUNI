package com.unipay.uni.ui.contactos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.unipay.uni.R;
import com.unipay.uni.models.CheckPhoneNumber;
import com.unipay.uni.ui.adapters.ListaContactosAdapter;
import com.unipay.uni.utilidades.MySingleton;
import com.unipay.uni.utilidades.Settings_VAR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerviewContactos extends Fragment {

    ArrayList<String> lista = null;
    ArrayList<CheckPhoneNumber> listaContactos;

    private RecyclerView recyclerView;
    private ListaContactosAdapter contactosAdapter;

    public RecyclerviewContactos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_recyclerview_contactos, container, false);

        recyclerView = root.findViewById(R.id.rvContactos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recibirAllUsers();
        return root;
    }

    private void recibirAllUsers() {
        // Aqui la peticion para traer los contactos
        listaContactos = new ArrayList<CheckPhoneNumber>();
        lista = new ArrayList<String>();
        String urlConsultaContactos = Settings_VAR.URL_CheckPhoneNumbers;
        StringRequest request = new StringRequest(Request.Method.POST, urlConsultaContactos, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray respuestaJSOn = new JSONArray(response);
                    int totalEnct = respuestaJSOn.length();

                    CheckPhoneNumber objContacto = null;
                    for (int i = 0; i < respuestaJSOn.length(); i++){
                        JSONObject users = respuestaJSOn.getJSONObject(i);
                        int id = users.getInt("id");
                        String nombre = users.getString("nombre");
                        String telefono = users.getString("telefono");

                        objContacto = new CheckPhoneNumber(nombre, telefono);

                        listaContactos.add(objContacto);

                        contactosAdapter = new ListaContactosAdapter(getContext(), listaContactos);

                        recyclerView.setAdapter(contactosAdapter);

                        Log.i("Telefono:    ", String.valueOf(objContacto.getTelefono()));
                        Log.i("Nombre:    ", String.valueOf(objContacto.getNombre()));

                    }

                } catch (JSONException ex){
                    String none = ex.toString();
                    Log.i("Error en la consulta **", none);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String err = volleyError.toString();
                Log.i("Error en el proceso **", err);
            }
        });

        //tiempo de respuesta, establece politica de reintentos
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }
}