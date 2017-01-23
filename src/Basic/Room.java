package Basic;

/**
 * Created by Mitsos on 25/05/16.
 */
public class Room {
    private int RoomId;
    private int RoomType;
    private int RoomCost;

    public Room() {
    }

    public Room(int roomId, int roomType, int roomCost) {
        RoomId = roomId;
        RoomType = roomType;
        RoomCost = roomCost;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public int getRoomType() {
        return RoomType;
    }

    public void setRoomType(int roomType) {
        RoomType = roomType;
    }

    public int getRoomCost() {
        return RoomCost;
    }

    public void setRoomCost(int roomCost) {
        RoomCost = roomCost;
    }
}
