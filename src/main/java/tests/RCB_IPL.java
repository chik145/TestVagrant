package tests;

import frameworks.JsonUtils;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class RCB_IPL {

    private JsonPath jsonPath;

    @BeforeClass
    public void preCondition() {
        assertNotNull(JsonUtils.fetchJsonData()); //Json File Should Not Be Blank
        jsonPath = JsonUtils.fetchJsonData();
    }

    @Test
    public void verifyNumOgForeignPlayers() {
        List<String> countries = jsonPath.getList("player.country"); //get list of countries available

        int foreignPlayersCount = (int) countries.stream().filter(country ->
                !country.equals("India")).count(); //filter foreign countries & get count

        assertEquals(foreignPlayersCount, 4,
                "Number Of Foreign Players Are More Then Allowed Limit Of 4");
    }

    @Test
    public void verifyAvailableWicketKeepers() {
        List<String> countries = jsonPath.getList("player.role"); //get list of roles available

        int wicketKeeperCount = (int) countries.stream().filter(country ->
                country.equals("Wicket-keeper")).count(); //find num of wicket keepers available

        assertTrue(wicketKeeperCount >= 1,
                "No Wicket Keeper Found In Team");
    }
}
