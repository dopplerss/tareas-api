package org.example.tareasapi;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private String mensaje;
    private int codigo;
    private String error;
    private LocalDateTime timestamp;
    private List<String> detalles;

    public ErrorResponse(String mensaje, int codigo, String error, List<String> detalles) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.error = error;
        this.detalles = detalles;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public List<String> getDetalles() { return detalles; }
    public void setDetalles(List<String> detalles) { this.detalles = detalles; }
}
