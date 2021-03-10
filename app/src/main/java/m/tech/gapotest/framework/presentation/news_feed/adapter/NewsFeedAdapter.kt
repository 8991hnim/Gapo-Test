package m.tech.gapotest.framework.presentation.news_feed.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import m.tech.gapotest.R
import m.tech.gapotest.business.domain.NewsFeed
import m.tech.gapotest.databinding.ItemContentNormalBinding
import m.tech.gapotest.databinding.ItemContentVideoBinding
import m.tech.gapotest.util.setPreventDoubleClick
import java.text.SimpleDateFormat

const val TYPE_VIDEO = 1
const val TYPE_NORMAL = 0

class NewsFeedAdapter(
    private val glide: RequestManager,
    private val onItemSelected: (position: Int, item: NewsFeed) -> Unit
) : ListAdapter<NewsFeed, RecyclerView.ViewHolder>(NewsFeedCallback()) {

    private class NewsFeedCallback : DiffUtil.ItemCallback<NewsFeed>() {

        override fun areItemsTheSame(oldItem: NewsFeed, newItem: NewsFeed): Boolean {
            return oldItem.baseDocument.documentId == newItem.baseDocument.documentId
        }

        override fun areContentsTheSame(oldItem: NewsFeed, newItem: NewsFeed): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].contentType == NewsFeed.CONTENT_TYPE_VIDEO)
            TYPE_VIDEO
        else
            TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_VIDEO)
            NewsFeedVideoHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_content_video,
                    parent,
                    false
                )
            )
        else
            NewsFeedHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_content_normal,
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsFeedHolder -> {
                holder.bind(currentList[position])
            }
            is NewsFeedVideoHolder -> {
                holder.bind(currentList[position])
            }
        }
    }

    inner class NewsFeedHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemContentNormalBinding.bind(itemView)

        fun bind(item: NewsFeed) = with(itemView) {
            itemView.setPreventDoubleClick {
                onItemSelected(adapterPosition, item)
            }

            binding.tvTitle.text = item.baseDocument.title
            binding.tvPublisherId.text = item.baseDocument.publisher.id
            binding.tvPublisherName.text = item.baseDocument.publisher.name

            item.images?.let {
                try {
                    glide.load(it[0].href).into(binding.ivOne)
                    glide.load(it[1].href).into(binding.ivTwo)
                    glide.load(it[2].href).into(binding.ivThree)
                } catch (e: Exception) {
                    Log.e("AppDebug", "bind: ${e.message}", )
                }
            }
        }
    }

    inner class NewsFeedVideoHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemContentVideoBinding.bind(itemView)

        fun bind(item: NewsFeed) = with(itemView) {
            itemView.setPreventDoubleClick {
                onItemSelected(adapterPosition, item)
            }

            binding.tvPublisherId.text = item.baseDocument.publisher.id
            binding.tvPublisherName.text = item.baseDocument.publisher.name
            item.content?.let {
                glide.load(it.previewImage.href).into(binding.ivPreview)
                binding.tvDuration.text = convertDurationToTime(it.duration)
            }
        }

        private fun convertDurationToTime(millis: Long): String {
            val h = (millis / 1000 / 3600)
            val m = (millis / 1000 / 60 % 60)
            val s = (millis / 1000 % 60)

            val mString = if (m < 10) "0$m" else m.toString()
            val sString = if (s < 10) "0$s" else s.toString()

            return "$h:$mString:$sString"
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads.any { it is InfoMessaged }) {
                when (holder) {
                    is NewsFeedHolder -> {
                        holder.bind(currentList[position])
                    }
                }
            }
        }
    }

    class InfoMessaged

}
