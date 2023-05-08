package com.wraith.dbs.interfaces;

public interface IService<M, D>
{
    M save(M entity);
    void delete(M entity);
    M convertToEntity(D dto);
    D convertToDTO(M entity);
}
