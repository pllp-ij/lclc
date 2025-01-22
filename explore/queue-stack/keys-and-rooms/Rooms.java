import java.util.List;
import java.util.ArrayList;

class Rooms {
    
    public List<List<Integer>> roomsList;
    
    public Rooms(String roomsStr) {
        String roomsSubStr = roomsStr.substring(1, roomsStr.length() - 1);
        String[] roomsSubStrSplitted = roomsSubStr.split(" ");
        
        roomsList = new ArrayList<>();
        for (int i = 0; i < roomsSubStrSplitted.length; i++) {
            String lineStr = roomsSubStrSplitted[i];
            // System.out.println("lineStr: " + lineStr);
            String lineSubStr = lineStr.substring(1, lineStr.length() - 1);
            if (lineSubStr.equals("")) {
                roomsList.add(new ArrayList<>());
                continue;
            }
            String[] numStrArr = lineSubStr.split(",");
            List<Integer> lineResult = new ArrayList<>();
            for (int j = 0; j < numStrArr.length; j++) {
                lineResult.add(Integer.parseInt(numStrArr[j]));
            }
            roomsList.add(lineResult);
        }
    }
    
    public String printRoomsList() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int row = 0; row < roomsList.size(); row++) {
            if (roomsList.get(row).size() == 0) {
                result.append("[]");
                continue;
            }
            result.append("[");
            for (int col = 0; col < roomsList.get(row).size(); col++) {
                result.append(roomsList.get(row).get(col));
                if (col < roomsList.get(row).size() - 1) {
                    result.append(",");
                }
            }
            result.append("]");
            if (row < roomsList.size() - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }
}