package org.example.model.entities;

import java.util.List;

public abstract class iduenoDAO {
    void  agregarDueno(dueno dueno);

    public abstract List<dueno> listarTodos();

    List<dueno> buscarPorId(Integer id) throws  Exception{}
}
