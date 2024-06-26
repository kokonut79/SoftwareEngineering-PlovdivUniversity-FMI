package Tests;

import Models.Room;
import Services.RoomService;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoomSteps {
    private RoomService roomService = new RoomService();
    Room room ;
    Boolean isRoomCreated ;
    public String roomName ;
    String roomUrl ;
    Boolean IsUrlCreated;
    Boolean isRoomDeleted ;


    @Given("Потребителят иска да създаде стая")
    public void потребителят_иска_да_създаде_стая() {
       room = roomService.createRoom();
    }
    @When("Избере създаване на стая")
    public void избере_създаване_на_стая() {
       isRoomCreated = room != null;
    }
    @Then("Трябва да види че стаята е създадена")
    public void трябва_да_види_че_стаята_е_създадена() {
        assertTrue(isRoomCreated);
    }

    @Given("Потребителят Избира да промени името на стаята")
    public void потребителят_избира_да_промени_името_на_стаята() {
       room = roomService.createRoom();

    }
    @When("Въвежда новото име")
    public void въвежда_новото_име() {
        roomName = roomService.customizeRoomName(room.getId() ,"Pengalinga");
    }
    @Then("Вижда вече променената стая")
    public void вижда_вече_променената_стая() {
        assertEquals("Pengalinga", roomName);
    }
    @Given("Потребителя иска да сподели връзка към стаята")
    public void потребителя_иска_да_сподели_връзка_към_стаята() {
        room = roomService.createRoom();

    }
    @When("Генерира линка за стаята")
    public void генерира_линка_за_стаята() {
        roomUrl = roomService.generateRoomURL(room.getId());
        IsUrlCreated =  roomUrl != "";
    }
    @Then("Вижга генерираният линк")
    public void вижга_генерираният_линк() {
        assertTrue(IsUrlCreated);
    }

    @When("Въвежда новото име което е {string}")
    public void въвежда_новото_име_което_е(String string) {
        roomName = roomService.customizeRoomName(room.getId() , string);
    }
    @Then("Вижда съобщение за грешка")
    public void вижда_съобщение_за_грешка() {
        assertEquals("" , roomName);
    }
    @Given("Иска да изтрие стая")
    public void иска_да_изтрие_стая() {
        room = roomService.createRoom();
    }
    @When("Избира стаята")
    public void избира_стаята() {
       isRoomDeleted =  roomService.removeRoom(room.getId());
    }
    @Then("Стаята бива изтрита")
    public void стаята_бива_изтрита() {
        assertTrue(isRoomDeleted);
    }


}
