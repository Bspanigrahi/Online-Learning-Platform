package com.api.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email) ;
	
    //void deleteByCourseId(Long courseId);
    
    @Modifying
    @Query("DELETE FROM User u WHERE u.course.id = :courseId")
    void deleteByCourseId(@Param("courseId") Long courseId);

	
    @Query("SELECT u FROM User u WHERE u.removed = false")
    List<User> findAllNonRemovedUsers();
}
