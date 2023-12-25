package kz.project.moped.presentation.mapper;

import kz.project.moped.domain.model.User;
import kz.project.moped.presentation.dto.UserDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDTOMapper {
    User toDomain(UserDTO userDTO);
    UserDTO toDTO(User user);
}
