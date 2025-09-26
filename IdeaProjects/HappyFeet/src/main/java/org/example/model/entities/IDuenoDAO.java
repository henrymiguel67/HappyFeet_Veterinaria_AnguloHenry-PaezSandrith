package org.example.model.entities;

import java.util.List;

public abstract class IDuenoDAO {
    public  abstract void  agregarDueno(Dueno dueno);

    public abstract List<Dueno> listarTodos();

    abstract Dueno buscarPorId(Integer id);

    public abstract void actulizarDueno(Dueno dueno);

    abstract void  eliminarDueno(Integer id);
}