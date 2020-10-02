package br.com.pitagoras.startactivityforresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var drink: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_send.setOnClickListener {
            val intent = Intent(this, DrinksListActivity::class.java)
            intent.putExtra(DrinksListActivity.EXTRA_DRINK, drink)

            startActivityForResult(intent, REQUEST_STATE)
        }

        if (savedInstanceState != null) {
            drink = savedInstanceState.getString(EXTRA_DRINK)
            if (drink != null) {
                button_send.text = drink
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_STATE) {
            drink = data?.getStringExtra(DrinksListActivity.EXTRA_RESULT)
            button_send.text = drink
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_DRINK, drink)
    }

    companion object {
        private const val REQUEST_STATE = 1
        private const val EXTRA_DRINK = "my_drink"
    }
}