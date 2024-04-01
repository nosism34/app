package com.example.myappgit.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myappgit.data.FavoriteUser
import com.example.myappgit.databinding.ItemReviewBinding

class FavoriteAdapter :
    ListAdapter<FavoriteUser, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class FavoriteViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: FavoriteUser) {
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .into(imgItemPhoto)
                tvItemName.text = user.username
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteUser>() {
            override fun areItemsTheSame(oldItem: FavoriteUser, newItem: FavoriteUser): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteUser,
                newItem: FavoriteUser
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
