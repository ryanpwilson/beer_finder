package com.example.myapplication.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.models.Beer
import com.example.myapplication.utils.NetworkUtils

class BeerListAdapter(val viewModel: MainViewModel, val arrayList: ArrayList<Beer>, val context: Context): RecyclerView.Adapter<BeerListAdapter.BeerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BeerViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return BeerViewHolder(root)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class BeerViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(beer: Beer){
            var name: TextView = item.findViewById(R.id.title)
            name.text = beer.name

            var tagline: TextView = item.findViewById(R.id.tagline)
            tagline.text = beer.tagline

            var ibu_container: LinearLayout = item.findViewById(R.id.ibu_container)
            var ibu: TextView = item.findViewById(R.id.ibu)
            ibu.text = beer.ibu.toString()

            var abv_container: LinearLayout = item.findViewById(R.id.abv_container)
            var abv: TextView = item.findViewById(R.id.abv)
            abv.text = beer.abv.toString()

            var description_container: LinearLayout = item.findViewById(R.id.description_container)
            var description: TextView = item.findViewById(R.id.description)
            description.text = beer.description

            var ingredients_container: LinearLayout = item.findViewById(R.id.ingredients_container)
            var ingredients: TextView = item.findViewById(R.id.ingredients)
            var list = beer.ingredients
            var malts = list.malt
            var hops = list.hops
            var yeast = list.yeast

            var iterator = malts.iterator()

            var display = "Malts: \n "
            iterator.forEach{
                display = display + "${it.name} -  ${it.amount.value} ${it.amount.unit} \n "
            }
            var iter = hops.iterator()

            display = display + "\nHops: \n "
            iter.forEach{
                display = display + "${it.name} -  ${it.amount.value} ${it.amount.unit} - ${it.attribute} \n "
            }

            display = display + "\nYeast:\n ${yeast} "

            ingredients.text = display

            var pairings_container: LinearLayout = item.findViewById(R.id.pairings_container)
            var pairings: TextView = item.findViewById(R.id.pairings)
            var list2 = beer.food_pairing
            var iterator2 = list2.iterator()
            var display2 = String()
            iterator2.forEach {
                display2 = display2 + "$it\n"
            }
            pairings.text = display2

            var NetUtil = NetworkUtils()
            NetUtil.getBitmapFromURL(beer.image_url, item)

            item.setOnClickListener {
                showHide(tagline)
                showHide(ibu_container)
                showHide(abv_container)
                showHide(pairings_container)
                showHide(description_container)
                showHide(ingredients_container)
            }
        }

        fun showHide(view:View) {
            view.visibility = if (view.visibility == View.VISIBLE){
                View.GONE
            } else{
                View.VISIBLE
            }
        }
    }
}