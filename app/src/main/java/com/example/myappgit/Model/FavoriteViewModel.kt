package com.example.myappgit.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myappgit.data.FavoriteUser
import com.example.myappgit.data.database.FavoritDatabase
import com.example.myappgit.data.database.UserRepository
import kotlinx.coroutines.launch
class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    val favoriteUsers: LiveData<List<FavoriteUser>>

    init {
        val userDao = FavoritDatabase.getDatabase(application).favoriteUserDao()
        repository = UserRepository.getInstance(userDao)
        favoriteUsers = repository.getFavoriteUsers()
    }

    fun addFavoriteUser(user: FavoriteUser) {
        viewModelScope.launch {
            repository.addFavoriteUser(user)
        }
    }

    fun removeFavoriteUser(userId: Int) {
        viewModelScope.launch {
            repository.removeFavoriteUser(userId)
        }
    }

    fun isFavorite(userId: Int): Boolean {
        return favoriteUsers.value?.any { it.id == userId } ?: false
    }
}
