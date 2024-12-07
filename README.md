**backend**

**ParkingManager**

You are an experienced Java software engineer working on a project about digitizing parking lot management. The Parking Manager is responsible for managing three parking lots:

- The Plaza Park (9 parking capacity)
- City Mall Garage (12 parking capacity)
- Office Tower Parking (9 parking capacity)

The Parking Manager has employed three Parking Boys to help manage these parking lots, each utilizing a specific parking strategy:

- Sequentially parking strategy
- Smart parking strategy
- Super Smart parking strategy

Their attribute names within the Parking Manager should be `standardParkingBoy`, `superParkingBoy`, and `superSmartParkingBoy`. Please create a `ParkingManager` class, given the above 3 parking lots and 3 parking boys. The manager should have the following 3 methods:

1. `getAllParkingLots()`: Returns all parking lots.
2. `park(String plateNumber, ParkingStrategy strategy)`: Requests the correct parking boy to do the parking job and returns a `Ticket`.
3. `fetch(Ticket ticket)`: Fetches the car from the corresponding parking lot and returns the car.





**UT for ParkingManager**

You are an experienced Java software engineer working on a project to digitize parking lot management. You have already created a `ParkingManager` class that manages three parking lots and employs three parking boys, each utilizing a specific parking strategy. The `ParkingManager` class has the following methods:

- `getAllParkingLots()`: Returns all parking lots.
- `park(String plateNumber, String strategy)`: Requests the correct parking boy to park the car based on the strategy and returns a `Ticket` object containing the plate number.
- `fetch(String ticket)`: Fetches the car from the corresponding parking lot using the ticket and returns the car.

The `ParkingManager` class manages the following parking lots:
- The Plaza Park (9 parking capacity)
- City Mall Garage (12 parking capacity)
- Office Tower Parking (9 parking capacity)

The `ParkingManager` employs three parking boys with the following strategies:
- `StandardParkingBoy`: Standard parking strategy
- `SuperParkingBoy`: Smart parking strategy
- `SuperSmartParkingBoy`: Super smart parking strategy

Your task is to write unit tests for the `ParkingManager` class using JUnit 5 in a Spring Boot environment. The unit tests should cover the following scenarios:

1. **Initialization Test**: Verify that the `ParkingManager` initializes correctly with the three parking lots and three parking boys.
2. **Get All Parking Lots Test**: Verify that the `getAllParkingLots()` method returns all the parking lots.
3. **Park Car Test**: Verify that the `park(String plateNumber, ParkingStrategy strategy)` method requests the correct parking boy to park the car and returns a valid `Ticket`.
4. **Fetch Car Test**: Verify that the `fetch(Ticket ticket)` method fetches the car from the corresponding parking lot using the ticket and returns the correct car.

Make sure all the test case function names are written using the `should_when_given` format.



**Controller**

You are an experienced Java software engineer working on a project to digitize parking lot management. You have already created a `ParkingManager` class that manages three parking lots and employs three parking boys, each utilizing a specific parking strategy. The `ParkingManager` class has the following methods:

- `getAllParkingLots()`: Returns all parking lots.
- `park(String plateNumber, ParkingStrategy strategy)`: Requests the correct parking boy to park the car based on the strategy and returns a `Ticket` object containing the plate number.
- `fetch(Ticket ticket)`: Fetches the car from the corresponding parking lot using the ticket and returns the car.

The `ParkingManager` class manages the following parking lots:

- The Plaza Park (9 parking capacity)
- City Mall Garage (12 parking capacity)
- Office Tower Parking (9 parking capacity)

The `ParkingManager` employs three parking boys with the following strategies:

- `StandardParkingBoy`: Standard parking strategy
- `SuperParkingBoy`: Smart parking strategy
- `SuperSmartParkingBoy`: Super smart parking strategy

Your task is to create a `ParkingLotController` class using `RestController` with a default mapping of `/`. Implement the following endpoints:

1. **GET /parking-lots**: Returns a list of all parking lots based on the `ParkingManager` API.
2. **POST /park**: Accepts a request body with `plateNumber` and `parkingBoyStrategy` (create a new DTO called `ParkDTO`) and returns a `Ticket` based on the `ParkingManager` API.
3. **POST /fetch**: Accepts a request body with `plateNumber` (create a new DTO called `FetchDTO`) and returns a `Car` based on the `ParkingManager` API.







**Frontend**

You are a senior front-end development engineer. You are using React and Ant Design technology stacks.

Now you need to design a front-end interface for a parking lot. This interface has the following components:

1. **PlateNumberInput**: This component is an input box for entering license plate numbers, with corresponding labels.
2. **ParkingStrategySelector**: This component is a radio button for selecting a parking strategy, with options including 'Standard', 'Smart', and 'SuperSmart'.
3. **ParkingLotGroup**: This component requests data for all parking lots from the backend and passes the corresponding data to **ParkingLotView**.
4. **ParkingLotView**: This component displays the parking situation of the parking lot. The view is grid-like, and the backend data has already specified that the number of parking spaces in the parking lot is 9 or 12. The frontend displays 3x3 or 3x4; the parking space displays the license plate number.
5. **ActionsButton**: This component contains two buttons, one is 'Park' and the other is 'Fetch'. This component accepts data from **PlateNumberInput** and **ParkingStrategySelector**, receives license plate numbers and parking strategies, and selects to park or pick up the vehicle.

Detail requirements:
1. Use Axios to request data from the backend.
2. All components are under the `App.js` component.
3. **PlateNumberInput**, **ParkingStrategySelector**, and **ActionsButton** are on the same line. Below are the parking situations and corresponding parking lot names displayed for each parking lot.
4. Use the `useState` function and `useReducer` function.
5. The data structure requested by the backend will be provided later, so let's first mock the data.



**with** **Backend**

I will now provide you with the backend-related code to obtain the parking situation of the parking lot. Please complete the corresponding front-end code based on these codes.

**Controller:**
```java
@GetMapping("/parking-lots")
public List<ParkingLot> getAllParkingLots() {
    return parkingManager.getAllParkingLots();
}
```

**ParkingManager:**
```java
public ParkingManager() {
    ParkingLot plazaPark = new ParkingLot(1, "The Plaza Park", 9);
    ParkingLot cityMallGarage = new ParkingLot(2, "City Mall Garage", 12);
    ParkingLot officeTowerParking = new ParkingLot(3, "Office Tower Parking", 9);

    this.parkingLots = Arrays.asList(plazaPark, cityMallGarage, officeTowerParking);

    this.standardParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
    this.superParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
    this.superSmartParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());
}

public List<ParkingLot> getAllParkingLots() {
    return parkingLots;
}
```



