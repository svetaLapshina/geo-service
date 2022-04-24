import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeoServiceTest {

    @Test
    public void location_by_ip_works_correctly_for_localhost()
    {
        Location location = new GeoServiceImpl().byIp("127.0.0.1");
        assertNull(location.getCountry());
        assertNull(location.getCity());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }

    @Test
    public void location_by_ip_works_correctly_for_moscow()
    {
        Location location = new GeoServiceImpl().byIp("172.0.32.11");
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Moscow", location.getCity());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuiling());
    }

    @Test
    public void location_by_up_works_correctly_for_newyork()
    {
        Location location = new GeoServiceImpl().byIp("96.44.183.149");
        assertEquals(Country.USA, location.getCountry());
        assertEquals("New York", location.getCity());
        assertEquals("10th Avenue", location.getStreet());
        assertEquals(32, location.getBuiling());
    }

    @Test
    public void location_by_ip_works_correctly_for_russia()
    {
        Location location = new GeoServiceImpl().byIp("172.x");
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Moscow", location.getCity());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }

    @Test
    public void location_by_ip_works_correctly_for_usa()
    {
        Location location = new GeoServiceImpl().byIp("96.x");
        assertEquals(Country.USA, location.getCountry());
        assertEquals("New York", location.getCity());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }

    @Test
    public void location_by_ip_returns_null_if_not_russia_or_usa_or_localhost()
    {
        Location location = new GeoServiceImpl().byIp("192.168.0.1");
        assertNull(location);
    }
}
