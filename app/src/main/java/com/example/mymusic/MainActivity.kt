package com.example.mymusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mymusic.Fragments.AddFragment
import com.example.mymusic.Fragments.FavoriteFragment
import com.example.mymusic.Fragments.HomeFragment
import com.example.mymusic.Fragments.ProfileFragment
import com.example.mymusic.Fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity(){


    val navBar=BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.home->{
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer,HomeFragment()).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.add->{
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer, AddFragment()).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite->{
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer,FavoriteFragment()).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile->{
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer,ProfileFragment()).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.search->{
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer,SearchFragment()).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(navBar)
        nav_view.selectedItemId = R.id.home

    }



}
