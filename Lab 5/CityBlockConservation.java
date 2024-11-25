interface WaterConservationSystem {
    public int calculateTrappedWater(int[] blockHeights);
}

abstract class RainySeasonConservation implements WaterConservationSystem {
    public abstract int calculateTrappedWater(int[] blockHeights);
}

public class CityBlockConservation extends RainySeasonConservation {
    public int calculateTrappedWater(int[] blockHeights) {
        int n = blockHeights.length;
        int left_high = 0;
        int right_high = 0;
        int left_min_index = 0;
        int right_min_index = 0;
        int trappedWater = 0;

        for (int i = 0; i < n; i++) {
            if (blockHeights[i] > left_high) {
                left_high = blockHeights[i];
                left_min_index = i;
                break;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (blockHeights[i] > right_high) {
                right_high = blockHeights[i];
                right_min_index = i;
                break;
            }
        }

        int min_side = Math.min(left_high, right_high);

        for (int i = left_min_index + 1; i < right_min_index; i++) {
            trappedWater += (min_side - blockHeights[i]);
        }

        return trappedWater;
    }

    public static void main(String[] args) {
        CityBlockConservation cityBlockConservation = new CityBlockConservation();

        
        System.out.println("Trapped water: " + cityBlockConservation.calculateTrappedWater(new int[] { 5, 0, 0, 2, 0, 4 }));
    }
}