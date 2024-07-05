package com.example.zajecia5

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Kontakt : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val Zgierz = LatLng(51.85778601245475, 19.414783516316767)
        googleMap.addMarker(MarkerOptions().position(Zgierz).title("Kimsu Zgierz"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zgierz,10f))

        val Pabianice = LatLng(51.66114954743302, 19.356873990780826)
        googleMap.addMarker(MarkerOptions().position(Pabianice).title("Kimsu Pabianice"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Pabianice,10f))

        val Lodz = LatLng(51.761031299430044, 19.459023662507445)
        googleMap.addMarker(MarkerOptions().position(Lodz).title("Kimsu Łódź"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lodz,10f))



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kontakt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}