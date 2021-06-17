package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminPtUserServiceTest {

    @Mock private PtUserRepository ptUserRepository;
    @Mock private PtRoleRepository ptRoleRepository;
    @InjectMocks private AdminPtUserService adminPtUserService;

    @Test
    public void findAll() {
        when(ptUserRepository.findAll()).thenReturn(Arrays.asList(new PtUser()));
        List<PtUserResponseDTO> responseDTOs = adminPtUserService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() {
        adminPtUserService.findOne(1L);
    }

    @Test
    public void findOne() {
        when(ptUserRepository.findById(eq(1L))).thenReturn(Optional.of(new PtUser()));
        PtUserResponseDTO responseDTO = adminPtUserService.findOne(1L);
        assertThat(responseDTO, notNullValue());
    }

    @Test
    public void create() {
        when(ptUserRepository.save((PtUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        PtUserResponseDTO responseDTO =
                adminPtUserService.create(new PtUserRequestDTO().setRoles(Collections.emptyList()));
        assertThat(responseDTO, notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        adminPtUserService.update(1L, new PtUserRequestDTO());
    }

    @Test(expected = UnauthorizedException.class)
    public void update_not_allowed_for_1() {
        when(ptUserRepository.findById(eq(1L))).thenReturn(Optional.of(new PtUser()));
        adminPtUserService.update(1L, new PtUserRequestDTO().setRoles(Collections.emptyList()));
    }

    @Test
    public void update() {
        when(ptUserRepository.findById(eq(2L))).thenReturn(Optional.of(new PtUser()));
        when(ptUserRepository.save((PtUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        PtUserResponseDTO responseDTO =
                adminPtUserService.update(
                        2L, new PtUserRequestDTO().setRoles(Collections.emptyList()));
        assertThat(responseDTO, notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        adminPtUserService.delete(1L);
    }

    @Test(expected = UnauthorizedException.class)
    public void delete_not_allowed() {
        when(ptUserRepository.findById(eq(1L))).thenReturn(Optional.of(new PtUser()));
        adminPtUserService.delete(1L);
    }

    @Test
    public void delete() {
        when(ptUserRepository.findById(eq(2L))).thenReturn(Optional.of(new PtUser()));
        PtUserResponseDTO responseDTO = adminPtUserService.delete(2L);
        assertThat(responseDTO, notNullValue());
    }
}
