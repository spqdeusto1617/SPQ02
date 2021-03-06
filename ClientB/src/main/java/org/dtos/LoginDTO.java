package org.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginDTO {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String encryptedPassword;

    
    
    public LoginDTO() {
		super();
	}

	public LoginDTO(String email, String encryptedPassword) {
		super();
		this.email = email;
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
