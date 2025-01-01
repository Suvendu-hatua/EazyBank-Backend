package com.easy_bytes_spring_security.EazyBytes_Spring_Security.config;

import com.easy_bytes_spring_security.EazyBytes_Spring_Security.model.Customer;
import com.easy_bytes_spring_security.EazyBytes_Spring_Security.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class EazyBankUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EazyBankUserDetailsService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer=customerRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("can't find customer with email:"+email));

        log.info(customer.toString());
        Collection<GrantedAuthority> authorities= List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(),customer.getPwd(),authorities);
    }
}
