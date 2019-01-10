package com.example.testdemo

import com.demo.jwt.service.impl.UserDetailsServiceAdapter
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Mapper
interface AuthMapper {
    @Select("select password from auth where username=#{username}")
    fun getPasswordByUsername(@Param("username") username: String): String?

    @Insert("insert into auth(username, password) values(#{username}, #{password})")
    fun saveUser(@Param("username") username: String, @Param("password") password: String)
}

@Service
@Primary
class UserDetailsServiceImpl : UserDetailsServiceAdapter() {
    @Autowired
    lateinit var authMapper: AuthMapper

    companion object {
        val userData = mutableMapOf(
                "fat" to BCryptPasswordEncoder().encode("pss")
        )
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val password =
//                userData[username]
                authMapper.getPasswordByUsername(username)
                        ?: throw UsernameNotFoundException(username)
        return User(
                username,
                password,
                emptyList()
        )
    }

    override fun saveUser(username: String, password: String) {
        authMapper.saveUser(username, BCryptPasswordEncoder().encode(password))
    }
}

