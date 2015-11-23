package quadratic;
import java.io.*;

public class Complex implements Serializable {
  private double real, imaginary;

  public Complex(double real, double imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  public String toString() {
    String sr = "" + real;
    String si = "" + imaginary;
    if(si.startsWith("-")) {
      return sr + si + "i";
    }
    else {
      return sr + "+" + si + "i";
    }
  }
}
