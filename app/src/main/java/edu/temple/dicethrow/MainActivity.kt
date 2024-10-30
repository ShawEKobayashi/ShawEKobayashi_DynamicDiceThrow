package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    lateinit var buttonFragment: ButtonFragment
    lateinit var dieFragment: DieFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonFragment = ButtonFragment()
        dieFragment = DieFragment()

        supportFragmentManager.beginTransaction().add(R.id.container1, buttonFragment).commit()

        if (resources.configuration.orientation== Configuration.ORIENTATION_LANDSCAPE){
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container2, dieFragment)
                commit()
            }
        }
    }

    /* TODO 2: switch fragments if portrait (no need to switch fragments if Landscape)
        */
    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked() {
        if (resources.configuration.orientation== Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container1, dieFragment)
                addToBackStack("switchedFragments")
                commit()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

}