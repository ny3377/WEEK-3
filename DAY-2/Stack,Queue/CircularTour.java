public class CircularTour {

    public static int findStartingPoint(int[] petrol, int[] distance) {
        int totalSurplus = 0;
        int currSurplus = 0;
        int start = 0;

        for (int i = 0; i < petrol.length; i++) {
            int net = petrol[i] - distance[i];
            totalSurplus += net;
            currSurplus += net;

            if (currSurplus < 0) {
                start = i + 1;
                currSurplus = 0;
            }
        }

        return totalSurplus >= 0 ? start % petrol.length : -1;
    }

    public static void main(String[] args) {
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};

        int startIndex = findStartingPoint(petrol, distance);
        System.out.println("Start at pump index: " + startIndex);
    }
}

