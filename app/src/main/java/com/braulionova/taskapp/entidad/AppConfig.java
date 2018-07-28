package com.braulionova.taskapp.entidad;

public class AppConfig {
    private static AppConfig APP_CONFIG;
    private Usuario usuario;

    public static AppConfig getConfig(){
        if(APP_CONFIG == null)
        {
            APP_CONFIG = new AppConfig();
        }
        return APP_CONFIG;
    }

    private AppConfig(){}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
}
