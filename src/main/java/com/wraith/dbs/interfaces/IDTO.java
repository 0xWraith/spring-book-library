package com.wraith.dbs.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IDTO
{
    @JsonIgnore
    boolean isDTOInvalid();
}
