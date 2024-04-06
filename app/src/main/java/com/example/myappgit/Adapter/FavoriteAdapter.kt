import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myappgit.data.FavoriteUser
import com.example.myappgit.databinding.ItemReviewBinding


class FavoriteAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var favoriteUsers: List<FavoriteUser> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(favoriteUser: FavoriteUser)
    }

    fun submitList(favoriteUsers: List<FavoriteUser>) {
        this.favoriteUsers = favoriteUsers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteUser = favoriteUsers[position]
        holder.bind(favoriteUser)
    }

    override fun getItemCount(): Int {
        return favoriteUsers.size
    }

    inner class ViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val favoriteUser = favoriteUsers[position]
                    listener.onItemClick(favoriteUser)
                }
            }
        }

        fun bind(favoriteUser: FavoriteUser) {
            binding.apply {
                tvItemName.text = favoriteUser.username
                Glide.with(root)
                    .load(favoriteUser.avatarUrl)
                    .into(imgItemPhoto)
            }
        }
    }
}