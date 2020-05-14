package entities;

public class VideoClub {

    private int Id;
    private float importePorDia;
    private int plazoMaxADevolver;
    private int cantMaxPelPendientes;

    public float getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public float getImportePorDia() {
        return importePorDia;
    }

    public void setImportePorDia(float importePorDia) {
        this.importePorDia = importePorDia;
    }

    public int getPlazoMaxADevolver() {
        return plazoMaxADevolver;
    }

    public void setPlazoMaxADevolver(int plazoMaxADevolver) {
        this.plazoMaxADevolver = plazoMaxADevolver;
    }

    public int getCantMaxPelPendientes() {
        return cantMaxPelPendientes;
    }

    public void setCantMaxPelPendientes(int cantMaxPelPendientes) {
        this.cantMaxPelPendientes = cantMaxPelPendientes;
    }

}
