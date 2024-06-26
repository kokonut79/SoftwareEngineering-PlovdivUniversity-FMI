package Services;

import Models.Room;
import Models.Link;

public class LinkService {
    // Method to share a video
    public Link shareVideo(String videoUrl) {
        Link link = new Link("1", "aloda", "youtube.com/video/SmokSviriNaVyrba" , 2);
        link.setUrl(videoUrl);
        return link;
    }

    // Method to get video details
    public Link getVideoDetails(String videoUrl) {
        Link video = new Link();
        video.setId("video123");
        video.setTitle("Sample Video");
        video.setUrl(videoUrl);
        video.setTimestamp(0);
        return video;
    }

    // Method to like a video
    public String likeVideo(String videoId) {
        return  videoId;
    }

    // Method to add a video to a room
    public String addVideoToRoom(Room room, Link video) {
        room.getVideos().add(video);
        return  video.getId() ;
    }

    // Method to remove a video from a room
    public String removeVideoFromRoom(Room room, String videoId) {
        for (Link video : room.getVideos()) {
            if (video.getId().equals(videoId)) {
                room.getVideos().remove(video);
                return  videoId ;
            }
        }
        return videoId ;
    }
}
