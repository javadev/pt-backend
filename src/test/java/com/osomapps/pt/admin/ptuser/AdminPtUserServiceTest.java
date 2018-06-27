package com.osomapps.pt.admin.ptuser;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminPtUserServiceTest {

    @Mock
    private PtUserRepository ptUserRepository;
    @Mock
    private PtRoleRepository ptRoleRepository;
    @InjectMocks
    private AdminPtUserService adminPtUserService;

    @Test
    public void findAll() {
        when(ptUserRepository.findAll()).thenReturn(Arrays.asList(new PtUser()));
        List<PtUserResponseDTO> responseDTOs = adminPtUserService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

    @Test
    public void findOne_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminPtUserService.findOne(1L);});
    }

    @Test
    public void findOne() {
        when(ptUserRepository.findOne(eq(1L))).thenReturn(new PtUser());
        PtUserResponseDTO responseDTO = adminPtUserService.findOne(1L);
        assertThat(responseDTO, notNullValue());
    }

    @Test
    public void create() {
        when(ptUserRepository.findOne(eq(1L))).thenReturn(new PtUser());
        when(ptUserRepository.save((PtUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        PtUserResponseDTO responseDTO = adminPtUserService.create(
                new PtUserRequestDTO().setRoles(Collections.emptyList()));
        assertThat(responseDTO, notNullValue());
    }

    @Test
    public void update_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminPtUserService.update(1L, new PtUserRequestDTO());});
    }

    @Test
    public void update_not_allowed_for_1() {
        when(ptUserRepository.findOne(eq(1L))).thenReturn(new PtUser());
        when(ptUserRepository.save((PtUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        assertThrows(UnauthorizedException.class, () -> {adminPtUserService.update(1L, new PtUserRequestDTO().setRoles(Collections.emptyList()));});
    }

    @Test
    public void update() {
        when(ptUserRepository.findOne(eq(2L))).thenReturn(new PtUser());
        when(ptUserRepository.save((PtUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        PtUserResponseDTO responseDTO = adminPtUserService.update(2L,
                new PtUserRequestDTO().setRoles(Collections.emptyList()));
        assertThat(responseDTO, notNullValue());
    }

    @Test
    public void delete_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminPtUserService.delete(1L);});
    }

    @Test
    public void delete_not_allowed() {
        when(ptUserRepository.findOne(eq(1L))).thenReturn(new PtUser());
        assertThrows(UnauthorizedException.class, () -> {adminPtUserService.delete(1L);});
    }

    @Test
    public void delete() {
        when(ptUserRepository.findOne(eq(2L))).thenReturn(new PtUser());
        PtUserResponseDTO responseDTO = adminPtUserService.delete(2L);
        assertThat(responseDTO, notNullValue());
    }
}
