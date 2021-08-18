package com.project.petshop.endpoints.requests.mapper;

import com.project.petshop.endpoints.requests.dto.PetPostRequestBody;
import com.project.petshop.endpoints.requests.dto.PetPutRequestBody;
import com.project.petshop.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class PetMapper {

    public static final PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    public abstract Pet toPet(PetPostRequestBody petPostRequestBody);
    public abstract Pet toPet(PetPutRequestBody petPutRequestBody);

}
