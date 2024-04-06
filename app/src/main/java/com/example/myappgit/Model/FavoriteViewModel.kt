package com.example.myappgit.Model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myappgit.data.FavoriteUser
import com.example.myappgit.data.database.FavoritDatabase
import com.example.myappgit.data.database.UserRepository
import com.example.myappgit.response.UserDetailResponse
import com.example.myappgit.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val userdetail = MutableLiveData<UserDetailResponse>()
    val UserDetail: LiveData<UserDetailResponse> = userdetail
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
