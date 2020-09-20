package com.example.fusecanteen;

import com.example.fusecanteen.entity.User;
import com.example.fusecanteen.entity.security.Role;
import com.example.fusecanteen.entity.security.UserRole;
import com.example.fusecanteen.enumconstant.AuthorityType;
import com.example.fusecanteen.enumconstant.Privilege;
import com.example.fusecanteen.enumconstant.Status;
import com.example.fusecanteen.repository.RoleRepository;
import com.example.fusecanteen.repository.UserRepository;
import com.example.fusecanteen.repository.UserRoleRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class FusecanteenApplication {

    public static void main(String[] args) {
        SpringApplication.run(FusecanteenApplication.class, args);
    }


    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }


    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {

        return args -> {


            Role superAdminRole = createRole(roleRepository, AuthorityType.ADMIN.name());
            Role employeeRole = createRole(roleRepository, AuthorityType.EMPLOYEE.name());


            User user = userRepository.findByEmail("samyog.acharya@gmail.com");
            if (user == null) {
                User superUser = new User();
                superUser.setEmail("samyog.acharya@gmail.com");
                superUser.setMobile("9804334560");
                superUser.setUsername(superUser.getMobile());
                superUser.setPassword(passwordEncoder().encode("123456"));
                superUser.setCreatedBy("system");
                superUser.setLastModifiedBy("system");
                superUser.setUserType(AuthorityType.ADMIN);
                superUser.setUserType(AuthorityType.EMPLOYEE);
                Set<UserRole> userRoles = new HashSet<>();
                userRoles.add(new UserRole(superUser, superAdminRole));
                userRoles.add(new UserRole(superUser, employeeRole));
                superUser.setUserRoles(userRoles);
                userRepository.save(superUser);
            }
        };
    }


    private Role createRole(RoleRepository roleRepository, String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {

            Set<Privilege> privilegeSet = new HashSet<>();

            privilegeSet.addAll(Arrays.asList(Privilege.values()));

            role = new Role();
            role.setCreatedBy("system");
            role.setLastModifiedBy("system");
            role.setStatus(Status.ACTIVE);
            role.setName(name);
            role.setPermissionSet(privilegeSet);

            return roleRepository.save(role);

        } else if (role.getPermissionSet().size() != Arrays.asList(Privilege.values()).size()) {
            Set<Privilege> privilegeSet = new HashSet<>();

            privilegeSet.addAll(Arrays.asList(Privilege.values()));

            role.setPermissionSet(privilegeSet);

            return roleRepository.save(role);
        }
        return role;
    }


}
