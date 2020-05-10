package com.sample.demo.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    private Long id;

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "password is required")
    @Size(min =3 , max=8 , message = "password must be between 3 to 8 characters")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserDto() {
    }

    public UserDto(@NotBlank(message = "firstName is required") String firstName, @NotBlank(message = "lastName is required") String lastName, @NotBlank(message = "password is required") @Size(min = 3, max = 8, message = "password must be between 3 to 8 characters") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserDto(Long id, @NotBlank(message = "firstName is required") String firstName, @NotBlank(message = "lastName is required") String lastName, @NotBlank(message = "password is required") @Size(min = 3, max = 8, message = "password must be between 3 to 8 characters") String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
