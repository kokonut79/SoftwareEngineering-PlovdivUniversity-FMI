package db;

import Models.Link;
import Models.Room;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MainRepo {
    @Getter
    public static List<Link> links = new ArrayList<>();
    @Getter
    public static List<Room> rooms = new ArrayList<>();

    static {
        Link link1 = new Link("link1", "Link Title 1", "https://www.example.com/link1", 0);
        Link link2 = new Link("link2", "Link Title 2", "https://www.example.com/link2", 0);
        Link link3 = new Link("link3", "Link Title 3", "https://www.example.com/link3", 0);
        links.add(link1);
        links.add(link2);
        links.add(link3);

        Room room1 = new Room("room1", "Room 1", "https://www.example.com/room1", new ArrayList<>());
        Room room2 = new Room("room2", "Room 2", "https://www.example.com/room2", new ArrayList<>());
        Room room3 = new Room("room3", "Room 3", "https://www.example.com/room3", new ArrayList<>());
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
    }

}
