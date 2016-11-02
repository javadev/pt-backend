package com.github.pt.controller;

import com.github.pt.model.Shipwreck;
import com.github.pt.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShipwreckControllerTest {

    // Mockito is now managing the fixture (no need to explicitly instntiate it in test)
    @InjectMocks
    private ShipwreckController fixture;

    // Mockito framework is now also managing the repository dependency that fixture has
    @Mock
    private ShipwreckRepository mockShipwreckRepository;

    @Before
    public void setup() throws Exception {
        // Initialize all mock objects when test runs.
        // Mockito will automatically setup deps with this line (similar to how spring manages deps).
        // Mockito looks at @InjectMocks and @Mock's and determines if they need to be pushed together.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGet() throws Exception {
        Long id = 123L;
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(id)
                .setName("foo")
                .setDescription("foo test");
        when(mockShipwreckRepository.findOne(id)).thenReturn(shipwreck);

        Shipwreck result = fixture.get(id);

        // junit assertion
//        assertEquals(123L, result.getId().longValue());

        // hamcrest assertion
        assertThat(result.getId(), is(123L));

        verify(mockShipwreckRepository).findOne(id);
    }

}