

import static org.junit.Assert.*;

import java.util.Scanner;
import java.util.UUID;

import com.jetbrains.Model.Location;
import com.jetbrains.Model.LocationType;
import com.jetbrains.service.LocationService;
import org.junit.Test;




public class LocationTests {

    LocationService service = new LocationService();
    Location location = new Location();

    @Test
    public void createProvince() {


        Scanner sc = new Scanner(System.in);
        System.out.println("Enter province code:");
        location.setLocationCode(sc.next());
        System.out.println("Enter province name:");
        location.setLocationName(sc.next());

        location.setLocationType(LocationType.PROVINCE);
        location.setParent(null);


        assertNotNull(service.saveLocation(location));

    }



    @Test
    public void CreateDistrictFromProvince() {

        Location districtLocation = new Location();
        Location location = new Location();
        Scanner sc = new Scanner(System.in);
    	System.out.println("Enter District Code:");
        String locationCode = sc.nextLine();
        location.setLocationCode(locationCode);
        System.out.println("Enter District Name:");
        String locationName = sc.nextLine();
        location.setLocationName(locationName);
        location.setLocationType(LocationType.DISTRICT);
        location.setParent(new Location(UUID.fromString("99a3d748-6f25-4777-a122-8b1530f18b4e")));

        assertNotNull(service.saveLocation(location));

    }

    @Test
    public void CreateSectorFromDistrict() {

        Location districtLocation = new Location();
        Location location = new Location();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Sector Code:");
        String locationCode = sc.nextLine();
        location.setLocationCode(locationCode);
        System.out.println("Enter Sector Name:");
        String locationName = sc.nextLine();
        location.setLocationName(locationName);
        location.setLocationType(LocationType.SECTOR);
        location.setParent(new Location(UUID.fromString("ec35aa6a-adf0-4628-be49-7ef0be1231b6")));

        assertNotNull(service.saveLocation(location));

    }

    @Test
    public void CreateCellFromSector() {

        Location districtLocation = new Location();
        Location location = new Location();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Cell Code:");
        String locationCode = sc.nextLine();
        location.setLocationCode(locationCode);
        System.out.println("Enter Cell Name:");
        String locationName = sc.nextLine();
        location.setLocationName(locationName);
        location.setLocationType(LocationType.CELL);
        location.setParent(new Location(UUID.fromString("1d48a92e-9057-4960-96c0-78828adf13ae")));

        assertNotNull(service.saveLocation(location));

    }

    @Test
    public void CreateVillageFromCell() {

        Location districtLocation = new Location();
        Location location = new Location();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Village Code:");
        String locationCode = sc.nextLine();
        location.setLocationCode(locationCode);
        System.out.println("Enter Village Name:");
        String locationName = sc.nextLine();
        location.setLocationName(locationName);
        location.setLocationType(LocationType.VILLAGE);
        location.setParent(new Location(UUID.fromString("d7c5f68e-bdfc-40a6-aa3a-0e8664fdfae0")));

        assertNotNull(service.saveLocation(location));

    }



    @Test
    public void testgetLocationFromVillage() {
        Location village = new Location();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter village name:");
        String villageName = sc.nextLine().trim();
        village.setLocationName(villageName);
        Location province = service.getProvince(village);
        System.out.println("\nThe village "+villageName+" is in " + province.getLocationName());

        assertNotNull(province);
    }


    @Test
    public void testGetLocationByPersonId() {
        // Get the person ID as input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user id:");
        String personId = sc.nextLine();



        Location province = service.getProvinceByUserId(personId);


        if (province != null) {
            System.out.println("\nThe user with person ID " + personId + " is located in " + province.getLocationName());
        } else {
            System.out.println("No location found for user with ID " + personId);
        }


        assertNotNull(province);
    }
}

