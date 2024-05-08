public class Fork {
    private boolean inUse = false;                  // initialize the fork as not in use
    
    public synchronized boolean isAvailable() {     // method to check if the fork is available
        return !inUse;                              // returns true if the fork is not in use, false otherwise
    }
    
    public synchronized void setAvailable(boolean available) {  // method to set the availability of the fork
        this.inUse = !available;                    // sets inUse to true if the fork is not available, false otherwise
    }
}