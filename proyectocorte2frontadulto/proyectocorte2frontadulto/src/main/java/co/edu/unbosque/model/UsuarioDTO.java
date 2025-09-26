package co.edu.unbosque.model;

import java.util.Objects;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private String nombreCompleto;
    private String email;
    private String fotoPerfil; 
    
    
    public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}
    
    public UsuarioDTO(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
    }

    
	@Override
	public int hashCode() {
		return Objects.hash(email, fotoPerfil, id, nombreCompleto, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(fotoPerfil, other.fotoPerfil)
				&& Objects.equals(id, other.id) && Objects.equals(nombreCompleto, other.nombreCompleto)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
    
    
}
