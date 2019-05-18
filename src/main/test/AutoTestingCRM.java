import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import source.SeleniumDriverUtil;

public class AutoTestingCRM {


    @BeforeMethod
    public void init() {
        SeleniumDriverUtil.getDriver().get("http://localhost:8080");
    }

    @Test()
    public void logIn() {
        System.out.println("---------Тест Авторизации---------");
//        $("input[id=$'login']").shouldBe(Condition.visible).setValue("admin");
//        $("input[id=$'password']").shouldBe(Condition.visible).setValue("admin");
//        $("button[id=$'SignIn']").shouldBe(Condition.visible).click();

    }

}
