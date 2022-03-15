package com.ide.todoapp.data.datasource.viewmmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.*
import com.ide.todoapp.data.datasource.model.body.PostLogIn
import com.ide.todoapp.data.datasource.model.body.PostRegister
import com.ide.todoapp.data.datasource.model.response.LogoutResponse
import com.ide.todoapp.data.datasource.model.response.PostLogInResponse
import com.ide.todoapp.data.datasource.model.response.PostResponse
import com.ide.todoapp.data.datasource.repo.Repo
import retrofit2.Response
import java.util.concurrent.Executors

class ToDoViewModel(
    application: Application,
    private val repo :Repo):AndroidViewModel(application) {
    var _registerMLData: MutableLiveData<Response<PostResponse>> = MutableLiveData()
    //val registerLData : LiveData<Response<Post>> = _registerMLData
    var _logInMLData: MutableLiveData<Response<PostLogInResponse>> = MutableLiveData()
    var _logoutMLData: MutableLiveData<Response<LogoutResponse>> = MutableLiveData()

    fun postRegister (postRegister: PostRegister,context:Context) {
        if (hasInternetConnection()) {
            val executors = Executors.newSingleThreadExecutor()
            executors.execute {
                val result: Response<PostResponse> = repo.postRegister(postRegister)
                _registerMLData.postValue(result)
            }
        }else{
            Toast.makeText(context,"Check your internet Connection!",Toast.LENGTH_SHORT).show()
        }
    }
    fun postLogIn (postLogIn: PostLogIn,context: Context){
        if (hasInternetConnection()) {
            val executors = Executors.newSingleThreadExecutor()
            executors.execute {
                val result: Response<PostLogInResponse> = repo.postLogIn(postLogIn)
                _logInMLData.postValue(result)
            }
        }else{
            Toast.makeText(context,"Check your internet Connection!",Toast.LENGTH_SHORT).show()
        }
    }
    fun logout(context: Context,token:String){
        if (hasInternetConnection()) {
            val executors = Executors.newSingleThreadExecutor()
            executors.execute {
                val result: Response<LogoutResponse> = repo.logout(token)
                _logoutMLData.postValue(result)
            }
        }else{
            Toast.makeText(context,"Check your internet Connection!",Toast.LENGTH_SHORT).show()
        }
    }




     fun hasInternetConnection():Boolean{
        val connectivityManeger = getApplication<ToDoAplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            val activeNetwork =connectivityManeger.activeNetwork ?: return false
            val capabilities= connectivityManeger.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else{
            connectivityManeger.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI->true
                    TYPE_MOBILE->true
                    TYPE_ETHERNET->true
                    else ->false
                }
            }
        }
        return false
    }
}

class MyViewModelFactory (val app :Application,private val repo: Repo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ToDoViewModel::class.java!!)) {
            ToDoViewModel(app,repo) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
class ToDoAplication :Application() {
}