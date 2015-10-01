package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.repository.InviteRepository;
import com.cassianoalves.quotes.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InviteComponentImplTest {
    @InjectMocks
    private InviteComponentImpl instance;
    @Mock
    private UserRepository userRepository;
    @Mock
    private InviteRepository inviteRepository;
    @Mock
    private EmailComponent emailComponent;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test (expected = ComponentException.class)
    public void testInitialize_AlreadyInitialized() throws Exception {
        when(userRepository.count()).thenReturn(9999L);
        instance.initialize(new Invite());
    }

    @Test (expected = ComponentException.class)
    public void testInitialize_AlreadyInitializedWithoutUsers() throws Exception {
        when(userRepository.count()).thenReturn(0L);
        when(inviteRepository.count()).thenReturn(1L);
        instance.initialize(new Invite());
    }

    @Test
    public void testInitialize() throws Exception {
        when(userRepository.count()).thenReturn(0L);
        when(inviteRepository.count()).thenReturn(0L);
        Invite savedInvite = new Invite();
        Invite initialInvite = new Invite();
        when(inviteRepository.save(same(initialInvite))).thenReturn(savedInvite);

        instance.initialize(initialInvite);
        verify(inviteRepository).save(same(initialInvite));
        verify(emailComponent).sendInvite(same(savedInvite));
    }

}