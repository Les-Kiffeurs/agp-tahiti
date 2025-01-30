package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.locations.Hotel;
import fr.cyu.depinfo.agp.tahiti.business.locations.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeansClustering {
    
    
    
    /**
     * Perform K-Means clustering on a list of points.
     *
     * @param points the points to cluster
     * @param k number of clusters
     * @param maxIterations maximum number of iterations
     * @return a list of centroids
     */
    public static List<Position> kMeans(List<Position> points, int k, int maxIterations) {
        if (k <= 0 || k > points.size()) {
            throw new IllegalArgumentException("k must be between 1 and the number of points");
        }

        List<Position> centroids = initRandomCentroids(points, k);

        int[] clusterAssignments = new int[points.size()];

        for (int iter = 0; iter < maxIterations; iter++) {
            boolean changed = false;

            for (int i = 0; i < points.size(); i++) {
                Position p = points.get(i);
                int closestCentroidIndex = 0;
                double minDist = Double.MAX_VALUE;
                for (int c = 0; c < k; c++) {
                    double dist = p.distanceFrom(centroids.get(c));
                    if (dist < minDist) {
                        minDist = dist;
                        closestCentroidIndex = c;
                    }
                }
                if (clusterAssignments[i] != closestCentroidIndex) {
                    clusterAssignments[i] = closestCentroidIndex;
                    changed = true;
                }
            }

            if (changed) {
                double[] sumX = new double[k];
                double[] sumY = new double[k];
                int[] count = new int[k];

                for (int i = 0; i < points.size(); i++) {
                    int clusterIndex = clusterAssignments[i];
                    sumX[clusterIndex] += points.get(i).getLongitude();
                    sumY[clusterIndex] += points.get(i).getLatitude();
                    count[clusterIndex]++;
                }

                for (int c = 0; c < k; c++) {
                    if (count[c] != 0) {
                        double newX = sumX[c] / count[c];
                        double newY = sumY[c] / count[c];
                        centroids.set(c, new Position(newX, newY));
                    }
                }
            } else {
                break;
            }
        }

        return centroids;
    }

    private static List<Position> initRandomCentroids(List<Position> points, int k) {
        List<Position> centroids = new ArrayList<>();
        Random rand = new Random();
        // We pick k random distinct points from the dataset as initial centroids
        List<Position> copy = new ArrayList<>(points);
        for (int i = 0; i < k; i++) {
            int randomIndex = rand.nextInt(copy.size());
            centroids.add(copy.remove(randomIndex));
        }
        return centroids;
    }

    public static List<Hotel> OneNN(List<Position> centroids, List<Hotel> hotels) {
        List<Hotel> bestHotels = new ArrayList<>();
        List<Hotel> availableHotels = new ArrayList<>(hotels);
        for (Position centroid : centroids) {
            double minDist = Double.MAX_VALUE;
            Hotel bestHotelForThisCentroid = null;

            for (Hotel hotel : availableHotels) {
                double dist = centroid.distanceFrom(hotel.getPosition());
                if (dist < minDist) {
                    minDist = dist;
                    bestHotelForThisCentroid = hotel;
                }
            }
            if (bestHotelForThisCentroid != null) {
                bestHotels.add(bestHotelForThisCentroid);
                // sinon il retourne le même hotel
                availableHotels.remove(bestHotelForThisCentroid);
            }
        }
        return bestHotels;
    }
}
