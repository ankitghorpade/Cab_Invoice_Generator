package company;

public class InvoiceGenerator {

    private static final double MINIMUM_FARE = 5;
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private final RideRepository rideRepository;

    public InvoiceGenerator(){
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        if (totalFare < MINIMUM_FARE)
            return MINIMUM_FARE;
        return totalFare;
    }

    public double calculateFareForMultipleRides(Ride[] rides) {
        double aggregateTotalFare = 0;
        for (Ride ride : rides) {
            aggregateTotalFare += this.calculateFare(ride.distance, ride.time);
        }
        return aggregateTotalFare;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }
}