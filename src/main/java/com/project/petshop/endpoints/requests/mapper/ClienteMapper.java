package com.project.petshop.endpoints.requests.mapper;

import com.project.petshop.endpoints.requests.dto.ClientePostRequestBody;
import com.project.petshop.endpoints.requests.dto.ClientePutRequestBody;
import com.project.petshop.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class ClienteMapper {

    public static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    public abstract Cliente toCliente(ClientePostRequestBody schedulePostRequestBody);
    public abstract Cliente toCliente(ClientePutRequestBody schedulePutRequestBody);

}
