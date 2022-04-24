import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.internal.matchers.Any;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MessageSenderTest {

    @Test
    public void when_location_rus_sends_text_rus()
    {
        LocalizationService localizationService = spy(new LocalizationServiceImpl());
        GeoService geoServiceMock = mock(GeoServiceImpl.class);
        when(geoServiceMock.byIp(anyString())).thenReturn(new Location("", Country.RUSSIA, "", 1));

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "X");

        String message = messageSender.send(headers);
        verify(geoServiceMock, only()).byIp("X");
        verify(localizationService, atLeastOnce()).locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", message);
    }

    @Test
    public void when_location_usa_sends_text_eng()
    {
        LocalizationService localizationService = spy(new LocalizationServiceImpl());
        GeoService geoServiceMock = mock(GeoServiceImpl.class);
        when(geoServiceMock.byIp(anyString())).thenReturn(new Location("", Country.USA, "", 1));

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "X");

        String message = messageSender.send(headers);
        verify(geoServiceMock, only()).byIp("X");
        verify(localizationService, atLeastOnce()).locale(Country.USA);
        assertEquals("Welcome", message);
    }
}
