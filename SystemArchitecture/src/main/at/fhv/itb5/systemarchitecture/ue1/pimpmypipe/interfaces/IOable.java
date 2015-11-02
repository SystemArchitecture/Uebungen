package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces;


public interface IOable<in, out> extends Readable<out>, Writeable<in> {

}
