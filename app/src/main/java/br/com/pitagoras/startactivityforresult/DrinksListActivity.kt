package br.com.pitagoras.startactivityforresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DrinksListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this)
        setContentView(listView)

        val drinksList = resources.getStringArray(R.array.drinks)
        val listAdapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_single_choice,
                drinksList
            )

        listView.adapter = listAdapter
        listView.choiceMode = AbsListView.CHOICE_MODE_SINGLE

        val drink = intent.getStringExtra(EXTRA_DRINK)

        if (drink != null) {
            val position = drinksList.indexOf(drink)
            listView.setItemChecked(position, true)
        }

        listView.setOnItemClickListener { parent, _, position, _ ->
            val result = parent.getItemAtPosition(position) as String
            val it = Intent()
            it.putExtra(EXTRA_RESULT, result)
            setResult(Activity.RESULT_OK, it)
            finish()
        }
    }

    companion object {
        const val EXTRA_DRINK = "my_drink"
        const val EXTRA_RESULT = "my_result"
    }
}