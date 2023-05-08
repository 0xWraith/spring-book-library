package com.wraith.dbs.utils.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum EPublicationInstanceStatus
{
    @JsonEnumDefaultValue
    available,
    reserved
}
