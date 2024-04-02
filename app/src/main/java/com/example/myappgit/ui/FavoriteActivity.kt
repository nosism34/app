package com.example.myappgit.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappgit.Adapter.FavoriteAdapter
import com.example.myappgit.Model.FavoriteViewModel
import com.example.myappgit.data.FavoriteUser
import com.example.myappgit.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private val favoriteAdapter = FavoriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel.favoriteUsers.observe(this, Observer { favoriteUsers ->
            favoriteAdapter.submitList(favoriteUsers)
        })

        favoriteAdapter.setOnItemClickListener(object : FavoriteAdapter.OnItemClickListener {
            override fun onItemClicked(favoriteUser: FavoriteUser) {
                // Start DetailUserActivity with the favorite user object
                val intent = Intent(this@FavoriteActivity, DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_FAVORITE_USER, favoriteUser)
                }
                startActivity(intent)
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recycle.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
        }
    }
}
