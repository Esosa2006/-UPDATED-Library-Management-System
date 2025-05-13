package com.LBS.Library.Management.System.repositories;

import com.LBS.Library.Management.System.enitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByemail(String email);
    User findByuniqueID(String uniqueID);
}
