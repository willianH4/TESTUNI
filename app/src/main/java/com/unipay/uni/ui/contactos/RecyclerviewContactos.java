//package com.unipay.uni.ui.contactos;
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.CursorLoader;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.provider.ContactsContract;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.unipay.uni.R;
//import com.unipay.uni.models.CheckPhoneNumber;
//import com.unipay.uni.ui.adapters.ListaContactosAdapter;
//import com.unipay.uni.utilidades.MySingleton;
//import com.unipay.uni.utilidades.Settings_VAR;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//// No usada por el momento
//public class RecyclerviewContactos extends Fragment {
//
//    ArrayList<String> lista = null;
//    ArrayList<String> contactosLocales = new ArrayList<>();
//    ArrayList<CheckPhoneNumber> listaContactos;
//
//    private RecyclerView recyclerView;
//    private ListaContactosAdapter contactosAdapter;
//
//    public RecyclerviewContactos() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment
//        View root = inflater.inflate(R.layout.fragment_recyclerview_contactos, container, false);
//
//        recyclerView = root.findViewById(R.id.rvContactos);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        agregarContactosLocales();
////        recibirAllContacts();
//        return root;
//    }
//
//    public void agregarContactosLocales() {
//        // acceso a los contactos
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//                ContactsContract.CommonDataKinds.Phone.NUMBER};
//
//        Cursor people = getActivity().getContentResolver().query(uri, projection, null, null, null);
//
//        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
//        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//
//        if(people.moveToFirst()) {
//            do {
//                contactosLocales.add("contactNumber" + people.getString(indexNumber) + "phoneNumber" + people.getString(indexName));
//            } while (people.moveToNext());
//        }
//    }
//
//    private void recibirAllContacts(){
////        Gson gson = new Gson();
////        String JSON = gson.toJson(contactosLocales);
//        listaContactos = new ArrayList<CheckPhoneNumber>();
//        lista = new ArrayList<String>();
//        String urlConsultaUsuarios = Settings_VAR.URL_CheckPhoneNumbers;
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlConsultaUsuarios, array, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    JSONArray respuestaJSOn = new JSONArray(response);
//                    int totalEnct = respuestaJSOn.length();
//
//                    CheckPhoneNumber objContactos = null;
//                    for (int i = 0; i < respuestaJSOn.length(); i++){
//                        JSONObject contactos = respuestaJSOn.getJSONObject(i);
//                        String phoneNumber = contactos.getString("phoneNumber");
//                        String contactName = contactos.getString("contactName");
//
//                        objContactos = new CheckPhoneNumber(phoneNumber, contactName);
//
//                        listaContactos.add(objContactos);
//
//                        contactosAdapter = new ListaContactosAdapter(getContext(), listaContactos);
//
//                        recyclerView.setAdapter(contactosAdapter);
//
//                        Log.i("Telefono:    ", String.valueOf(objContactos.getPhoneNumber()));
//                        Log.i("Nombre:    ", String.valueOf(objContactos.getContactName()));
//
//                    }
//
//                } catch (JSONException ex){
//                    String none = ex.toString();
//                    Log.i("NO consulta ***** ", none);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                String err = volleyError.toString();
//                Log.i("No se pudo **********", err);
//            }
//        });
//
//        //tiempo de respuesta, establece politica de reintentos
//        request.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        MySingleton.getInstance(getContext()).addToRequestQueue(request);
//    }
//}