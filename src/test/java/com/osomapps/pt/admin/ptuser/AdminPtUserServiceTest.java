package com.osomapps.pt.admin.ptuser;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
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

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() {
        adminPtUserService.findOne(1L);
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

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        adminPtUserService.update(1L, new PtUserRequestDTO());
    }

    @Test(expected = UnauthorizedException.class)
    public void update_not_allowed_for_1() {
        when(ptUserRepository.findOne(eq(1L))).thenReturn(new PtUser());
        when(ptUserRepository.save((PtUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        adminPtUserService.update(1L, new PtUserRequestDTO().setRoles(Collections.emptyList()));
    }

    @Test
    public void update() {
        when(ptUserRepository.findOne(eq(2L))).thenReturn(new PtUser());
        when(ptUserRepository.save((PtUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        PtUserResponseDTO responseDTO = adminPtUserService.update(2L,
                new PtUserRequestDTO().setRoles(Collections.emptyList()));
        assertThat(responseDTO, notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        adminPtUserService.delete(1L);
    }

    @Test(expected = UnauthorizedException.class)
    public void delete_not_allowed() {
        when(ptUserRepository.findOne(eq(1L))).thenReturn(new PtUser());
        adminPtUserService.delete(1L);
    }

    @Test
    public void delete() {
        when(ptUserRepository.findOne(eq(2L))).thenReturn(new PtUser());
        PtUserResponseDTO responseDTO = adminPtUserService.delete(2L);
        assertThat(responseDTO, notNullValue());
    }
}
