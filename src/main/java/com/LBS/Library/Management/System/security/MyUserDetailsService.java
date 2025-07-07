package com.LBS.Library.Management.System.security;

import com.LBS.Library.Management.System.enitites.Librarian;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.repositories.LibrarianRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final LibrarianRepository librarianRepository;

    public MyUserDetailsService(UserRepository userRepository, LibrarianRepository librarianRepository) {
        this.userRepository = userRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByemail(email);
        if (user != null){
            return new UserPrincipal(user.getEmail(), user.getPassword(), user.getRole());
        }
        Librarian librarian = librarianRepository.findByEmail(email);
        if (librarian != null){
            return new UserPrincipal(librarian.getEmail(), librarian.getPassword(), librarian.getRole());
        }
        throw new UsernameNotFoundException("Username not found exception!");
    }
}
