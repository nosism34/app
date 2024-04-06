package com.example.myappgit.ui

import FavoriteAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappgit.Model.FavoriteViewModel
import com.example.myappgit.data.FavoriteUser
import com.example.myappgit.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity(), FavoriteAdapter.OnItemClickListener {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private val favoriteAdapter = FavoriteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel.favoriteUsers.observe(this, Observer { favoriteUsers ->
            favoriteAdapter.submitList(favoriteUsers)
        })
    }

    private fun setupRecyclerView() {
        binding.recycle.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
        }
    }

    override fun onItemClick(favoriteUser: FavoriteUser) {
        val intent = Intent(this, DetailUserActivity::class.java).apply {
            putExtra(DetailUserActivity.EXTRA_USERNAME, favoriteUser.username)
            putExtra(DetailUserActivity.EXTRA_FAVORITE_USER, favoriteUser.avatarUrl)
            // Jika perlu, Anda bisa menambahkan data tambahan lainnya
        }
        startActivity(intent)
    }
}
