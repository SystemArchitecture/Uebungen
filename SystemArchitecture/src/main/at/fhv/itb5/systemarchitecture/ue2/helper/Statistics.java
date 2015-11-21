package main.at.fhv.itb5.systemarchitecture.ue2.helper;

public class Statistics {
	public static double mean(Long[] values) {
	    double sum = 0;
	    for (int i = 0; i < values.length; i++) {
	        sum += values[i];
	    }
	    return sum / values.length;
	}
	
	public static double median(Long[] values) {
	    int middle = values.length/2;
	    if (values.length%2 == 1) {
	        return values[middle];
	    } else {
	        return (values[middle-1] + values[middle]) / 2.0;
	    }
	}
}
