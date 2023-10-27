package lihan.chen.wordpressahan.feature.expandablelist

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class User(
    val id : String,
    val name : String,
    val gender : String
)

data class UserGroup(
    val lastName : String,
    val userList : List<User>
){
    companion object{
        fun getAllUserDataFromJsonData() : Map<Char, List<User>> {
            val gson = Gson()
            val itemType = object : TypeToken<List<User>>(){}.type
            val items : List<User> = gson.fromJson<List<User>?>(users , itemType).sortedBy{ it.name.first() }
            val groupList = items.groupBy {
                it.name.first()
            }
            return groupList
        }

    }
}

