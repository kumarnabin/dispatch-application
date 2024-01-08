package com.konnect.app.service.mapper;

import com.konnect.app.domain.ExcelData;
import com.konnect.app.service.dto.ExcelDataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExcelData} and its DTO {@link ExcelDataDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExcelDataMapper extends EntityMapper<ExcelDataDTO, ExcelData> {}
