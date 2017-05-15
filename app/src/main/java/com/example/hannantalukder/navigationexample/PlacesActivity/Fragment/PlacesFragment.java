package com.example.hannantalukder.navigationexample.PlacesActivity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hannantalukder.navigationexample.Map_Fragments.MapsFragment;
import com.example.hannantalukder.navigationexample.MapsActivity;
import com.example.hannantalukder.navigationexample.PlacesActivity.Adapter.PlacesAdapter;
import com.example.hannantalukder.navigationexample.PlacesActivity.Interface.NearByPlacesNameInterface;
import com.example.hannantalukder.navigationexample.PlacesActivity.Interface.NearByPlacesServiceApi;
import com.example.hannantalukder.navigationexample.PlacesActivity.ModelClass.NearByPlacesClass;
import com.example.hannantalukder.navigationexample.PlacesActivity.ModelClass.PlacesName;
import com.example.hannantalukder.navigationexample.PlacesActivity.NearByActivity;
import com.example.hannantalukder.navigationexample.R;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.NearByInterface;
import com.example.hannantalukder.navigationexample.WeatherActivity.Weather_Activity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.hannantalukder.navigationexample.PlacesActivity.NearByActivity.queryAreaPlaces;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment {


    private static final String BASE_ULR = "https://maps.googleapis.com/maps/api/place/";

    private String urlString;
    private NearByPlacesServiceApi mNearByPlacesServiceApi;
    private NearByPlacesClass mNearByPlacesClass;
    private ArrayList<PlacesName> placesNameList;

    private NearByInterface nearByInterface;
    private NearByPlacesNameInterface mNearByPlacesNameInterface;
    private View mView;
    private String address;
    private String addressPlacesName;
    private ListView mPlacesList;

    public PlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_places, container, false);

        mPlacesList = (ListView) mView.findViewById(R.id.placesList);

        nearByInterface = new Weather_Activity();
        address = nearByInterface.getLocationPosition();
        mNearByPlacesNameInterface = new NearByActivity();
        addressPlacesName = mNearByPlacesNameInterface.getQueryPlacesName();
        Toast.makeText(getContext(), "" + address + "\n" + addressPlacesName, Toast.LENGTH_SHORT).show();

        placesNameList = new ArrayList<>();
        urlString = String.format("textsearch/json?query=" + address + addressPlacesName + "&location=" + address + "&radius=10000&key= AIzaSyCBQTHrzG9fl-ZO_Mwnxn_uqOf4WqN210o");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_ULR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mNearByPlacesServiceApi = retrofit.create(NearByPlacesServiceApi.class);
        final Call<NearByPlacesClass> mNearByPlacesClassCall = mNearByPlacesServiceApi.getNearByPlacesClassCall(urlString);

        mNearByPlacesClassCall.enqueue(new Callback<NearByPlacesClass>() {
            @Override
            public void onResponse(Response<NearByPlacesClass> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    mNearByPlacesClass = response.body();
                    List<NearByPlacesClass.Result> placesList = mNearByPlacesClass.getResults();
                    for (NearByPlacesClass.Result items : placesList) {
                        String name = items.getName();
                        String address = items.getFormattedAddress();
                        Double lat = items.getGeometry().getLocation().getLat();
                        Double lon = items.getGeometry().getLocation().getLng();

                        placesNameList.add(new PlacesName(name, address, lat, lon));
                    }
                    updateListView();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        return mView;
    }

    private void updateListView() {
        if (placesNameList.size() > 0) {
            PlacesAdapter adapter = new PlacesAdapter(getContext(), placesNameList);
            mPlacesList.setAdapter(adapter);
            mPlacesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Double latt = placesNameList.get(position).getLat();
                    Double lnn = placesNameList.get(position).getLon();
                    String name = placesNameList.get(position).getName();
                    Toast.makeText(getContext(), "" + latt + lnn, Toast.LENGTH_SHORT).show();
                    NearByMapsFragment mapsFragment = new NearByMapsFragment();
                    Bundle args = new Bundle();
                    args.putDouble("lt",latt);
                    args.putDouble("ln",lnn);
                    args.putString("name",name);
                    mapsFragment.setArguments(args);
                    /*getFragmentManager().beginTransaction().replace(R.id.map_frag_Container,mapsFragment).commit();*/
                    getFragmentManager().beginTransaction().replace(R.id.map_frag_Container,mapsFragment).commit();
                }
            });
        }
    }
}


