package model.Usuario;

public class Usuario {
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String corDeFundo;
    private String corDeFonte;
    
    public Usuario() {
    }

    public Usuario(String nome, String login, String senha, String corDeFundo, String corDeFonte) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.corDeFundo = corDeFundo;
        this.corDeFonte = corDeFonte;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getCorDeFundo() {
        return corDeFundo;
    }

    public void setCorDeFundo(String corDeFundo) {
        this.corDeFundo = corDeFundo;
    }


    public String getCorDeFonte() {
        return corDeFonte;
    }

    public void setCorDeFonte(String corDeFonte) {
        this.corDeFonte = corDeFonte;
    }
}
