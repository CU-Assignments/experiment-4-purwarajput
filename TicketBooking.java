class TicketBookingSystem {
    private int availableSeats = 10;

    public synchronized boolean bookTicket(String customerType) {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println(customerType + " booked a seat. Seats remaining: " + availableSeats);
            return true;
        } else {
            System.out.println("No seats available for " + customerType + ".");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private String customerType;
    private TicketBookingSystem bookingSystem;

    public BookingThread(String customerType, TicketBookingSystem bookingSystem) {
        this.customerType = customerType;
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void run() {
        boolean success = false;
        while (!success) {
            success = bookingSystem.bookTicket(customerType);
            if (!success) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        BookingThread regularCustomer1 = new BookingThread("Regular Customer 1", bookingSystem);
        BookingThread regularCustomer2 = new BookingThread("Regular Customer 2", bookingSystem);
        BookingThread vipCustomer1 = new BookingThread("VIP Customer 1", bookingSystem);
        BookingThread vipCustomer2 = new BookingThread("VIP Customer 2", bookingSystem);

        vipCustomer1.setPriority(Thread.MAX_PRIORITY);
        vipCustomer2.setPriority(Thread.MAX_PRIORITY);
        regularCustomer1.setPriority(Thread.NORM_PRIORITY);
        regularCustomer2.setPriority(Thread.NORM_PRIORITY);

        vipCustomer1.start();
        vipCustomer2.start();
        regularCustomer1.start();
        regularCustomer2.start();
    }
}

