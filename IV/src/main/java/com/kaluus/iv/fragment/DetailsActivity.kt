package com.kaluus.iv.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class DetailsActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish()
            return
        }

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            val details = DetailsFragment().apply {
                arguments = intent.extras
            }
            supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, details)
                    .commit()
        }
    }
}