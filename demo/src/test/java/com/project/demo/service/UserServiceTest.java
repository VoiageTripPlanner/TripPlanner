package com.project.demo.service;

import com.project.demo.entity.*;
import com.project.demo.entity.request.UserRequest;
import com.project.demo.logic.UserService;
import com.project.demo.repository.CountryRepository;
import com.project.demo.repository.CurrencyRepository;
import com.project.demo.repository.RoleRepository;
import com.project.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private UserService userService;

    private UserRequest userRequest;
    private User user;
    private Role role;

    @BeforeEach
    public void setUp() {
        userRequest = new UserRequest();
        userRequest.setId(1);
        userRequest.setName("John");
        userRequest.setLastname("Smith");
        userRequest.setEmail("john@gmail.com");
        userRequest.setPassword("password");
        userRequest.setCountryId("1");
        userRequest.setCurrencyId("1");
        userRequest.setRoleId(1);

        role = new Role();
        role.setRole_id(1);
        role.setRole_name(RoleEnum.USER);
        role.setAbbreviation("aaa");
        role.setOperational(true);
        role.setUpdate_responsible(1);
        role.setCreation_responsible(1);

        user = new User();
        user.setEmail("john@gmail.com");
        user.setPassword("password");
        user.setOperational(true);
        user.setUser_id(1);
        user.setCountry(Mockito.mock(Country.class));
        user.setCurrency(Mockito.mock(Currency.class));
        user.setRole(role);
        user.setCreation_datetime(new Date());
        user.setLast_update_datetime(new Date());
    }

    @Test
    public void UserService_SaveUser_ReturnsUserRequest() {
        Country country = Mockito.mock(Country.class);
        Currency currency = Mockito.mock(Currency.class);
        Role role = Mockito.mock(Role.class);

        Mockito.when(countryRepository.findById(Integer.valueOf(userRequest.getCountryId()))).thenReturn(Optional.ofNullable(country));
        Mockito.when(currencyRepository.findById(Integer.valueOf(userRequest.getCurrencyId()))).thenReturn(Optional.ofNullable(currency));
        Mockito.when(roleRepository.findByName(RoleEnum.USER)).thenReturn(Optional.ofNullable(role));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());

        userService.save(userRequest);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
        Assertions.assertThat(userRequest.getPassword()).isEqualTo(null);
    }

    @Test
    public void UserService_FindAll_ReturnsUserRequestList() {
        List<User> userList = Mockito.mock(List.class);
        Mockito.when(userRepository.findUsersOperationalUsers()).thenReturn(userList);

        List<UserRequest> userRequestList = userService.findAll();

        Assertions.assertThat(userRequestList).isNotNull();
    }

    @Test
    public void UserService_FindById_ReturnsUserRequest() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        Optional<UserRequest> userResponse = userService.findById(1);

        Assertions.assertThat(userResponse).isNotNull();
        Assertions.assertThat(userResponse.get().getId()).isEqualTo(userRequest.getId());
    }

    @Test
    public void UserService_UpdateUser_ReturnsUserRequest() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        Optional<UserRequest> userResponse = userService.findById(1);

        Assertions.assertThat(userResponse).isNotNull();
    }

    @Test
    public void UserService_DeleteUser_ReturnsVoid() {
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        assertAll(() -> userService.deleteById(1));
    }
}
