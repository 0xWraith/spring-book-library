package com.wraith.dbs.dto.authors;

import com.wraith.dbs.interfaces.IDTO;

public abstract class AuthorDTO implements IDTO
{
    protected String name;
    protected String surname;

    public String getName() { return name; }
    public String getSurname() { return surname; }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }

    @Override
    public boolean isDTOInvalid()
    {

        if(name == null || name.isEmpty())
            return true;

        return surname == null || surname.isEmpty();
    }
}
