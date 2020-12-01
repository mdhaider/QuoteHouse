package com.nehal.quotehouse.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.nehal.quotehouse.model.Mcq
import com.nehal.quotehouse.repository.PostRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    val postData:MutableLiveData<List<Mcq>> = MutableLiveData()

    /*  way 1 */
    fun getPost(map:MutableMap<String, String>) {
        viewModelScope.launch {
            postRepository.getPost(map)
                .catch { e->
                    Log.d("main", "getPost: ${e.message}")
                }
                .collect {postData1->
                    postData.value=postData1.results
                }
        }
    }

  /*
     way 2

   val postData1:LiveData<List<Post>> = liveData {
        postRepository.getPost()
            .catch {  }
            .collect {postData->
                emit(postData)
            }
    }

   */

   /*
   way 3
    val postData2:LiveData<List<Post>> = postRepository.getPost()
        .catch {  }
        .asLiveData()

    */
}