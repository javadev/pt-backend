package com.osomapps.pt.admin.ptuser;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminPtUserService {

    private final PtUserRepository ptUserRepository;
    private final PtRoleRepository ptRoleRepository;

    AdminPtUserService(PtUserRepository ptUserRepository, PtRoleRepository ptRoleRepository) {
        this.ptUserRepository = ptUserRepository;
        this.ptRoleRepository = ptRoleRepository;
    }

    private static PtUserResponseDTO ptUserToDto(PtUser ptUser) {
        return PtUserResponseDTO.builder()
                .id(ptUser.getId())
                .created(ptUser.getCreated())
                .login(ptUser.getLogin())
                .name(ptUser.getName())
                .activated(ptUser.getActivated())
                .is_blocked(ptUser.getIs_blocked())
                .is_blocked_date_set(ptUser.getIs_blocked_date_set())
                .blocked_comment(ptUser.getBlocked_comment())
                .blocked_start(ptUser.getBlocked_start())
                .blocked_finish(ptUser.getBlocked_finish())
                .deleted(ptUser.getDeleted())
                .deleted_comment(ptUser.getDeleted_comment())
                .is_deleted(ptUser.getIs_deleted())
                .is_default_password(ptUser.getIs_default_password())
                .description(ptUser.getDescription())
                .phone(ptUser.getPhone())
                .phone2(ptUser.getPhone2())
                .roles(
                        ptUser.getPtRoles().stream()
                                .map(
                                        role ->
                                                PtRoleResponseDTO.builder()
                                                        .id(role.getId())
                                                        .name(role.getName())
                                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }

    List<PtUserResponseDTO> findAll() {
        return ptUserRepository.findAll().stream()
                .map(AdminPtUserService::ptUserToDto)
                .collect(Collectors.toList());
    }

    PtUserResponseDTO findOne(Long id) {
        final PtUser ptUser = ptUserRepository.findById(id).orElse(null);
        if (ptUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
        return ptUserToDto(ptUser);
    }

    PtUserResponseDTO delete(Long id) {
        final PtUser ptUser = ptUserRepository.findById(id).orElse(null);
        if (ptUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
        if (id == 1L) {
            throw new UnauthorizedException("Cannot delete user with id 1.");
        }
        final PtUserResponseDTO ptUserResponseDTO = ptUserToDto(ptUser);
        ptUserRepository.deleteById(id);
        return ptUserResponseDTO;
    }

    PtUserResponseDTO create(PtUserRequestDTO userRequestDTO) {
        final PtUser ptUser = dtoToPtUser(userRequestDTO, new PtUser());
        final PtUser savedPtUser = ptUserRepository.save(ptUser);
        return ptUserToDto(savedPtUser);
    }

    PtUserResponseDTO update(Long id, PtUserRequestDTO userRequestDTO) {
        final PtUser ptUser = ptUserRepository.findById(id).orElse(null);
        if (ptUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
        if (id == 1L) {
            throw new UnauthorizedException("Cannot update user with id 1.");
        }
        final PtUser updatedPtUser = dtoToPtUser(userRequestDTO, ptUser);
        final PtUser savedPtUser = ptUserRepository.save(updatedPtUser);
        return ptUserToDto(savedPtUser);
    }

    private PtUser dtoToPtUser(PtUserRequestDTO userRequestDTO, PtUser ptUser) {
        return ptUser.setLogin(userRequestDTO.getLogin())
                .setPassword(userRequestDTO.getPassword())
                .setName(userRequestDTO.getName())
                .setActivated(userRequestDTO.getActivated())
                .setIs_blocked(userRequestDTO.getIs_blocked())
                .setIs_blocked_date_set(userRequestDTO.getIs_blocked_date_set())
                .setBlocked_comment(userRequestDTO.getBlocked_comment())
                .setBlocked_start(userRequestDTO.getBlocked_start())
                .setBlocked_finish(userRequestDTO.getBlocked_finish())
                .setDeleted(userRequestDTO.getDeleted())
                .setDeleted_comment(userRequestDTO.getDeleted_comment())
                .setIs_deleted(userRequestDTO.getIs_deleted())
                .setIs_default_password(userRequestDTO.getIs_default_password())
                .setDescription(userRequestDTO.getDescription())
                .setPhone(userRequestDTO.getPhone())
                .setPhone2(userRequestDTO.getPhone2())
                .setPtRoles(
                        ptRoleRepository.findAllById(
                                userRequestDTO.getRoles().stream()
                                        .map(role -> role.getId())
                                        .collect(Collectors.toList())));
    }
}
