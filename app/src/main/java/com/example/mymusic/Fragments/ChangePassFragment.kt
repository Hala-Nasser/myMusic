package com.example.mymusic.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymusic.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_change_pass.*
import kotlinx.android.synthetic.main.fragment_change_pass.view.*

/**
 * A simple [Fragment] subclass.
 */
class ChangePassFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val sharedPref=activity!!.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val getPass=sharedPref.getString("password","")
        val editor=sharedPref.edit()

        val root=LayoutInflater.from(activity).inflate(R.layout.fragment_change_pass,null,false)

        root.pass_check.setOnClickListener {
            var currentPass=current_pass.text.toString()
            var newPass=new_pass.text.toString()
            var confirmNewPass =confirm_new_pass.text.toString()

            if(currentPass==getPass && newPass==confirmNewPass) {
                editor.putString("password", newPass)
                editor.commit()
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, EditProfileFragment()).commit()
            }
            else
                Snackbar.make(view!!, "Make sure from the bove requierments", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        root.pass_cancel.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, EditProfileFragment()).commit()
        }

        return root
    }


}
