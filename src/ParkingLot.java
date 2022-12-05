import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ParkingLot {
    private String lotID;
    private List<ParkingFloor> floors;
    private List<Entrance> entry;
    private List<Exit> exit;

    public static ParkingLot INSTANCE = new ParkingLot();

    private ParkingLot(){
        this.lotID = UUID.randomUUID().toString();
        floors = new ArrayList<>();
        entry = new ArrayList<>();
        exit = new ArrayList<>();
    }

    public boolean ifFull() {
        int i = 0;
        BitSet bitSet = new BitSet();
        for(ParkingFloor floor : floors) {
            if(ParkingFloor.canPark(ParkingFloor.getSpotType(vehicleType))) return true;
        }
        return false;
    }

    public ParkingSpot vacateSpot(String lotID) {
        for(ParkingFloor floor : ParkingLot.INSTANCE.getFloors()) {
            ParkingSpot spot = floor.vacateSpot(lotID);
            if(spot != null) {
                return spot;
            }
        }
    }
}
