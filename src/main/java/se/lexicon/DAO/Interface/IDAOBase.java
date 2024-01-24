package se.lexicon.DAO.Interface;

import java.util.List;
import java.util.Optional;

public interface IDAOBase <T, ID>{

    public abstract T save(T item);
    public abstract Optional<T> findByID(ID id);
    public abstract List<T> findAll();
    public abstract void delete(ID id);
}
