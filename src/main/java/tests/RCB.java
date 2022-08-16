package tests;

import frameworks.JsonUtils;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class RCB {

    private JsonPath jsonPath;

    @BeforeClass
    public void preCondition() {
        Assert.assertNotNull(JsonUtils.fetchJsonData());
        jsonPath = JsonUtils.fetchJsonData();
    }

    @Test
    public void verifyNumOgForeignPlayers() {
        List<String> countries = jsonPath.getList("player.country"); //get list of countries available

        int actForeignPlayersCount = (int) countries.stream().filter(country -> !country.equals("India")).count(); //filter foreign countries & get count

        Assert.assertTrue(actForeignPlayersCount < 5, "Number Of Foreign Players Are More Then Allowed Limit Of 4");
    }

    @Test
    public void verifyAvailableWicketKeepers() {
        List<String> countries = jsonPath.getList("player.role"); //get list of roles available

        int wicketKeeperCount = (int) countries.stream().filter(country -> country.equals("Wicket-keeper")).count(); //find num of wicket keepers available
        Assert.assertTrue(wicketKeeperCount >= 1, "No Wicket Keeper Found In Team");
    }
}
