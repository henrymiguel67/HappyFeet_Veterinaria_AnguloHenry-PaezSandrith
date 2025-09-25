package org.example.model.entities;

import java.util.List;

public abstract class iduenoDAO {
    void  agregarDueno(dueno dueno){

    }

    public abstract List<dueno> listarTodos();

    abstract dueno buscarPorId(Integer id);

    public abstract void  actulizarDueno(dueno dueno);

    abstract void  eliminarDueno(Integer id);
}