package com.example.myappgit.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappgit.Adapter.FavoriteAdapter
import com.example.myappgit.Model.FavoriteViewModel
import com.example.myappgit.R
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
    }

    private fun setupRecyclerView() {
        binding.recycle.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
        }
    }
}