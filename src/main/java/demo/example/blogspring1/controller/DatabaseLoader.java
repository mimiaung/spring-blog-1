package demo.example.blogspring1.controller;

import demo.example.blogspring1.model.Role;
import demo.example.blogspring1.model.User;
import demo.example.blogspring1.repository.RoleRepository;
import demo.example.blogspring1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public DatabaseLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin=new Role("ROLE_ADMIN");
        Role user=new Role("ROLE_USER");
        String secretAdminUser=bCryptPasswordEncoder.encode("kyaw");

        User adminUser=new User("kyaw","admin",secretAdminUser,"adminmail@gmail.com");
        String secretUser=bCryptPasswordEncoder.encode("thaw");
        User userUser=new User("thaw","user",secretUser,"usermail@gmail.com");

        adminUser.addRole(admin);

        admin.getUsers().add(adminUser);

        userUser.addRole(user);
        user.getUsers().add(userUser);

//        roleRepository.save(admin);
//        roleRepository.save(user);
//        userRepository.save(adminUser);
//        userRepository.save(userUser);




    }
}
