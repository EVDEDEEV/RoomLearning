package my.project.roomlearning.data.repository

import androidx.lifecycle.LiveData
import my.project.roomlearning.data.dataBase.UserDao
import my.project.roomlearning.data.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}