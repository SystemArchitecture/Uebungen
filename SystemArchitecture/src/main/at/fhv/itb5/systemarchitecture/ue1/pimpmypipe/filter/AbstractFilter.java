package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.IOable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public abstract class AbstractFilter<in, out> implements IOable<in, out>, Runnable{
    private Readable<in> _predecessor = null;
    private Writeable<out> _sucessor = null;

    public Object ENDING_SIGNAL = null;
    
    public AbstractFilter(Readable<in> input) throws InvalidParameterException{
        if (input == null){
            throw new InvalidParameterException("input can't be null!");
        }
        _predecessor = input;
    }
    
    public AbstractFilter(Writeable<out> output)throws InvalidParameterException{
        if (output == null){
            throw new InvalidParameterException("output can't be null!");
        }
        _sucessor = output;
    }
    
    public AbstractFilter(Readable<in> input, Writeable<out> output)throws InvalidParameterException{
        if (input == null){
            throw new InvalidParameterException("input can't be null!");
        }else if (output == null){
            throw new InvalidParameterException("output can't be null!");
        }
        _predecessor = input;
        _sucessor = output;
    }
    
    protected void writeOutput(out value) throws StreamCorruptedException{
        if (_sucessor != null){
            if (value == ENDING_SIGNAL) beforeSendingEndingSignal();
            _sucessor.write(value);
        }else{
            throw new StreamCorruptedException("output is null");
        }
    }
    
    protected in readInput() throws StreamCorruptedException {
        if (_predecessor != null){
            return _predecessor.read();
        }else{
            throw new StreamCorruptedException("predessesor is null");
        }
    }
    
    /**
     * send a signal, that the end of the stream has been reached
     */
    protected void sendEndSignal() throws StreamCorruptedException{
        writeOutput(null);
    }
    
    /**
     * derived class may override this, if they are interested in 
     * getting informed before the ending-signal is sended
     */
    protected void beforeSendingEndingSignal() throws StreamCorruptedException {}
}
