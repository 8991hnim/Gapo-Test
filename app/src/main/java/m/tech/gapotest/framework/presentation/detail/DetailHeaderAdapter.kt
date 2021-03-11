package m.tech.gapotest.framework.presentation.detail

import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import m.tech.gapotest.R

class DetailHeaderAdapter
constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<DetailHeaderAdapter.BookHeaderHolder>() {

    val list = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHeaderHolder {
        return BookHeaderHolder(parent)
    }

    override fun onBindViewHolder(holder: BookHeaderHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size


    inner class BookHeaderHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_detail_header, parent, false)
                )

        fun bind(s: String) {
            glide.load(s).into(itemView.findViewById(R.id.ivDetail))
        }
    }
}