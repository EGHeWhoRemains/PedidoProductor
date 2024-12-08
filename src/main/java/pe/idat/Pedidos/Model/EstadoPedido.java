package pe.idat.Pedidos.Model;

public enum EstadoPedido {
    Pendiente,
    En_Proceso("En Proceso"),
    Enviado,
    Cancelado;
    private final String displayName;
    EstadoPedido(){
        this.displayName = this.name();
    } 
    EstadoPedido(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }   
}
