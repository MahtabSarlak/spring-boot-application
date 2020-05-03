package com.sample.demo.dto;
import javax.validation.constraints.NotBlank;

public class UserDto {

    private Long id;

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

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

    public UserDto(@NotBlank(message = "firstName is required") String firstName, @NotBlank(message = "lastName is required") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto(Long id, @NotBlank(message = "firstName is required") String firstName, @NotBlank(message = "lastName is required") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
