package Tests;

import Models.Link;
import Models.Room;
import Services.LinkService;
import Services.RoomService;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class LinkSteps {
    private LinkService linkService = new LinkService();
    private RoomService roomService = new RoomService();
    Link link;
    Room room;
    String linkUrl;
    String roomLink;
    String likedVideo;
    String deletedVideo;
    @Given("Намерил е линка за видеото което иска да споделя")
    public void намерил_е_линка_за_видеото_което_иска_да_споделя() {
        room = roomService.createRoom();
        link = linkService.shareVideo("youtube.com/video/SmokSviriNaVyrba");
    }
    @When("въведе Линка в полето")
    public void въведе_линка_в_полето() {
       roomLink =  linkService.addVideoToRoom(room ,link);
    }
    @Then("Линка е валиден")
    public void започва_възпройзвеждането() {
        assertEquals(roomLink , link.getId());
    }
    @Given("Има вече създадена стая")
    public void има_вече_създадена_стая() {
       room = roomService.createRoom();
        link = linkService.shareVideo("youtube.com/video/ПенгалингаГледаПроекти");
    }
    @When("Избере да види детайлите на видеото")
    public void избере_да_види_детайлите_на_видеото() {
        linkService.getVideoDetails( link.getUrl());
    }
    @Then("Детайлите се показват")
    public void детайлите_се_показват() {
        assertNotNull(linkService.getVideoDetails( link.getUrl()));
    }
    @Given("Потребителя е вече в стая")
    public void потребителя_е_вече_в_стая() {
        room = roomService.createRoom();
        link = linkService.shareVideo("youtube.com/video/danchoEmnQk");
    }
    @When("Иска да хареса видеото в стаята")
    public void иска_да_хареса_видеото_в_стаята() {
        likedVideo = linkService.likeVideo(link.getId());
    }
    @Then("Показва се съобщение за успешно харесване")
    public void показва_се_съобщение_за_успешно_харесване() {
        assertEquals(likedVideo , link.getId());
    }
    @Given("Потребителят иска да изтрие видеото")
    public void потребителят_иска_да_изтрие_видеото() {
        room = roomService.createRoom();
        link = linkService.shareVideo("youtube.com/video/KefMiENADushtaVasko");
    }
    @When("Натисне бутона за изтриване")
    public void натисне_бутона_за_изтриване() {
       deletedVideo =  linkService.removeVideoFromRoom(room , link.getId());

    }
    @Then("Показва се съобщение за успешно изтриване")
    public void показва_се_съобщение_за_успешно_изтриване() {
        assertEquals(deletedVideo , link.getId());
    }
    @Given("Потребителя иска да въведе линк в стаята")
    public void потребителя_иска_да_въведе_линк_в_стаята() {
        room = roomService.createRoom();
    }
    @When("Линкът е невалиден {string}")
    public void линкът_е_невалиден(String string) {
        link = linkService.shareVideo(string);
    }
    @Then("ПОказва се съобщение за грешка")
    public void п_оказва_се_съобщение_за_грешка() {
        assertEquals(link.getUrl() , "");
    }



}