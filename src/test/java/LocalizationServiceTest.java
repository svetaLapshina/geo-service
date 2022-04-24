import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceTest {

    @Test
    public void localization_for_russia_works_correctly()
    {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    public void localization_for_usa_works_correctly()
    {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Welcome", localizationService.locale(Country.USA));
    }

    @Test
    public void localization_for_brazil_works_correctly()
    {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
    }

    @Test
    public void localization_for_germany_works_correctly()
    {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Welcome", localizationService.locale(Country.GERMANY));
    }
}
