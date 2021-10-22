package com.unipay.uni.ui.resumen;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.unipay.uni.R;
import com.unipay.uni.models.Transaccion;
import com.unipay.uni.ui.adapters.ListaTransaccionesAdapter;
import com.unipay.uni.utilidades.Settings_VAR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TodasTransacciones extends Fragment {
    ArrayList<String> lista = null;
    ArrayList<Transaccion> listaTransacciones;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ListaTransaccionesAdapter transaccionAdapter;

    public TodasTransacciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_todas_transacciones, container, false);

        progressBar = root.findViewById(R.id.progress);
        recyclerView = root.findViewById(R.id.rvTransacciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recibirAllTransacciones();
        return root;
    }

    private void recibirAllTransacciones(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        listaTransacciones = new ArrayList<Transaccion>();
        lista = new ArrayList<String>();
        String urlAll = Settings_VAR.URL_consultarAllTransaccion;
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlAll, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    JSONArray respuestaJSOn = new JSONArray(response);
                    response = response.getJSONObject("response");
                    JSONArray respuestaJSOn = response.getJSONArray("msg");
                    int totalEnct = respuestaJSOn.length();

                    Transaccion objTransaccion = null;
                    for (int i = 0; i < respuestaJSOn.length(); i++){
                        JSONObject msg = respuestaJSOn.getJSONObject(i);
                        String phoneNumberFrom = msg.getString("phoneNumberFrom");
                        String phoneNumberTo = msg.getString("phoneNumberTo");
                        String concept = msg.getString("concept");
                        String balance = msg.getString("balance");
                        String createDate = msg.getString("createDate");

                        objTransaccion = new Transaccion(phoneNumberFrom, phoneNumberTo, concept, balance, createDate);

                        listaTransacciones.add(objTransaccion);

                        transaccionAdapter = new ListaTransaccionesAdapter(getContext(), listaTransacciones);

                        recyclerView.setAdapter(transaccionAdapter);

//                        Log.i("Origen:    ", String.valueOf(objTransaccion.getPhoneNumberFrom()));
//                        Log.i("Destino:    ", String.valueOf(objTransaccion.getPhoneNumberTo()));
//                        Log.i("Concepto:    ", String.valueOf(objTransaccion.getConcept()));
//                        Log.i("Balance:    ", String.valueOf(objTransaccion.getBalance()));
//                        Log.i("Fecha:    ", String.valueOf(objTransaccion.getCreateDate()));
                    }
                    progressBar.setVisibility(View.INVISIBLE);

                } catch (JSONException ex){
                    String none = ex.toString();
                    Log.i("NO consulta ***** ", none);
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

}