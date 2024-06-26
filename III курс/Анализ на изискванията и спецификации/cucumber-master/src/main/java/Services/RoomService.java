package Services;

import Models.Link;
import Models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private List<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
    }

    public Room createRoom() {
        Room room = new Room();
        room.setId("room" + (rooms.size() + 1));
        room.setName("Room " + (rooms.size() + 1));
        room.setUrl("https://www.watchTogether.com/room/" + room.getId());
        room.setVideos(new ArrayList<>());
        rooms.add(room);
        return room;
    }

    public String customizeRoomName(String roomId, String newName) {
        for (Room room : rooms) {
            if (room.getId().equals(roomId)) {
                room.setName(newName);
                return newName;
            }
        }
        return null;
    }
    public boolean removeRoom(String roomId) {
        for (Room room : rooms) {
            if (room.getId().equals(roomId)) {
                rooms.remove(room);
                return true;
            }
        }
        return false;
    }
    public String generateRoomURL(String roomId) {
        for (Room room : rooms) {
            if (room.getId().equals(roomId)) {
                return room.getUrl();
            }
        }
        return "";
    }

    public Room getRoomDetails(String roomId) {
        for (Room room : rooms) {
            if (room.getId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }
}