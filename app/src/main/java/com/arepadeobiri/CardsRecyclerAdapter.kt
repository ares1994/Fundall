package com.arepadeobiri

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arepadeobiri.fundall.R

class CardsRecyclerAdapter(private val categories: List<String>) :
    RecyclerView.Adapter<CardsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_layout, parent, false)

        view.setOnFocusChangeListener { root, b ->
            Log.d("Ares", "focus listener called")

        }


        return ViewHolder(view)

    }

    override fun getItemCount(): Int = categories.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.category.text = categories[position]
        holder.mainImageView.setOnClickListener {
            Log.d("Ares", "view clicked")
            holder.mainImageView.requestFocus()
            Log.d("Ares","${holder.mainImageView.hasFocus()}")
        }
        holder.mainImageView.setOnFocusChangeListener { view, b ->
            if (b) {
                val anim = AnimationUtils.loadAnimation(view.context, R.anim.scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
            } else{
                val anim = AnimationUtils.loadAnimation(view.context, R.anim.scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category: TextView = itemView.findViewById(R.id.cardTextView)
        val mainImageView : ConstraintLayout = itemView.findViewById(R.id.backgroundImageView)
    }
}