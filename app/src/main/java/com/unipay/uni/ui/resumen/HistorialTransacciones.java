package com.unipay.uni.ui.resumen;

import android.app.ProgressDialog;
import android.net.TrafficStats;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unipay.uni.R;
import com.unipay.uni.models.Transaccion;
import com.unipay.uni.ui.adapters.ListaTransaccionesAdapter;
import com.unipay.uni.utilidades.MySingleton;
import com.unipay.uni.utilidades.Settings_VAR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistorialTransacciones extends Fragment {

    ArrayList<String> lista = null;
    ArrayList<Transaccion> listaTransacciones;

    private RecyclerView recyclerView;
    private ListaTransaccionesAdapter transaccionAdapter;

//    RecyclerView historialTransacciones;
//    ArrayList<Transaccion> listaTransacciones;
//
//    ProgressDialog progressDialog;
//    JsonObjectRequest jsonObjectRequest;


    public HistorialTransacciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historial_transacciones, container, false);

        recyclerView = root.findViewById(R.id.rvTransacciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        historialTransacciones = root.findViewById(R.id.rvTransacciones);
//        historialTransacciones.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        historialTransacciones.setHasFixedSize(true);

//        cargarWebService();
        recibirAllTransacciones();
        return root;
    }

//    private void cargarWebService() {
//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Consultando registros, por favor espere!");
//        progressDialog.show();
//
//        String url = Settings_VAR.URL_consultarAllTransaccion;
//
//        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url, null, this, this);
//
//        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
//    }
//
//    @Override
//    public void onResponse(JSONObject response) {
//        Transaccion transaccion = null;
//        JSONArray json = response.optJSONArray("transacciones");
//        try {
//
//            for (int i = 0; i < json.length(); i++) {
//                transaccion = new Transaccion();
//                JSONObject jsonObject = null;
//                jsonObject = json.getJSONObject(i);
//
//                productos.setId_producto(jsonObject.optInt("id_producto"));
//                productos.setNom_producto(jsonObject.optString("nom_producto"));
//                productos.setDes_producto(jsonObject.optString("des_producto"));
//                productos.setStock(jsonObject.optDouble("stock"));
//                productos.setPrecio(jsonObject.optDouble("precio"));
//                productos.setUnidad_de_medida(jsonObject.optString("unidad_de_medida"));
//                productos.setEstado_producto(jsonObject.optInt("estado_producto"));
//                productos.setCategoria(jsonObject.optInt("categoria"));
//                productos.setFecha(jsonObject.optString("fecha_entrada"));
//
//                listaProductos.add(productos);
//            }
//
//            progressDialog.hide();
//            ProductoAdapter adapter = new ProductoAdapter(listaProductos);
//            recyclerViewProductos.setAdapter(adapter);
//
//        }catch (JSONException e){
//            e.printStackTrace();
//            Toast.makeText(getContext(), "Error en la conexion " +
//                    " "+response, Toast.LENGTH_LONG).show();
//            progressDialog.hide();
//        }
//    }
//
//    @Override
//    public void onErrorResponse(VolleyError error) {
//        Toast.makeText(getContext(), "Error de conexion "+error.toString(), Toast.LENGTH_LONG).show();
//        System.out.println();
//        Log.d("Error",error.toString());
//        progressDialog.hide();
//    }


    private void recibirAllTransacciones(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        listaTransacciones = new ArrayList<Transaccion>();
        lista = new ArrayList<String>();
        String urlAll = Settings_VAR.URL_consultarAllTransaccion;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlAll, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray respuestaJSOn = response.getJSONArray("msg");
                    int totalEnct = respuestaJSOn.length();

                    Transaccion objTransaccion = null;
                    for (int i = 0; i < respuestaJSOn.length(); i++){
                        JSONObject transaccion = respuestaJSOn.getJSONObject(i);
                        String phoneNumberFrom = transaccion.getString("phoneNumberFrom");
                        String phoneNumberTo = transaccion.getString("phoneNumberTo");
                        String concept = transaccion.getString("concept");
                        double balance = transaccion.getDouble("balance");
                        String createDate = transaccion.getString("createDate");

                        objTransaccion = new Transaccion(phoneNumberFrom, phoneNumberTo, concept, balance, createDate);

                        listaTransacciones.add(objTransaccion);

                        transaccionAdapter = new ListaTransaccionesAdapter(getContext(), listaTransacciones);

                        recyclerView.setAdapter(transaccionAdapter);

                        Log.i("Id:    ", String.valueOf(objTransaccion.getPhoneNumberFrom()));
                        Log.i("Nombre:    ", String.valueOf(objTransaccion.getPhoneNumberTo()));

                    }

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

}