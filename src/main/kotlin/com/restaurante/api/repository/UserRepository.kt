package com.restaurante.api.repository

import com.restaurante.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID>{

}