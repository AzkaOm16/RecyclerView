package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var title = "Mode List"
    private val list = ArrayList<Kopi>()
    private var mode: Int = 0
    companion object {
        private const val STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_coffee.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            list.addAll(getListHeroes())
            showRecyclerList()
            mode = R.id.action_list
        } else {
            title = savedInstanceState.getString(STATE_TITLE).toString()
            val stateList = savedInstanceState.getParcelableArrayList<Kopi>(STATE_LIST)
            val stateMode = savedInstanceState.getInt(STATE_MODE)
            setActionBarTitle(title)
            if (stateList != null) {
                list.addAll(stateList)
            }
            setMode(stateMode)
        }
    }

    fun getListHeroes(): ArrayList<Kopi> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listKopi = ArrayList<Kopi>()
        for (position in dataName.indices) {
            val kopi = Kopi(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listKopi.add(kopi)
        }
        return listKopi
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }

    private fun showRecyclerList() {
        rv_coffee.layoutManager = LinearLayoutManager(this)
        val listKopiAdapter = ListKopiAdapter(list)
        rv_coffee.adapter = listKopiAdapter

        listKopiAdapter.setOnItemClickCallback(object : ListKopiAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Kopi) {
                showSelectedHero(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rv_coffee.layoutManager = GridLayoutManager(this, 2)
        val gridKopiAdapter = GridKopiAdapter(list)
        rv_coffee.adapter = gridKopiAdapter

        gridKopiAdapter.setOnItemClickCallback(object : GridKopiAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Kopi) {
                showSelectedHero(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rv_coffee.layoutManager = LinearLayoutManager(this)
        val cardViewKopiAdapter = CardViewKopiAdapter(list)
        rv_coffee.adapter = cardViewKopiAdapter
    }

    private fun showSelectedHero(kopi: Kopi) {
        Toast.makeText(this, "Kamu memilih ${kopi.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }


    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        mode = selectedMode
        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}